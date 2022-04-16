package main.composite.arithmeticexpression;

public class Operation implements Expression{

    Expression val_one;
    Expression val_two;

    public Operation(Expression val_one, Expression val_two){
        this.val_one = val_one;
        this.val_two = val_two;
    }

    @Override
    public long evaluate() {
        return 0;
    }
}
