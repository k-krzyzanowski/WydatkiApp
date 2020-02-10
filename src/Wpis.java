public class Wpis {

    private float wartosc;
    private String opis;
    private String data;
    private String kategoria;

    public Wpis(float wartosc, String opis, String data, String kategoria) {
        this.wartosc = wartosc;
        this.opis = opis;
        this.data = data;
        this.kategoria = kategoria;
    }

    public float getWartosc() {
        return wartosc;
    }

    public String getOpis() {
        return opis;
    }

    public String getData() {
        return data;
    }

    public String getKategoria() {
        return kategoria;
    }

    @Override
    public String toString() {
        return  wartosc +" zł, "+ kategoria + ", " + opis +", " + data;
    }
}
