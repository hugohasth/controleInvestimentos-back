package br.com.hugohasth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	public SetorService(SetorRepository setorRepository) {
		this.setorRepository = setorRepository;
	}
	
	
	public List<Setor> list() {
		return setorRepository.findAll();
	}
	

	public Setor findById(@PathVariable @NotNull @Positive Long id) {
		return setorRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(id));
	}
	
	public Setor create(@Valid Setor setor) {
		return setorRepository.save(setor);
	}
	
	public Setor update(@PathVariable @NotNull @Positive Long id, @Valid Setor setor) {
		return setorRepository.findById(id)
				.map(setorEncontrado -> {
					setorEncontrado.setNome(setor.getNome());
					setorEncontrado.setPorcentagem(setor.getPorcentagem());
					setorEncontrado.setValor(setor.getValor());
					return setorRepository.save(setorEncontrado);
				}).orElseThrow(() -> new RegistroNaoEncontradoException(id));
	}
	

	public void delete(@PathVariable @NotNull @Positive Long id) {
		setorRepository.delete(setorRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(id)));
	}

}
