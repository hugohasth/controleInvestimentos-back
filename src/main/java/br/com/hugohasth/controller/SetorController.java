package br.com.hugohasth.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
				.map(setor -> ResponseEntity.ok().body(setor))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public Setor create(@RequestBody Setor setor) {
		return setorRepository.save(setor);
	}

}
