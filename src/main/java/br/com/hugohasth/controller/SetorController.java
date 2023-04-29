package br.com.hugohasth.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
