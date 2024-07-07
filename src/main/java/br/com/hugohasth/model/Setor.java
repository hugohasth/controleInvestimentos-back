package br.com.hugohasth.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

	@NotNull
	@NotBlank
	@Column(name = "nm_setor", nullable = false)
	private String nome;

	@NotNull
	@DecimalMax(value = "100.0")
	@Column(name = "pc_setor")
	private Double porcentagem;

	@NotNull
	@Column(name = "vl_setor")
	private Double valor;
	
	@NotNull
	@NotEmpty
	@Valid
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "setor")
	private List<Ativo> ativos = new ArrayList<Ativo>();
}
