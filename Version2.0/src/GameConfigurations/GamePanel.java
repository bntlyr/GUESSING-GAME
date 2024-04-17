package GameConfigurations;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    Configurations config = new Configurations();

    public GamePanel() {
        this.setPreferredSize(new Dimension(config.screenSize[0], config.screenSize[1]));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
    }
}
