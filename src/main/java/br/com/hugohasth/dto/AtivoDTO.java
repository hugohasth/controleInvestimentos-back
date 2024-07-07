package br.com.hugohasth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AtivoDTO(@JsonProperty("_id") Long id,
		@NotNull @NotBlank @Size(min = 5, max = 100, message = "O nome do ativo deve ter entre 5 e 100 caracteres") String nome,
		@NotNull @NotBlank String sigla,
		@NotNull @NotBlank String tipo,
		@NotNull @NotBlank String segmento) {

}
