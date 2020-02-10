import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AppView {

    private AppController appCtrl;
    private JTextArea statusBar;
    private JTextField cost;
    private JTextField date;
    private JTextField description;
    private JComboBox<String> category;

    private Wykres wykres;
    private Pasek pasek;

    public AppView(){
        wykres = new Wykres();
        pasek = new Pasek();
        appCtrl = new AppController(wykres,pasek,this);
    }

    public Container createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,5,10));

        panel.add(dataPanel(),BorderLayout.NORTH);
        panel.add(viewPanel(),BorderLayout.CENTER);
        panel.add(statusBar = new JTextArea(),BorderLayout.SOUTH);
        statusBar.setEditable(false);

        return panel;
    }

    public Container dataPanel() {
        JPanel panel = new JPanel(new GridLayout(1,2,50,10));

        panel.add(addPanel());
        panel.add(menuPanel());

        return panel;
    }

    public Container viewPanel() {
        JPanel panel = new JPanel(new GridLayout(1,2,50,10));
        JPanel subPanel = new JPanel(new BorderLayout(10,10));

        DefaultTableModel tableModel = appCtrl.setTableModel();
        JTable table = new JTable(tableModel);
        table.setEnabled(false);

        subPanel.add(pasek,BorderLayout.NORTH);
        subPanel.add(new JScrollPane(table),BorderLayout.CENTER);

        panel.add(wykres,0);
        panel.add(subPanel,1);

        return panel;
    }

    public Container addPanel(){

        JPanel panel = new JPanel(new BorderLayout(10,5));
        JPanel textFld = new JPanel(new GridLayout(4,2,10,2));

        JButton addButton = new JButton("Dodaj");
        addButton.setPreferredSize(new Dimension(100, 50));
        cost = new JTextField();
        date = new JTextField();
        description = new JTextField();
        category = new JComboBox<>(appCtrl.getKat());

        textFld.add(new JLabel("Kwota"));
        textFld.add(new JLabel("Data"));
        textFld.add(cost);
        textFld.add(date);
        textFld.add(new JLabel("Opis"));
        textFld.add(new JLabel("Kategoria"));
        textFld.add(description);
        textFld.add(category);

        panel.add(textFld,BorderLayout.CENTER);
        panel.add(addButton,BorderLayout.EAST);

        addButton.addActionListener(actionEvent -> {
            appCtrl.addWpis(cost.getText(), description.getText(), date.getText(), (String)category.getSelectedItem());

            wykres.repaint();
            pasek.repaint();
        });

        return panel;
    }

    public Container menuPanel(){
        JPanel panel = new JPanel(new GridLayout(0,2,10,5));

        JComboBox<String> monyhList = new JComboBox<>(appCtrl.getMonths());
        monyhList.setSelectedIndex(1);
        panel.add(new JLabel("Miesiac"),0);
        panel.add(monyhList,1);

        JButton addLimit = new JButton("Ustal limit");
        JButton addKat = new JButton("Dodaj kategorie");
        JTextField limit = new JTextField("Limit");
        JTextField kategoria = new JTextField("Kategoria");

        panel.add(limit);
        panel.add(addLimit);
        panel.add(kategoria);
        panel.add(addKat);

        addLimit.addActionListener(actionEvent -> {
            appCtrl.setBudzet(limit.getText());
            pasek.repaint();
        });

        addKat.addActionListener(actionEvent -> appCtrl.addKat(kategoria.getText()));

        return panel;
    }

//    public

    public void clrAddFields(){
        cost.setText(null);
        cost.requestFocusInWindow();
        description.setText(null);
        date.setText(null);
    }
    public void addToStatusBar(String s){
        statusBar.setText(s);
    }
    public void addNewCategory(String s){
        category.addItem(s);
    }
    public void clrStatusBar(){
        statusBar.setText(null);
    }

}


//https://www.tutorialspoint.com/swing/swing_containers.htm
//https://docs.oracle.com/javase/tutorial/uiswing/TOC.html