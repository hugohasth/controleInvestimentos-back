package br.com.hugohasth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;

public record SetorDTO(
		@JsonProperty("_id") Long id,
		@NotNull String nome,
		@NotNull @DecimalMax(value = "100.0") Double porcentagem,
		@NotNull Double valor) {

}
