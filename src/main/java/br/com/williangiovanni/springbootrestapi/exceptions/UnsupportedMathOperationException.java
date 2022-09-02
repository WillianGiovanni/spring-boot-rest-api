package br.com.williangiovanni.springbootrestapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UnsupportedMathOperationException
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException {

    public UnsupportedMathOperationException(String ex) {
        super(ex);
    }

    private static final long serialVersionUID = 1L;
}