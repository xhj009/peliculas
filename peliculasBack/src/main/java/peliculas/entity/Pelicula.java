package peliculas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "peliculas")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "El campo titulo es requerido")
    private String titulo;
    @NotEmpty(message = "El campo portada es requerido")
    private String portada;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "El campo fecha es requerido")
    private LocalDate fechaEstreno;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @NotEmpty(message = "El campo descripcion es requerido")
    private String descripcion;
    @NotEmpty(message = "El campo video es requerido")
    private String video;
    @NotNull(message = "El campo genero es requerido")
    @ManyToOne(targetEntity = Genero.class)
    private Genero genero;
}
