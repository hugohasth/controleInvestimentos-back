package br.com.hugohasth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="TB_SETOR")
@Data
public class Setor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cd_setor")
	@JsonProperty("_id")
	private Long id;
	
	@Column(name = "nm_setor", nullable = false)
	@NotNull
	private String nome;

	@NotNull
	@DecimalMax(value = "100.0")
	@Column(name = "pc_setor")
	private Double porcentagem;

	@NotNull
	@Column(name = "vl_setor")
	private Double valor;
}
