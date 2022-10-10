package br.com.douglas.aterrosystem.controller;

import br.com.douglas.aterrosystem.entity.Combo;
import br.com.douglas.aterrosystem.exception.DomainException;
import br.com.douglas.aterrosystem.models.ComboResponse;
import br.com.douglas.aterrosystem.service.ComboService;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/combos")
public class CombroController {

    private final ComboService entityService;

    public CombroController(ComboService comboService) {
        this.entityService = comboService;
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/all")
    public List<ComboResponse> findAll (
            @SortDefault.SortDefaults(
                    { @SortDefault(sort = "nome", direction = Sort.Direction.ASC) }
            ) Sort sort){
        List<ComboResponse> result = new ArrayList<>();
        entityService.findAll(sort).forEach(entity -> result.add(convert(entity)));
        return result;
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping
    public ComboResponse create(@Valid @RequestBody Combo entity) throws DomainException {
        return convert(entityService.save(entity));
    }

    private ComboResponse convert(Combo entity){
        ComboResponse response = ComboResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .build();
        response.atribuirTipoDescarte(entity);
        return response;
    }
}
