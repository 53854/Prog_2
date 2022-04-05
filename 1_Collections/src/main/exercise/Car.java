package main.exercise;


public class Car implements Buyable, Comparable<Buyable>{

    private int _price = 0;

    public Car(int price){
        _price = price;
    }

    @Override
    public Integer fetchPrice() {
        return _price;
    }

    @Override
    public int compareTo(Buyable other){
        return Integer.compare(this.fetchPrice(), other.fetchPrice());
    }
}
