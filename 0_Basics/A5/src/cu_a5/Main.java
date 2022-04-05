package cu_a5;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        int plaetze[][] = new int[5][10];

        int r = 0;
        int p = 0;

        StringBuilder output = new StringBuilder();

        Scanner sc = new Scanner(System.in);
        System.out.println("In welcher Reihe moechten sie sitzen? (1 - 5)");
        r = sc.nextInt();
        System.out.println("Welchen Platz möchten sie buchen? (1 - 10)");
        p = sc.nextInt();

        for(int y = 0; y < plaetze.length; y++) {
            for (int x = 0; x < plaetze[y].length; x++) {
                if (y == r-1 && x == p-1) output.append("1 ");
                else output.append("0 ");
            }
            output.append("\n");
        }

        System.out.println(output);
        sc.close();
    }
}
