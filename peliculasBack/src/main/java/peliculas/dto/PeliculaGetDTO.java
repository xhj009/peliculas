package peliculas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PeliculaGetDTO {
    private Integer id;
    @NotNull(message = "El campo titulo es requerido")
    private String titulo;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @NotNull(message = "El campo descripcion es requerido")
    private String descripcion;
    @NotNull
    private String portada;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "El el campo fecha de estreno es requerido")
    private LocalDate fechaEstreno;
    @NotNull(message = "El campo video es requerido")
    private String video;
    @NotNull(message = "El campo genero es requerido")
    private String genero;

/*
    public PeliculaGetDTO(Pelicula pelicula){
        this.id = pelicula.getId();
        this.titulo = pelicula.getTitulo();
        this.descripcion = pelicula.getDescripcion();
        this.portada = pelicula.getPortada();
        this.fechaEstreno = pelicula.getFechaEstreno();
        this.genero = String.valueOf(pelicula.getGenero().getGenero());
    }

 */

}
