import javax.swing.*;
import java.awt.*;

public class AppMain {

    private void createAndShowGUI() {

        JFrame frame = new JFrame("Analizator wydatkow");
        frame.setContentPane(new AppView().createMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setPreferredSize(new Dimension(800, 550));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                AppMain appMain = new AppMain();
                appMain.createAndShowGUI();
            }
        });

    }

}
