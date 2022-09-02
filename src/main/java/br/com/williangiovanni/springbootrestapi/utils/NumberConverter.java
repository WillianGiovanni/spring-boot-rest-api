package br.com.williangiovanni.springbootrestapi.utils;

public class NumberConverter {

    public static Double convertDouble(String strNumber) {
        if (strNumber == null) {
            return 0D;
        }
        String number = strNumber.replaceAll(",", ".");
        if (CheckNumber.isNumeric(number)) {
            return Double.parseDouble(number);
        }
        return 0D;
    }
}
