package peliculas.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import peliculas.dto.PeliculaGetDTO;
import peliculas.dto.PeliculaSaveDTO;
import peliculas.entity.Genero;
import peliculas.entity.Mensaje;
import peliculas.entity.Pelicula;
import peliculas.repository.GeneroRepository;
import peliculas.repository.PeliculaRepository;
import peliculas.service.PeliculaService;

@Service
public class PeliculaServiceImpl implements PeliculaService  {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<PeliculaGetDTO> findAll(Pageable pageable) {
        Page<Pelicula> peliculas = peliculaRepository.findAll(pageable);
        return peliculas.map(peli -> modelMapper.map(peli, PeliculaGetDTO.class));
    }

    @Override
    public ResponseEntity<PeliculaGetDTO> findOneById(Integer id) {
        boolean existe = peliculaRepository.existsById(id);
        if (!existe){
            return ResponseEntity.badRequest().build();
        }

        Pelicula pelicula = peliculaRepository.findById(id).get();
        PeliculaGetDTO dto = modelMapper.map(pelicula, PeliculaGetDTO.class);
        return ResponseEntity.ok(dto);
    }


    @Override
    public ResponseEntity<PeliculaGetDTO> save(PeliculaSaveDTO pelicula) {
        if (pelicula.getId() != null){
            return ResponseEntity.noContent().build();
        }if (!generoRepository.existsByGenero(pelicula.getGenero())){
            return new ResponseEntity(new Mensaje("No existe genero"),HttpStatus.NOT_FOUND);
        }

        Pelicula nuevaPelicula = new Pelicula();
        nuevaPelicula.setTitulo(pelicula.getTitulo());
        nuevaPelicula.setDescripcion(pelicula.getDescripcion());
        nuevaPelicula.setFechaEstreno(pelicula.getFechaEstreno());
        nuevaPelicula.setVideo(pelicula.getVideo());
        nuevaPelicula.setPortada(pelicula.getPortada());

        Genero genero = generoRepository.findByGenero(pelicula.getGenero());
        nuevaPelicula.setGenero(genero);
        peliculaRepository.save(nuevaPelicula);
        PeliculaGetDTO dto = modelMapper.map(nuevaPelicula, PeliculaGetDTO.class);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<PeliculaGetDTO> update(Integer id, PeliculaSaveDTO pelicula) {
        boolean existe = peliculaRepository.existsById(id);

        if (!existe){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }

        Pelicula editarPelicula = peliculaRepository.findById(id).get();
        editarPelicula.setTitulo(pelicula.getTitulo());
        editarPelicula.setDescripcion(pelicula.getDescripcion());
        editarPelicula.setFechaEstreno(pelicula.getFechaEstreno());
        editarPelicula.setVideo(pelicula.getVideo());
        editarPelicula.setPortada(pelicula.getPortada());
        Genero genero = generoRepository.findByGenero(pelicula.getGenero());
        editarPelicula.setGenero(genero);
        peliculaRepository.save(editarPelicula);

        PeliculaGetDTO dto = modelMapper.map(editarPelicula, PeliculaGetDTO.class);
        return ResponseEntity.ok(dto);
    }



    @Override
    public ResponseEntity<PeliculaGetDTO> delete(Integer id) {
        boolean existe = peliculaRepository.existsById(id);

        if (!existe){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.BAD_REQUEST);
        }
        peliculaRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Se ha eliminado exitosamente"),HttpStatus.OK);
    }
}
