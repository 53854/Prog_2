package cu_a4;

public class Main {

    public static void main(String[] args) {
        Trecker trecker = new Trecker();
        abfahrt(trecker);
    }

    static void abfahrt(iMotorisiert m){
        m.fahren();
    }

}
