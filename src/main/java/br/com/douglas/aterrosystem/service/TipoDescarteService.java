package br.com.douglas.aterrosystem.service;

import br.com.douglas.aterrosystem.entity.Combo;
import br.com.douglas.aterrosystem.entity.TipoDescarte;
import br.com.douglas.aterrosystem.exception.DomainException;
import br.com.douglas.aterrosystem.repository.TipoDescarteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TipoDescarteService {

    private final TipoDescarteRepository tipoDescarteRepository;

    public TipoDescarteService(TipoDescarteRepository tipoDescarteRepository) {
        this.tipoDescarteRepository = tipoDescarteRepository;
    }

    public TipoDescarte save(TipoDescarte entity) throws DomainException{
        validate(entity);
        return this.tipoDescarteRepository.save(entity);
    }

    private void validate(TipoDescarte entity) throws DomainException {
        if(Objects.isNull(entity.getValor()))
            throw new DomainException("Valor obrigatório");
    }

    public List<TipoDescarte> findAll(Sort sort){
        return tipoDescarteRepository.findAll(sort);
    }
}
