package main.composite.arithmeticexpression;

public class Division extends Operation{


    public Division(Expression val_one, Expression val_two) {
        super(val_one, val_two);
    }

    @Override
    public long evaluate() {
        return val_one.evaluate() / val_two.evaluate();
    }
}
