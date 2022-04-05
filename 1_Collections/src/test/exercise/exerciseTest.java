package test.exercise;

import main.exercise.Buyable;
import main.exercise.Car;
import main.exercise.Drink;
import main.exercise.ShoppingList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class exerciseTest {

    @Test
    public void buyablesTest(){

        final var buyableList = new ArrayList<Buyable>(){{
            add(new Car(1000));
            add(new Car(2000));
            add(new Car(3000));
            add(new Drink(5));
            add(new Drink(10));
        }};
        final var expectedResult = new ArrayList<Integer>(){{
            add(5);
            add(10);
            add(1000);
            add(2000);
            add(3000);
        }};


        final var actualResult = new ArrayList<Integer>(){{
            for (Buyable i: buyableList.stream().sorted().toList()) {
                add(i.fetchPrice());
            }
        }};

        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void shoppingListTest(){

        final var testShoppingList = new ShoppingList(Map.of(
                "Tomato", 1,
                "Onion", 2,
                "Banana", 3,
                "Dank", 4,
                "Apple", 7,
                "Energy Drink", 5
                ));

        final var expectedResult = List.of(
                Map.entry("Apple", 7),
                Map.entry("Banana", 3),
                Map.entry("Dank", 4),
                Map.entry("Energy Drink", 5),
                Map.entry("Onion", 2),
                Map.entry("Tomato", 1)
                );

        final var actualResult = testShoppingList.sort();

        assertEquals(expectedResult,actualResult);
    }
}
