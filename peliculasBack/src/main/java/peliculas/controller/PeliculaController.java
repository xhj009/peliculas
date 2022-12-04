package peliculas.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peliculas.dto.PeliculaGetDTO;
import peliculas.dto.PeliculaSaveDTO;
import peliculas.service.PeliculaService;

import javax.validation.Valid;

@RestController

@RequestMapping("/api/peliculas")

public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public Page<PeliculaGetDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "true") boolean asc
    ){

        Page<PeliculaGetDTO> dto =  peliculaService.findAll(PageRequest.of(page,size, Sort.by(order)));

        if (!asc){
            dto =  peliculaService.findAll(PageRequest.of(page,size, Sort.by(order).descending()));
        }

        return dto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaGetDTO> findOneById(@PathVariable Integer id){
        return peliculaService.findOneById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<PeliculaGetDTO> save(@Valid @RequestBody PeliculaSaveDTO pelicula){
        return peliculaService.save(pelicula);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<PeliculaGetDTO> update(@PathVariable Integer id, @RequestBody @Valid PeliculaSaveDTO pelicula){
        return peliculaService.update(id,pelicula);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<PeliculaGetDTO> delete(@PathVariable Integer id){
        return peliculaService.delete(id);
    }
}
