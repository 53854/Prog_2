package cu_a3;

public class SchenllZugWagen extends PersonenWagen{

    private int _ersteKlassePlaetze = 0;

    public SchenllZugWagen(int sitzplaetze, int stehplaetze, int ersteKlassePlaetze){
        super(sitzplaetze, stehplaetze);
        _ersteKlassePlaetze = ersteKlassePlaetze;
    }

    public int get_sitzplartze(){
        return super.get_sitzplaetze() + _ersteKlassePlaetze;
    }

    public int get_plaetze(){
        return get_sitzplaetze() + _ersteKlassePlaetze;
    }
}
