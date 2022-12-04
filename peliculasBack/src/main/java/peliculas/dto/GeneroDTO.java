package peliculas.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GeneroDTO {
    private Integer id;
    @NotEmpty(message = "El campo genero es requerido")
    private String genero;
}
