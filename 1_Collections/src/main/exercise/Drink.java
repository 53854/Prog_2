package main.exercise;

public class Drink implements Buyable, Comparable<Buyable>{

    int _price = 0;

    public Drink (int price){
        _price = price;
    }

    @Override
    public Integer fetchPrice() {
        return _price;
    }

    public int compareTo(Buyable other){
        return Integer.compare(this.fetchPrice(), other.fetchPrice());
    }
}
