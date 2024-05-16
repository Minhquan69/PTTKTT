import java.io.*; 
public class SimplePostfix { 
    public static void main(String[] args) throws IOException{ 
        System.out.println("Do Xuan Trang");
        String expression = "( 3 + 4 ) / ( 7 - 2 ) + ( 9 * 2 - 38 )";
        SimpleParser parse = new SimpleParser(expression); 
        parse.expr(); 
        String postfix =  parse.getPostfix();
        System.out.println("Bieu thuc hau to:" + " " + postfix);
        ComplexExpression suffixExp = new ComplexExpression(postfix);
        double result = suffixExp.interpret();
    System.out.println("Ket qua tinh la:" + " " + result);
    } 
}
