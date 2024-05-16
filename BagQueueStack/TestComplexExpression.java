import java.util.Stack;

public class TestComplexExpression {

    public static void main(String[] args) {
        System.out.println("Do Xuan Trang ");
        //String tokenString = "8 4 2 / +";
        String tokenString = "8.2 4.1 2.05 / +";
        ComplexExpression suffixExp = new ComplexExpression(tokenString);
        double result = suffixExp.interpret();
        System.out.println("Ket qua tinh " +tokenString + " la: " + result);
        }
}