package br.com.douglas.aterrosystem.models;

import br.com.douglas.aterrosystem.entity.Combo;
import br.com.douglas.aterrosystem.entity.TipoDescarte;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComboResponse {
    private Long id;
    private String nome;
    private TipoDescarteResponse tipoDescarte;

    public void atribuirTipoDescarte(Combo entity) {
        this.tipoDescarte = TipoDescarteResponse.builder()
                .valor(entity.getTipoDescarte().getValor())
                .nome(entity.getTipoDescarte().getNome())
                .id(entity.getTipoDescarte().getId())
                .build();
    }
}
