package com.example.christian.mathcalculator;

/**
 * Created by Christian on 2015-09-28.
 */
public class Math {

    public String callMathOperation(String operation, double firstNum, double secondNum) {
        String answer = "";
        if (operation.equals("+")) {
            answer = add(firstNum, secondNum);
        } else if (operation.equals("-")) {
            answer = subtract(firstNum, secondNum);
        } else if (operation.equals("÷")) {
            answer = divide(firstNum, secondNum);
        } else if (operation.equals("×")) {
            answer = multiply(firstNum, secondNum);
        }
        return answer;
    }

    public String add(double a, double b) {
        double answer = a + b;
        return String.valueOf(answer);
    }// end add

    public String subtract(double a, double b) {
        double answer = a - b;
        return String.valueOf(answer);
    }// end subtract

    public String multiply(double a, double b) {
        double answer = a * b;
        return String.valueOf(answer);
    }// end add

    public String divide(double a, double b) {
        if (b == 0) {
            return "NaN";
        } else {
            double answer = a / b;
            return String.valueOf(answer);
        }
    }// end add

}
