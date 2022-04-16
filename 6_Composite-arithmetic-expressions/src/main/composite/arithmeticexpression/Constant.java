package main.composite.arithmeticexpression;

public class Constant implements Expression{

    long val;

    public Constant (long val){
        this.val = val;
    }

    @Override
    public long evaluate() {
        return val;
    }
}
