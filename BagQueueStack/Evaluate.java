

import java.io.*;
public class Evaluate
{
 public static void main(String[] args) throws IOException
 {
     System.out.println("Do Xuan Trang ");
     System.setIn(new FileInputStream(new File("expression1.txt")));
     Stack<String> dauNgoac = new Stack<String>(); // Ngo?c
     Stack<Double> so = new Stack<Double>();        // S?
     while (!StdIn.isEmpty())
         { // Read token, push if operator.
             String s = StdIn.readString();
             if (s.equals("(")) ;
             else if (s.equals("+")) dauNgoac.push(s);
             else if (s.equals("-")) dauNgoac.push(s);
             else if (s.equals("*")) dauNgoac.push(s);
             else if (s.equals("/")) dauNgoac.push(s);
             else if(s.equals("sqrt")) dauNgoac.push(s);
             else if (s.equals(")"))
             { // Pop, evaluate, and push result if token is ")".
                 String layngoac = dauNgoac.pop();
                 double layso = so.pop();
                 if (layngoac.equals("+")) layso = so.pop() + layso;
                 else if (layngoac.equals("-")) layso = so.pop() - layso;
                 else if (layngoac.equals("*")) layso = so.pop() * layso;
                 else if (layngoac.equals("/")) layso = so.pop() / layso;
                 else if (layngoac.equals("sqrt"))layso = Math.sqrt(layso);
                 
                 // Bo sung phep khai can  ......    
                 
                so.push(layso);
             } // Token not operator or paren: push double value.
             else so.push(Double.parseDouble(s));
         }
         StdOut.println(so.pop());
     }
}
