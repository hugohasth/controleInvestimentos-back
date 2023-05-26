package br.com.hugohasth.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.hugohasth.model.Setor;
import br.com.hugohasth.repository.SetorRepository;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/setores")
@AllArgsConstructor
public class SetorController {
	
	private final SetorRepository setorRepository;
	
	@GetMapping
	public List<Setor> list() {
		return setorRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Setor> findById(@PathVariable Long id) {
		return setorRepository.findById(id)
				.map(setorEncontrado -> ResponseEntity.ok().body(setorEncontrado))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public Setor create(@RequestBody Setor setor) {
		return setorRepository.save(setor);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Setor> update(@PathVariable Long id, @RequestBody Setor setor) {
		return setorRepository.findById(id)
				.map(setorEncontrado -> {
					setorEncontrado.setNome(setor.getNome());
					setorEncontrado.setPorcentagem(setor.getPorcentagem());
					setorEncontrado.setValor(setor.getValor());
					Setor setorAtualizado = setorRepository.save(setorEncontrado);
					return ResponseEntity.ok().body(setorAtualizado);
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		return setorRepository.findById(id)
				.map(setorEncontrado -> {
					setorRepository.deleteById(id);
					return ResponseEntity.noContent().<Void>build();
				})
				.orElse(ResponseEntity.notFound().build());
	}

}
