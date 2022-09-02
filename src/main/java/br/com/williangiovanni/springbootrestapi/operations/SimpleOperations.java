package br.com.williangiovanni.springbootrestapi.operations;

import br.com.williangiovanni.springbootrestapi.utils.NumberConverter;

public class SimpleOperations {
    public Double sum(String numberOne, String numberTwo) {

        return NumberConverter.convertDouble(numberOne) + NumberConverter.convertDouble(numberTwo);
    }

    public Double sub(String numberOne, String numberTwo) {

        return NumberConverter.convertDouble(numberOne) - NumberConverter.convertDouble(numberTwo);
    }

    public Double multi(String numberOne, String numberTwo) {

        return NumberConverter.convertDouble(numberOne) * NumberConverter.convertDouble(numberTwo);
    }

    public Double div(String numberOne,
            String numberTwo) {

        return NumberConverter.convertDouble(numberOne) / NumberConverter.convertDouble(numberTwo);
    }

    public Double med(String numberOne,
            String numberTwo) {

        return (NumberConverter.convertDouble(numberOne) + NumberConverter.convertDouble(numberTwo)) / 2;
    }

    public Double source(String number) {

        return Math.sqrt(NumberConverter.convertDouble(number));
    }
}
