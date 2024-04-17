package GameScenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GameConfigurations.Configurations;
import GameConfigurations.GameFrame;
import GameConfigurations.GamePanel;

public class StartGame implements ActionListener {
    GameFrame window = new GameFrame();
    GamePanel gamePanel = new GamePanel();
    JPanel startGamePanel = new JPanel();
    Configurations config = new Configurations();
    JLabel label = new JLabel();
    JLabel creatorLabel = new JLabel();
    JButton startButton = new JButton();

    public StartGame() {
        // label used
        label.setText("GUESS DA CODEEE!");
        label.setFont(new Font("SansSerif", Font.BOLD, 80));

        creatorLabel.setText("Created by Ctrl-Hayan");
        creatorLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));

        // compents to add to pane
        startGamePanel.setBounds(200, 200, 900, 175);
        startGamePanel.setBackground(Color.WHITE);
        startGamePanel.add(label);
        startGamePanel.add(creatorLabel);

        startButton.setBounds(550, 400, 200, 50);
        startButton.setText("START GAME");
        startButton.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.setBackground(Color.LIGHT_GRAY);
        startButton.addActionListener(this);

        gamePanel.add(startButton);
        gamePanel.add(startGamePanel);
        gamePanel.setLayout(null);

        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) { // after clicking button will go to choose diffculty
        if (e.getSource() == startButton) {
            new ChooseDifficulty();
            window.setVisible(false);
        }
    }

}
