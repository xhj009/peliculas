package peliculas.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import peliculas.dto.GeneroDTO;
import peliculas.entity.Genero;
import peliculas.entity.Mensaje;
import peliculas.repository.GeneroRepository;
import peliculas.service.GeneroService;

@Service
public class GeneroServiceImpl implements GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<GeneroDTO> findAll(Pageable pageable){
        Page<Genero> genero = generoRepository.findAll(pageable);
        return genero.map(generoDTO -> modelMapper.map(generoDTO,GeneroDTO.class));
    }

    public ResponseEntity<GeneroDTO> findById(Integer id){
        boolean existe = generoRepository.existsById(id);
        Genero genero = generoRepository.getReferenceById(id);

        if (!existe){
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }

        GeneroDTO generoDTO = modelMapper.map(genero,GeneroDTO.class);
        return ResponseEntity.ok(generoDTO);
    }

    @Override
    public ResponseEntity<GeneroDTO> save(Genero genero) {
        if (genero.getId() != null){
            return ResponseEntity.badRequest().build();
        }

        Genero nuevoGenero = new Genero();
        nuevoGenero.setGenero(genero.getGenero());
        generoRepository.save(nuevoGenero);

        GeneroDTO generoDTO = modelMapper.map(nuevoGenero,GeneroDTO.class);
        return ResponseEntity.ok(generoDTO);
    }

    @Override
    public ResponseEntity<GeneroDTO> update(Integer id, Genero genero) {
        boolean existe = generoRepository.existsById(id);

        if (!existe) {
            return ResponseEntity.badRequest().build();
        }

        Genero editarGenero = generoRepository.findById(id).get();
        editarGenero.setGenero(genero.getGenero());
        generoRepository.save(editarGenero);

        GeneroDTO generoDTO = modelMapper.map(editarGenero,GeneroDTO.class);
        return ResponseEntity.ok(generoDTO);
    }

    @Override
    public ResponseEntity<Genero> delete(Integer id) {
        boolean existe = generoRepository.existsById(id);
        int empty = generoRepository.generoRepetido(id);

        if (!existe){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.BAD_REQUEST);
        }if (empty > 0){
            return new ResponseEntity(new Mensaje("No puedes borrar generos relacionadas"), HttpStatus.BAD_REQUEST);
        }
        generoRepository.deleteById(id);
        return new ResponseEntity(new Mensaje("Se ha eliminado exitosamente"),HttpStatus.OK);

    }
}
