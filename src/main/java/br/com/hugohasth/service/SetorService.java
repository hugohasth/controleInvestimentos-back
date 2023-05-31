package br.com.hugohasth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.hugohasth.dto.SetorDTO;
import br.com.hugohasth.dto.mapper.SetorMapper;
import br.com.hugohasth.exception.RegistroNaoEncontradoException;
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
	
	
	public List<SetorDTO> list() {
		return setorRepository
				.findAll()
				.stream()
				.map(setorMapper::toDTO)
				.collect(Collectors.toList());
	}
	

	public SetorDTO findById(@PathVariable @NotNull @Positive Long id) {
		return setorRepository.findById(id).map(setorMapper::toDTO).orElseThrow(() -> new RegistroNaoEncontradoException(id));
	}
	
	public SetorDTO create(@Valid @NotNull SetorDTO setor) {
		return setorMapper.toDTO(setorRepository.save(setorMapper.toEntity(setor)));
	}
	
	public SetorDTO update(@PathVariable @NotNull @Positive Long id, @Valid @NotNull SetorDTO setor) {
		return setorRepository.findById(id)
				.map(setorEncontrado -> {
					setorEncontrado.setNome(setor.nome());
					setorEncontrado.setPorcentagem(setor.porcentagem());
					setorEncontrado.setValor(setor.valor());
					return setorMapper.toDTO(setorRepository.save(setorEncontrado));
				}).orElseThrow(() -> new RegistroNaoEncontradoException(id));
	}
	

	public void delete(@PathVariable @NotNull @Positive Long id) {
		setorRepository.delete(setorRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(id)));
	}

}
