package peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peliculas.entity.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero,Integer> {
   boolean existsGeneroById(Integer id);

   @Query(value = "select count(genero) from generos inner join peliculas on peliculas.genero_id = generos.id where generos.id = :id",nativeQuery = true)
   int generoRepetido(Integer id);
}
