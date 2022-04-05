package main.exercise;

public interface Buyable {
    /**
     * returns the price of the buyable object
     */
    public default Integer fetchPrice() {
        return null;
    }
}
