package main.composite.arithmeticexpression;

import java.util.ArrayList;

public class Operation implements Expression{

    Expression val_one;
    Expression val_two;

    public Operation(Expression val_one, Expression val_two){
        this.val_one = val_one;
        this.val_two = val_two;
    }

    public ArrayList<Expression> getExpressions(){
        ArrayList<Expression> expressions = new ArrayList<Expression>();
        expressions.add(val_one);
        expressions.add(val_two);
        return expressions;
    }


    @Override
    public long evaluate() {
        return 0;
    }

}
