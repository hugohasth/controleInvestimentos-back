package br.com.hugohasth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.hugohasth.dto.SetorDTO;
import br.com.hugohasth.dto.SetorPageDTO;
import br.com.hugohasth.service.SetorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequestMapping("/api/setores")
public class SetorController {

	private final SetorService setorService;
	
	public SetorController(SetorService setorService) {
		this.setorService = setorService;
	}
	
	@GetMapping
	public SetorPageDTO list(@RequestParam(defaultValue = "0") @PositiveOrZero int pageNumber, @RequestParam(defaultValue = "10") @Positive @Max(50) int pageSize) {
		return setorService.list(pageNumber, pageSize);
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
