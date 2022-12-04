package peliculas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "generos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Genero implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "El campo genero es requerido")
    private String genero;
    @OneToMany(targetEntity = Pelicula.class)
    private List<Pelicula> peliculas;


}
