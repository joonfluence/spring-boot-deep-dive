package jdk;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface ArithmeticOperations {
    int apply(int x, int y);
}

public class JDK8 {
    public static void main(String[] args){
        // Functional Interface
        ArithmeticOperations addition = (x, y) -> x + y;
        ArithmeticOperations subtraction = (x, y) -> x - y;
        ArithmeticOperations multiplication = (x, y) -> x * y;
        ArithmeticOperations division = (x, y) -> x / y;

        int plusResult = addition.apply(3, 4);
        int minusResult = subtraction.apply(3, 4);
        int multiplyResult = multiplication.apply(3, 4);
        int divisionResult = division.apply(4, 2);

        System.out.println("plusResult = " + plusResult);
        System.out.println("minusResult = " + minusResult);
        System.out.println("multiplyResult = " + multiplyResult);
        System.out.println("divisionResult = " + divisionResult);

        // Stream API
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
    }
}
