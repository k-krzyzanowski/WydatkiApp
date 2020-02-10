import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Wykres extends JComponent {

    private float wydane;
    private ArrayList<Float> ileWKat = new ArrayList<>();
    private int ileKat;
    private int[] proc;
    private int[] angle;

    public void setWydane(float wydane) {
        this.wydane = wydane;
    }

    public void setWykres(ArrayList<Float> sumaWKat) {
        ileWKat.addAll(sumaWKat);
        ileKat = 0;
        Collections.sort(ileWKat);
        Collections.reverse(ileWKat);
        proc = new int[ileWKat.size()];
        angle = new int[ileWKat.size()];

        for (int i=0;i<ileWKat.size();i++) {
            proc[i] = (int) (ileWKat.get(i)*100/wydane);
            angle[i] = proc[i]*360 /100;
            if (ileWKat.get(i) != 0) {
                ileKat++;
            }
        }

    }


    @Override
    public void paintComponent(Graphics g) {
        int wys = (getHeight()+getWidth())/2;
        int xpos = wys/10;
        int ypos = wys/10;
        int szer = wys*3/5;

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLUE);


        switch(ileKat) {

            case 1:
                g2d.fillOval(xpos,ypos,szer,szer);
                g2d.drawString("Kategoria 1: "+proc[0]+"%", xpos*7, ypos*7);
                break;
            case 2:
                g2d.fillOval(xpos,ypos,szer,szer);
                g2d.drawString("Kategoria 1: "+proc[1]+"%", xpos*7, ypos*7);

                g2d.setColor(Color.green);
                g2d.drawString("Kategoria 2: " + proc[0] + "%", xpos*7, ypos*8);
                g2d.fillArc(xpos, ypos, szer, szer, 90 - angle[0], angle[0]);
                break;
            case 3:
                g2d.fillOval(xpos, ypos, szer, szer);
                g2d.drawString("Kategoria 1: "+proc[2]+"%", xpos*7, ypos*7);

                g2d.setColor(Color.green);
                g2d.fillArc(xpos, ypos, szer, szer, 90 - angle[0], angle[0]);
                g2d.drawString("Kategoria 2: "+proc[0]+"%", xpos*7, ypos*8);
                g2d.setColor(Color.red);
                g2d.fillArc(xpos, ypos, szer, szer, 90 - angle[0]-angle[1], angle[1]);
                g2d.drawString("Kategoria 3: "+proc[1]+"%", xpos*7, ypos*9);
            default:
                g2d.drawOval(xpos, ypos, szer, szer);
        }


        ileWKat.clear();


    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200,200);
    }
}
