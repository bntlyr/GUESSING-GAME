package GameConfigurations;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    Configurations config = new Configurations();

    public GameFrame() {

        // Frame we use
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(config.windowTitle);
        this.setVisible(true);
    }

}
