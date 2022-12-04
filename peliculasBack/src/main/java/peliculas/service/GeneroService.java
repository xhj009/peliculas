package peliculas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import peliculas.dto.GeneroDTO;
import peliculas.entity.Genero;

public interface GeneroService {

    Page<GeneroDTO> findAll(Pageable pageable);

    ResponseEntity<GeneroDTO> findById(Integer id);
    ResponseEntity<GeneroDTO> save(Genero genero);

    ResponseEntity<GeneroDTO> update(Integer id, Genero genero);

    ResponseEntity<Genero> delete(Integer id);
}
