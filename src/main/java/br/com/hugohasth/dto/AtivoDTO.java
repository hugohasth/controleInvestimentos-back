package br.com.hugohasth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AtivoDTO(@JsonProperty("_id") Long id,
		String nome,
		String sigla,
		String tipo,
		String segmento) {

}
