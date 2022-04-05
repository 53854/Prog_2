package main.exercise;

import java.util.*;

public class ShoppingList {

   Map<String, Integer> _items;

    /**
     * Creates a Shopping List based on Items names and amount of Items
     * @param items A Map of names and corresponding amounts
     */
   public ShoppingList(Map<String, Integer> items){
       _items = items;
   }

    /**
     * @return A List of sorted Map entries generated from shopping list items
     */
   public List<Map.Entry<String, Integer>> sort(){
        TreeMap<String, Integer> sorted = new TreeMap<>();
        sorted.putAll(_items);
        return sorted.entrySet().stream().toList();
   }
}
