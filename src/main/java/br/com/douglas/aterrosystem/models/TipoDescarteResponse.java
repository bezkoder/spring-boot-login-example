package br.com.douglas.aterrosystem.models;

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
public class TipoDescarteResponse {

    private Long id;
    private String nome;
    private Double valor;
}
