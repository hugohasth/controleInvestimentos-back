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
import jakarta.validation.constraints.NotNull;
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
	
	@Column(name = "nm_ativo", nullable = false)
	@NotNull
	private String nome;
	
	@Column(name = "sg_ativo", nullable = false)
	@NotNull	
	private String sigla;
	
	@Column(name = "tp_ativo", nullable = false)
	@NotNull
	private String tipo;
	
	@Column(name = "cd_segmento", nullable = true)
	@NotNull
	private String segmento;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cd_setor", nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Setor setor;

}
