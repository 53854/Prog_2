package main.composite.arithmeticexpression;

public class Addition extends Operation{

    public Addition(Expression val_one, Expression val_two) {
        super(val_one, val_two);
    }

    @Override
    public long evaluate() {
        return val_one.evaluate() + val_two.evaluate();
    }
}
