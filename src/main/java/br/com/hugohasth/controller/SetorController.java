package br.com.hugohasth.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.hugohasth.dto.SetorDTO;
import br.com.hugohasth.model.Setor;
import br.com.hugohasth.service.SetorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/setores")
public class SetorController {

	private final SetorService setorService;
	
	public SetorController(SetorService setorService) {
		this.setorService = setorService;
	}
	
	@GetMapping
	public List<SetorDTO> list() {
		return setorService.list();
	}
	
	@GetMapping("/{id}")
	public SetorDTO findById(@PathVariable @NotNull @Positive Long id) {
		return setorService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public SetorDTO create(@RequestBody @Valid @NotNull SetorDTO setor) {
		return setorService.create(setor);
	}
	
	@PutMapping("/{id}")
	public SetorDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull SetorDTO setor) {
		return setorService.update(id, setor);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void delete(@PathVariable @NotNull @Positive Long id) {
		setorService.delete(id);
	}

}
