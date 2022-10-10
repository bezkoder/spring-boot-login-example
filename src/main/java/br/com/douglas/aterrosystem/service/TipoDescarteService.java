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
public class TipoDescarteService {

    private final TipoDescarteRepository tipoDescarteRepository;
    private final ComboRepository comboRepository;

    public TipoDescarteService(TipoDescarteRepository tipoDescarteRepository, ComboRepository comboRepository) {
        this.tipoDescarteRepository = tipoDescarteRepository;
        this.comboRepository = comboRepository;
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

    public TipoDescarte update(TipoDescarte entity) throws DomainException {
        Optional<TipoDescarte> optTipoDescarte = this.tipoDescarteRepository.findById(entity.getId());
        if (optTipoDescarte.isPresent()) {
            optTipoDescarte.get().setNome(entity.getNome());
            optTipoDescarte.get().setValor(entity.getValor());
            return tipoDescarteRepository.save(optTipoDescarte.get());
        }else {
            throw new DomainException(String.format("Tipo de descarte com id %s não encontrado", entity.getId()));
        }
    }

    public void delete(Long id) throws DomainException{
        Optional<TipoDescarte> optTipoDescarte = tipoDescarteRepository.findById(id);
        if(optTipoDescarte.isPresent()){
            TipoDescarte entity = optTipoDescarte.get();
            validaSePodeSerExcluido(entity);
            tipoDescarteRepository.delete(entity);
        }else {
            throw new DomainException(String.format("Tipo de descarte com id %s não encontrado", id));
        }
    }

    private void validaSePodeSerExcluido(TipoDescarte entity) throws DomainException {
        List<Combo> combos = comboRepository.findAllByTipoDescarte(entity);
        if (!combos.isEmpty()) {
            throw new DomainException("Não é possível excluir Tipo de descarte que possua combo relacionado.");
        }
    }
}
