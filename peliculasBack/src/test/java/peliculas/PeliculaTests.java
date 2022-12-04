package peliculas;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import peliculas.entity.Genero;
import peliculas.entity.Pelicula;
import peliculas.repository.PeliculaRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PeliculaTests {
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    @DisplayName("Test Get All")
    public void getAllTest(){
        List<Pelicula> peliculas = peliculaRepository.findAll();
        assertThat(peliculas).size().isGreaterThan(0);
    }

    @Test
    @DisplayName("Test find by id")
    public void findOneByIdTest(){
        Pelicula pelicula  = peliculaRepository.findById(6).get();
        assertThat(pelicula.getTitulo(),equalTo("update"));
    }

    @Test
    @DisplayName("Test save ")
    public void saveTest(){
        Genero genero = new Genero();
        genero.setId(1);

        Pelicula pelicula = new Pelicula();
        pelicula.setId(9);
        pelicula.setTitulo("probando httpstatus");
        pelicula.setDescripcion("test22");
        pelicula.setFechaEstreno(LocalDate.parse("2020-10-10"));
        pelicula.setPortada("test.png");
        pelicula.setGenero(genero);
        peliculaRepository.save(pelicula);

        assertNotNull(pelicula);

    }

    @Test
    @DisplayName("Test update")
    public void updateTest(){
        Pelicula pelicula = peliculaRepository.findById(6).get();
        pelicula.setTitulo("update");
        peliculaRepository.save(pelicula);

        assertThat(pelicula.getTitulo(),equalTo("update"));
    }

    @Test
    @DisplayName("Test delete by id")
    public void deleteTest(){
        peliculaRepository.deleteById(5);
        assertFalse(peliculaRepository.existsById(5));

    }


}
