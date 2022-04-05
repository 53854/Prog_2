package cu_a3;

public class PersonenWagen {
    private int _sitzplaetze = 0;
    private int _stehplaetze = 0;

    public PersonenWagen(int sitzplaetze, int stehplaetze){
        _sitzplaetze = sitzplaetze;
        _stehplaetze = stehplaetze;
    }

    public int get_sitzplaetze(){
        return _sitzplaetze;
    }

    public int get_stehplaetze(){
        return _stehplaetze;
    }
}
