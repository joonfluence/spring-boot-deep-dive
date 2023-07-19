package jdk;

public class Calculation {
    public static void main(String[] args) {
        Calculation calculation = new Calculation();
        calculation.sum(3);
    }

    private int sum(int param){
        int localVariable = 1;
        int sum = localVariable + param;
        print(sum);
        return sum;
    }

    private void print(int param){
        System.out.println("result = " + param);
    }
}