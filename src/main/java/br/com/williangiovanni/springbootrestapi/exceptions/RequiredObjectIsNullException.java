package br.com.williangiovanni.springbootrestapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UnsupportedMathOperationException
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RequiredObjectIsNullException(String ex) {
        super(ex);
    }

    public RequiredObjectIsNullException() {
        super("Não é possível armazenar um objeto vazio");
    }

}