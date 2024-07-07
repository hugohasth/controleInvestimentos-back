package br.com.hugohasth.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SetorDTO(
		@JsonProperty("_id") Long id,
		@NotNull @NotBlank String nome,
		@NotNull @DecimalMax(value = "100.0") Double porcentagem,
		@NotNull Double valor,
		@NotNull @NotEmpty @Valid List<AtivoDTO> ativos) {

}
