
public class  Divide implements Expression {
    
    private final Expression leftExpression;
    private final Expression rightExpression;

    public Divide(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
    
    @Override
    public double interpret() {
        if(rightExpression.interpret() != 0){
            return leftExpression.interpret() / rightExpression.interpret();
        }else return 0;
        
    }
}
