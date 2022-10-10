package br.com.douglas.aterrosystem.controller;

import br.com.douglas.aterrosystem.entity.TipoDescarte;
import br.com.douglas.aterrosystem.exception.DomainException;
import br.com.douglas.aterrosystem.models.TipoDescarteResponse;
import br.com.douglas.aterrosystem.service.TipoDescarteService;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tipo-descarte")
public class TipoDescarteController {

    private final TipoDescarteService entityService;


    public TipoDescarteController(TipoDescarteService tipoDescarteService) {
        this.entityService = tipoDescarteService;
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/all")
    public List<TipoDescarteResponse> findAll (
            @SortDefault.SortDefaults(
                    { @SortDefault(sort = "nome", direction = Sort.Direction.ASC) }
            ) Sort sort){
        List<TipoDescarteResponse> result = new ArrayList<>();
        entityService.findAll(sort).forEach(tipoDescarte -> result.add(convert(tipoDescarte)));
        return result;
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping
    public TipoDescarteResponse create(@Valid @RequestBody TipoDescarte entity) throws DomainException {
        return convert(entityService.save(entity));
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PutMapping
    public TipoDescarteResponse update(@Valid @RequestBody TipoDescarte entity) throws DomainException {
        return convert(entityService.update(entity));
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws DomainException {
        entityService.delete(id);
        return ResponseEntity.accepted().build();
    }

    private TipoDescarteResponse convert(TipoDescarte entity){
        return TipoDescarteResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .valor(entity.getValor())
                .build();
    }
}
