package cu_a3;

import java.util.ArrayList;

public class Zug {
    private Lokomotive _Lok;
    private ArrayList<PersonenWagen> _Personenwagen = new ArrayList<PersonenWagen>();

    public Zug(Lokomotive lok){
        _Lok = lok;
    }

    public int getSitzplaetze(){
        int sp = 0;

        for (PersonenWagen pw : _Personenwagen) {
            sp += pw.get_sitzplaetze();
        }

        return sp;
    }

    public int getPleatze(){
        int p = 0;

        for (PersonenWagen pw : _Personenwagen) {
            p += pw.get_sitzplaetze();
        }

        return p;
    }

    public void addWagen(PersonenWagen p){
        if(_Personenwagen.size() > 0 && _Lok.get_ps() / _Personenwagen.size() >= 100) {
            _Personenwagen.add(p);
        }
    }

    public String toString(){

        return "Der Zug hat eine Lokomotive mit: " + _Lok.get_ps() + ",\n" +
                "der Zug hat insgesamt " + getPleatze() + " Plaetze,\n" +
                "davon sind " + getSitzplaetze() + " Sitzplaetze";
    }

}
