package GameScenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GameConfigurations.Configurations;
import GameConfigurations.Difficulty;
import GameConfigurations.GameFrame;
import GameConfigurations.GameLogic;
import GameConfigurations.GamePanel;

public class ChooseDifficulty implements ActionListener {
    GameFrame window = new GameFrame();
    GamePanel gamePanel = new GamePanel();
    JPanel chooseDifficulty = new JPanel();
    JLabel label = new JLabel();
    JButton EasyButton = new JButton();
    JButton MediumButton = new JButton();
    JButton HardButton = new JButton();
    Configurations config = new Configurations();
    Difficulty gameDifficulty = new Difficulty();
    GameLogic gameLogic = new GameLogic();

    public ChooseDifficulty() {
        // add components
        label.setText("Choose Your Difficulty");
        label.setFont(new Font("SerifsSans", Font.BOLD, 60));

        chooseDifficulty.setBounds(200, 100, 900, 100);
        chooseDifficulty.setBackground(Color.WHITE);
        chooseDifficulty.add(label);

        // buttons for difficulty
        EasyButton.setText("Easy Mode");
        EasyButton.setBounds(550, 250, 200, 50);
        EasyButton.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        EasyButton.setFocusable(false);
        EasyButton.setBackground(Color.LIGHT_GRAY);
        EasyButton.addActionListener(this);

        MediumButton.setText("Medium Mode");
        MediumButton.setBounds(550, 350, 200, 50);
        MediumButton.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        MediumButton.setFocusable(false);
        MediumButton.setBackground(Color.LIGHT_GRAY);
        MediumButton.addActionListener(this);

        HardButton.setText("Hard Mode");
        HardButton.setBounds(550, 450, 200, 50);
        HardButton.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        HardButton.setFocusable(false);
        HardButton.setBackground(Color.LIGHT_GRAY);
        HardButton.addActionListener(this);

        gamePanel.add(chooseDifficulty);
        gamePanel.setLayout(null);
        gamePanel.add(EasyButton);
        gamePanel.add(MediumButton);
        gamePanel.add(HardButton);

        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) { // values will be set here so it doesnt change every new attempt
        if (e.getSource() == EasyButton) {
            // Easy Mode
            gameDifficulty.setGameDifficulty(config.gameDifficulty[0]);

            gameLogic.setRandomCode(gameLogic.randomNumberGenerator(gameDifficulty.getGameDifficulty()));
            new GamePlay(gameDifficulty.getGameDifficulty(),
                    gameLogic.randomNumberGenerator(gameDifficulty.getGameDifficulty()));
            window.setVisible(false);
        } else if (e.getSource() == MediumButton) {
            // Meduim Mode
            gameDifficulty.setGameDifficulty(config.gameDifficulty[1]);
            gameLogic.setRandomCode(gameLogic.randomNumberGenerator(gameDifficulty.getGameDifficulty()));
            new GamePlay(gameDifficulty.getGameDifficulty(),
                    gameLogic.randomNumberGenerator(gameDifficulty.getGameDifficulty()));
            window.setVisible(false);
        } else if (e.getSource() == HardButton) {
            // Hard Mode
            gameDifficulty.setGameDifficulty(config.gameDifficulty[2]);
            gameLogic.setRandomCode(gameLogic.randomNumberGenerator(gameDifficulty.getGameDifficulty()));
            new GamePlay(gameDifficulty.getGameDifficulty(),
                    gameLogic.randomNumberGenerator(gameDifficulty.getGameDifficulty()));
            window.setVisible(false);
        }
    }
}
