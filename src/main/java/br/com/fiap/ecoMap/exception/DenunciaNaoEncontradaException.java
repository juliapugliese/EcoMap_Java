package br.com.fiap.ecoMap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DenunciaNaoEncontradaException extends RuntimeException {
    public DenunciaNaoEncontradaException(String message) {
        super(message);
    }
}
