package peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peliculas.entity.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula,Integer> {


}
