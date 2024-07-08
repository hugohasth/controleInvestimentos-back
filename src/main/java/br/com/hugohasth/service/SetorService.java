package br.com.hugohasth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.hugohasth.dto.SetorDTO;
import br.com.hugohasth.dto.SetorPageDTO;
import br.com.hugohasth.dto.mapper.SetorMapper;
import br.com.hugohasth.exception.RegistroNaoEncontradoException;
import br.com.hugohasth.model.Setor;
import br.com.hugohasth.repository.SetorRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class SetorService {
	
	private final SetorRepository setorRepository;
	
	private final SetorMapper setorMapper;
	
	public SetorService(SetorRepository setorRepository, SetorMapper setorMapper) {
		this.setorRepository = setorRepository;
		this.setorMapper = setorMapper;
	}
	
	
	public SetorPageDTO list(int pageNumber, int pageSize) {
		Page<Setor> page = setorRepository.findAll(PageRequest.of(pageNumber, pageSize));
		List<SetorDTO> setores = page.get().map(setorMapper::toDTO).collect(Collectors.toList());
		return new SetorPageDTO(setores, page.getTotalElements(), page.getTotalPages());
	}
	

	public SetorDTO findById(@PathVariable @NotNull @Positive Long id) {
		return setorRepository.findById(id).map(setorMapper::toDTO).orElseThrow(() -> new RegistroNaoEncontradoException(id));
	}
	
	public SetorDTO create(@Valid @NotNull SetorDTO setorDTO) {
		return setorMapper.toDTO(setorRepository.save(setorMapper.toEntity(setorDTO)));
	}
	
	public SetorDTO update(@PathVariable @NotNull @Positive Long id, @Valid @NotNull SetorDTO setorDTO) {
		return setorRepository.findById(id)
				.map(setorEncontrado -> {
					Setor setor = setorMapper.toEntity(setorDTO);
					setorEncontrado.setNome(setorDTO.nome());
					setorEncontrado.setPorcentagem(setorDTO.porcentagem());
					setorEncontrado.setValor(setorDTO.valor());
					setorEncontrado.getAtivos().clear();
					setor.getAtivos().forEach(setorEncontrado.getAtivos()::add);
					return setorMapper.toDTO(setorRepository.save(setorEncontrado));
				}).orElseThrow(() -> new RegistroNaoEncontradoException(id));
	}
	

	public void delete(@PathVariable @NotNull @Positive Long id) {
		setorRepository.delete(setorRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(id)));
	}

}
