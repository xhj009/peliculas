package peliculas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import peliculas.dto.PeliculaGetDTO;
import peliculas.dto.PeliculaSaveDTO;

public interface PeliculaService {

    Page<PeliculaGetDTO> findAll(Pageable pageable);

    ResponseEntity<PeliculaGetDTO> findOneById(Integer id);

    ResponseEntity<PeliculaGetDTO> save(PeliculaSaveDTO pelicula);

    ResponseEntity<PeliculaGetDTO> update(Integer id, PeliculaSaveDTO pelicula);

    ResponseEntity<PeliculaGetDTO> delete(Integer id);
}
