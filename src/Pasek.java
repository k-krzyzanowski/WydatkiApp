import javax.swing.*;
import java.awt.*;

public class Pasek extends JComponent {

    private float procent=0;
    private float budzet=0;


    public void setProcent(float procent) {
        this.procent = procent;
    }

    public void setBudzet(float budzet) {
        this.budzet = budzet;
    }


    @Override
    public void paintComponent(Graphics g) {
        int szer = getWidth()-1;
        int pcnt = (int) (procent*100/budzet);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawString("Budżet: " + budzet + " zł", 0, 10);
        g2d.setColor(Color.GREEN);



        g2d.fillRoundRect(0, 20, szer, 30, 10, 10);

        if(procent>budzet) {
            g2d.setColor(Color.RED);
            g2d.fillRoundRect(0, 20, szer, 30, 10, 10);
            g2d.setColor(Color.YELLOW);
            g2d.drawString("Budżet przekroczony o: " + (procent - budzet) + " zł", 5, 40);
        }
        else if(procent != 0) {
            g2d.setColor(Color.YELLOW);
            g2d.fillRoundRect(1, 21, (pcnt*szer/100)-2, 28, 10, 10);
            g2d.setColor(Color.BLUE);
            g2d.drawString("Razem wydano " + procent + " zł", 5, 40);


        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100,50);
    }
}
