package ziua_36;

import javax.swing.*;
import java.awt.*;

public class Clasa_SelectareEcran_1_2_V1 {

    public static void main(String[] args) {
// Obține lista de ecrane disponibile
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();

// Alege ecranul dorit (de exemplu, ecranul 1 sau 2)
        int screenIndex = 1; // 0 pentru primul ecran, 1 pentru al doilea ecran, etc.

        if (screenIndex >= screens.length) {
            System.out.println("Ecranul specificat nu există.");
            return;
        }

        GraphicsDevice screen = screens[screenIndex];
        Rectangle bounds = screen.getDefaultConfiguration().getBounds();

// Creează fereastra
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

// Afișează fereastra pe ecranul selectat
        frame.setLocation(bounds.x + (bounds.width - frame.getWidth()) / 2, bounds.y + (bounds.height - frame.getHeight()) / 2);
        frame.setVisible(true);
    }
}
