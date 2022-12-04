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
import peliculas.dto.GeneroDTO;
import peliculas.entity.Genero;
import peliculas.service.GeneroService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {
    @Autowired
    private GeneroService generoService;

    @GetMapping("")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public Page<GeneroDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "true") boolean asc
    ){
        Page<GeneroDTO> generoDTO = generoService.findAll(PageRequest.of(page,size, Sort.by(order)));
        if (!asc) {
            generoDTO = generoService.findAll(PageRequest.of(page,size,Sort.by(order).descending()));
        }
        return generoDTO;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<GeneroDTO> findById(@PathVariable Integer id){
        return generoService.findById(id);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<GeneroDTO> save(@Valid @RequestBody Genero genero){
        return generoService.save(genero);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<GeneroDTO> update(@PathVariable Integer id, @Valid @RequestBody Genero genero){
        return generoService.update(id,genero);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Genero> delete(@PathVariable Integer id){
        return generoService.delete(id);
    }
}
