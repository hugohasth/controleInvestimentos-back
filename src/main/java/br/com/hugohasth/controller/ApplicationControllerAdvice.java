package br.com.hugohasth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.hugohasth.exception.RegistroNaoEncontradoException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(RegistroNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFoundException(RegistroNaoEncontradoException e) {
		return e.getMessage();
	}

}
