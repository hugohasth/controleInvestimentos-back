package br.com.hugohasth.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.hugohasth.dto.AtivoDTO;
import br.com.hugohasth.dto.SetorDTO;
import br.com.hugohasth.model.Ativo;
import br.com.hugohasth.model.Setor;

@Component
public class SetorMapper {
	
	public SetorDTO toDTO(Setor setor) {
		if(setor == null) return null;
		List<AtivoDTO> ativos = setor.getAtivos()
				.stream()
				.map(ativo -> new AtivoDTO(ativo.getId(), ativo.getNome(), ativo.getSigla(), ativo.getTipo(), ativo.getSegmento()))
				.collect(Collectors.toList());
		return new SetorDTO(setor.getId(), setor.getNome(), setor.getPorcentagem(), setor.getValor(), ativos);
	}
	
	public Setor toEntity(SetorDTO setorDTO) {
		if(setorDTO == null) return null;
		
		Setor setor = new Setor();
		if(setorDTO.id() != null) setor.setId(setorDTO.id());
		setor.setNome(setorDTO.nome());
		setor.setPorcentagem(setorDTO.porcentagem());
		setor.setValor(setorDTO.valor());
		
		List<Ativo> ativos = setorDTO.ativos().stream().map(ativoDTO -> {
			var ativo = new Ativo();
			ativo.setId(ativoDTO.id());
			ativo.setNome(ativoDTO.nome());
			ativo.setSigla(ativoDTO.sigla());
			ativo.setTipo(ativoDTO.tipo());
			ativo.setSegmento(ativoDTO.segmento());
			ativo.setSetor(setor);
			return ativo;
		}).collect(Collectors.toList());
		
		setor.setAtivos(ativos);
		
		return setor;
		
	}

}
