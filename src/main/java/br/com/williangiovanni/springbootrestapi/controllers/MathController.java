package br.com.williangiovanni.springbootrestapi.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.williangiovanni.springbootrestapi.exceptions.UnsupportedMathOperationException;
import br.com.williangiovanni.springbootrestapi.operations.SimpleOperations;
import br.com.williangiovanni.springbootrestapi.utils.CheckNumber;

@RestController
public class MathController {

    private final AtomicLong counter = new AtomicLong();

    private SimpleOperations simpleOperations = new SimpleOperations();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!CheckNumber.isNumeric(numberOne) || !CheckNumber.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return simpleOperations.sum(numberOne, numberTwo);
    }

    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sub(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!CheckNumber.isNumeric(numberOne) || !CheckNumber.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return simpleOperations.sub(numberOne, numberTwo);
    }

    @RequestMapping(value = "/multi/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multi(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!CheckNumber.isNumeric(numberOne) || !CheckNumber.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return simpleOperations.multi(numberOne, numberTwo);
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double div(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!CheckNumber.isNumeric(numberOne) || !CheckNumber.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return simpleOperations.div(numberOne, numberTwo);
    }

    @RequestMapping(value = "/med/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double med(@PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo) throws Exception {

        if (!CheckNumber.isNumeric(numberOne) || !CheckNumber.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return simpleOperations.med(numberOne, numberTwo);
    }

    @RequestMapping(value = "/source/{number}", method = RequestMethod.GET)
    public Double source(@PathVariable(value = "number") String number) throws Exception {

        if (!CheckNumber.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return simpleOperations.source(number);
    }

}
