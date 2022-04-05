package cu_a6;

import java.util.Scanner;

public class Main {

    static enum wuerfel{eins,zwei,drei,vier,fuenf,sechs}

    public static void main(String[] args) {
        wuerfeln();
    }

    static void wuerfeln(){
        Scanner sc = new Scanner(System.in);
        int roll = (int) (Math.random() * wuerfel.values().length);

        System.out.println("Your roll is: " + wuerfel.values()[roll]);

        System.out.println("Again? (y | n )");
        if (sc.nextLine().equals("y")){

            wuerfeln();
        }

    }
}
