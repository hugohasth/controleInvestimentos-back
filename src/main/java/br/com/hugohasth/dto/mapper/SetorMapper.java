package br.com.hugohasth.dto.mapper;

import org.springframework.stereotype.Component;

import br.com.hugohasth.dto.SetorDTO;
import br.com.hugohasth.model.Setor;

@Component
public class SetorMapper {
	
	public SetorDTO toDTO(Setor setor) {
		if(setor == null) return null;
		return new SetorDTO(setor.getId(), setor.getNome(), setor.getPorcentagem(), setor.getValor());
	}
	
	public Setor toEntity(SetorDTO setorDTO) {
		if(setorDTO == null) return null;
		
		Setor setor = new Setor();
		if(setorDTO.id() != null) setor.setId(setorDTO.id());
		setor.setNome(setorDTO.nome());
		setor.setPorcentagem(setorDTO.porcentagem());
		setor.setValor(setorDTO.valor());
		
		return setor;
		
	}

}
