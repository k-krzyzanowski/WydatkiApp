import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;

public class AppController {

    private Wykres wykres;
    private Pasek pasek;
    private AppView appView;
    private Wpis wpis;

    private DefaultTableModel tableModel;

    private String[] months = { "Styczeń","Luty","Marzec","Kwiecień","Maj","Czerwiec",
            "Lipiec","Sierpień","Wrzesień","Październik","Listopad","Grudzień"};
    private ArrayList<String> kategorie = new ArrayList<>();
    private ArrayList<Wpis> listaDane = new ArrayList<>();
    private ArrayList<Float> sumaWKat = new ArrayList<>();
    private float budzet=1000;
    private float wydane=0;
    private int idx=0;

    public AppController(Wykres wy, Pasek pa, AppView vi){
        wykres = wy;
        pasek = pa;
        appView = vi;

        pasek.setBudzet(budzet);
        String[] kat = {"Zakupy", "Restauracje", "Inne wydatki"};
        kategorie.addAll(Arrays.asList(kat));

        for(int i=0;i<kategorie.size();i++){
            sumaWKat.add(i,(float) 0);
        }

        wpis = new Wpis(0, "", "", "");
        listaDane.add(0,wpis);

    }

    public String[] getMonths() {
        return months;
    }

    public void addKat(String k){
        if(getKat().length>=6) return;
        kategorie.add(k);
        sumaWKat.add((float) 0);
        appView.addNewCategory(k);
        appView.addToStatusBar(Arrays.toString(getKat()));
    }

    public String[] getKat(){
        return kategorie.toArray(new String[0]);
    }

    public void addWpis(String wart, String opis, String data, String kat){

        if(isNotNum(wart)){
            appView.addToStatusBar("Brak lub nieprawidlowe dane");
            return;
        }
        float kwota =  Float.parseFloat(wart);

        wpis = new Wpis(kwota, opis, data, kat);
        listaDane.add(idx,wpis);
        tableModel.addRow(getRow(idx));

        addWydane(kwota);
        pasek.setProcent(ileWydane());

        addToKat(kategorie.indexOf(kat), kwota);
        appView.addToStatusBar(sumaWKat.toString());
        wykres.setWydane(ileWydane());
        wykres.setWykres(sumaWKat);

        appView.clrAddFields();
//        appView.clrStatusBar();
        idx++;

    }

    public DefaultTableModel setTableModel(){
        String[] columns = {"Data","Opis","Kategoria","Kwota"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columns);
        return tableModel;

    }

    public Object[] getRow(int idx){
        Object[] row = new Object[4];
        row[0] = listaDane.get(idx).getData();
        row[1] = listaDane.get(idx).getOpis();
        row[2] = listaDane.get(idx).getKategoria();
        row[3] = "- "+listaDane.get(idx).getWartosc()+" zł";
        return row;
    }

    public void setBudzet(String bud) {
        if(isNotNum(bud)){
            appView.addToStatusBar("Brak lub nieprawidlowe dane");
            return;
        }
        this.budzet = Float.parseFloat(bud);
        pasek.setBudzet(budzet);
        appView.clrStatusBar();
    }

    public void addWydane(float kwota) {
        this.wydane += kwota;
    }
    public float ileWydane() {
        return wydane;
    }

    public void addToKat(int idx,float kwota) {
        sumaWKat.set(idx,sumaWKat.get(idx)+kwota);
    }

    private boolean isNotNum(String s){
        if (s == null) {
            return true;
        }
        try {
            Float.parseFloat(s);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }


}
