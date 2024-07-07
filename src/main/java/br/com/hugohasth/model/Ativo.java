package br.com.hugohasth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name="TB_ATIVO")
@Data
public class Ativo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cd_ativo")
	@JsonProperty("_id")
	private Long id;

	@NotNull
	@NotBlank	
	@Size(min = 5, max = 100, message = "O nome do ativo deve ter entre 5 e 100 caracteres")
	@Column(name = "nm_ativo", nullable = false)
	private String nome;

	@NotNull
	@NotBlank
	@Column(name = "sg_ativo", nullable = false)
	private String sigla;

	@NotNull
	@NotBlank
	@Column(name = "tp_ativo", nullable = false)
	private String tipo;

	@NotNull
	@NotBlank
	@Column(name = "cd_segmento", nullable = true)
	private String segmento;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cd_setor", nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Setor setor;

}
