import java.util.Stack;

public class TestComplexExpression {

    public static void main(String[] args) {

        String tokenString = "7 3 + 7.2 /";
        ComplexExpression suffixExp = new ComplexExpression(tokenString);
        double result = suffixExp.interpret();
        System.out.println("Ket qua tinh " +tokenString + " la: " + result);
        }
}