package br.com.hugohasth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.hugohasth.exception.RegistroNaoEncontradoException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(RegistroNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFoundException(RegistroNaoEncontradoException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return e.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + " " + error.getDefaultMessage())
				.reduce("", (acc, error) -> acc + error + "\n");
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleConstraintViolationException(ConstraintViolationException e) {
		return e.getConstraintViolations().stream()
				.map(error -> error.getPropertyPath() + " " + error.getMessage())
				.reduce("", (acc, error) -> acc + error + "\n");
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		if (e != null && e.getRequiredType() != null) {
			String typeName = e.getRequiredType().getName().split("\\.")[e.getRequiredType().getName().split("\\.").length - 1];
			return e.getName() + " precisa ser do tipo " + typeName;
		}
		return "Tipo de argumento não válido.";
	}
	
}
