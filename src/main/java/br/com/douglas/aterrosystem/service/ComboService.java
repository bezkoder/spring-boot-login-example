package br.com.douglas.aterrosystem.service;

import br.com.douglas.aterrosystem.entity.Combo;
import br.com.douglas.aterrosystem.entity.TipoDescarte;
import br.com.douglas.aterrosystem.exception.DomainException;
import br.com.douglas.aterrosystem.repository.ComboRepository;
import br.com.douglas.aterrosystem.repository.TipoDescarteRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ComboService{

    private final ComboRepository comboRepository;
    private final TipoDescarteRepository tipoDescarteRepository;

    public ComboService(ComboRepository comboRepository, TipoDescarteRepository tipoDescarteRepository) {
        this.comboRepository = comboRepository;
        this.tipoDescarteRepository = tipoDescarteRepository;
    }

    public Combo save(Combo combo) throws DomainException{
        validate(combo);
        Optional<TipoDescarte> optDescarte = tipoDescarteRepository.findById(combo.getTipoDescarte().getId());
        optDescarte.ifPresent(combo::setTipoDescarte);
        return this.comboRepository.save(combo);
    }

    private void validate(Combo combo) throws DomainException {
        if(Objects.isNull(combo.getNome()) || combo.getNome().isEmpty())
            throw new DomainException("Nome de combo é obrigatório");
    }

    public List<Combo> findAll(Sort sort){
        return comboRepository.findAll(sort);
    }
}
