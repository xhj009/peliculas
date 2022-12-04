package peliculas.dto;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PeliculaSaveDTO {
    private Integer id;
    @NotNull(message = "El campo titulo es requerido")
    private String titulo;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @NotNull(message = "El campo descripcion es requerido")
    private String descripcion;
    @NotNull(message = "El campo portada es requerido")
    private String portada;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "El campo fecha de estreno es requerido")
    private LocalDate fechaEstreno;
    @NotNull(message = "El campo video es requerido")
    private String video;
    @NotNull(message = "El campo genero es requerido")
    private Integer genero;

}
