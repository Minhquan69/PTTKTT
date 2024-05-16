public class Chia implements Expression{
     private final Expression leftExpression;
    private final Expression rightExpression;

    public Chia(Expression leftExpression,Expression rightExpression ){
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }
    @Override
    public double interpret() {
        return leftExpression.interpret() / rightExpression.interpret();
    }
}