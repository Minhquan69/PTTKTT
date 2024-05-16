 

public class Number implements Expression{

    private final double n;
    
    public Number(double n){
        this.n = n;
    }
    @Override
    public double interpret() {
        return n;
    }

}
