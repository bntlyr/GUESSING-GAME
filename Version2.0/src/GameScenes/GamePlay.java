package GameScenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import GameConfigurations.Attempts;
import GameConfigurations.Difficulty;
import GameConfigurations.GameFrame;
import GameConfigurations.GameLogic;
import GameConfigurations.GamePanel;

public class GamePlay implements ActionListener {
    Attempts maxAttempts = new Attempts();
    Difficulty gameDifficulty = new Difficulty();
    GameLogic gameLogic = new GameLogic();

    // game frame components
    GameFrame window = new GameFrame();
    GamePanel gamePanel = new GamePanel();
    JPanel headerPanel = new JPanel();
    JPanel bodyPanel = new JPanel();
    JLabel diffucultyLabel = new JLabel();
    JLabel attemptsLeft = new JLabel();
    JTextArea codeToGuess = new JTextArea();
    JTextArea playerGuess = new JTextArea();
    JTextField playerGuessField = new JTextField();
    JButton Submit = new JButton();
    JLabel labelCode = new JLabel();
    JLabel showHintMessage = new JLabel();

    int attempts = maxAttempts.getAttempts();

    public GamePlay(int difficulty, int[] code) {
        gameLogic.setRandomCode(code);
        // set values for the game
        setHeaderPanel(difficulty);
        setBodyPanel(gameLogic.getRandomCode(), difficulty, attempts);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == Submit) {
                    gameLogic.setRandomCode(gameLogic.getRandomCode());
                    gameLogic.setplayerGuess(gameLogic.stringToIntArray(playerGuessField.getText()));
                    checkGuess(gameLogic.getRandomCode(), gameLogic.getplayerGuess(),
                            gameDifficulty.getGameDifficulty());
                    // Check if the player's guess is correct
                    if (Arrays.equals(gameLogic.getRandomCode(), gameLogic.getplayerGuess())) {
                        isGameOver(attempts, true);
                    } else if (gameLogic.showHint(gameLogic.getRandomCode(), gameLogic.getplayerGuess())) {
                        showHintMessage.setText("Some were correct but in wrong place");
                        showHintMessage.setFont(new Font("SerifsSans", Font.PLAIN, 20));
                        showHintMessage.setBounds(482, 140, 400, 50);
                        bodyPanel.add(showHintMessage);
                        System.out.println("\nSome are correct but in wrong place\n");
                        // show attempts
                        attempts--;
                        attemptsLeft.setText("Attempts Left: " + String.valueOf(attempts));
                        attemptsLeft.setFont(new Font("SerifsSans", Font.BOLD, 30));
                        attemptsLeft.setForeground(Color.RED);
                        attemptsLeft.setBounds(1000, 40, 400, 50);
                        System.out.println("You now have " + attempts + " attempts left\n");
                    } else {
                        attempts--;
                        System.out.println("\nWRONG!!!");
                        attemptsLeft.setText("Attempts Left: " + String.valueOf(attempts));
                        attemptsLeft.setFont(new Font("SerifsSans", Font.BOLD, 30));
                        attemptsLeft.setForeground(Color.RED);
                        attemptsLeft.setBounds(1000, 40, 400, 50);
                        System.out.println("You now have " + attempts + " attempts left\n");
                    }
                    if (attempts == 0) {
                        isGameOver(attempts, false);
                    }

                }
            }
        });

    }

    private void setHeaderPanel(int diffculty) {
        String GameMode = new String();
        // condition for displaying game difficulty
        if (diffculty == 4) {
            GameMode = "Easy Mode";
        } else if (diffculty == 6) {
            GameMode = "Medium Mode";
        } else if (diffculty == 8) {
            GameMode = "Hard Mode";
        }

        diffucultyLabel.setText(GameMode);
        diffucultyLabel.setFont(new Font("SerifsSans", Font.BOLD, 30));
        diffucultyLabel.setBounds(50, 40, 400, 50);

        attemptsLeft.setText("Attempts Left: " + String.valueOf(maxAttempts.getAttempts()));
        attemptsLeft.setFont(new Font("SerifsSans", Font.BOLD, 30));
        attemptsLeft.setForeground(Color.RED);
        attemptsLeft.setBounds(1000, 40, 400, 50);

        headerPanel.add(attemptsLeft);
        // headerPanel.setBackground(Color.GREEN);
        headerPanel.setBounds(0, 0, 1280, 130);
        headerPanel.add(diffucultyLabel);
        headerPanel.setLayout(null);

    }

    private void setBodyPanel(int[] code, int gameDifficulty, int attempts) {
        // panel for Body
        labelCode.setText("GUESS CODE");
        labelCode.setFont(new Font("SerifsSans", Font.BOLD, 40));
        labelCode.setBounds(510, 0, 300, 40);

        // set values for random code

        // display the textarea differently accornign to difficulty
        if (gameDifficulty == 4) {
            codeToGuess.append(gameLogic.charArrToString(
                    gameLogic.encryptCode(code, attempts)));
            codeToGuess.setFont(new Font("SerifsSans", Font.PLAIN, 40));
            codeToGuess.setBounds(590, 50, 108, 50);
            codeToGuess.setFocusable(false);
            System.out.println("Random Code is: ");
            System.out.println("\n");
        } else if (gameDifficulty == 6) {
            codeToGuess.append(gameLogic.charArrToString(
                    gameLogic.encryptCode(code, attempts)));
            codeToGuess.setFont(new Font("SerifsSans", Font.PLAIN, 40));
            codeToGuess.setBounds(567, 50, 162, 50);
            codeToGuess.setFocusable(false);
            System.out.println("Random Code is: ");
            System.out.println("\n");
        } else if (gameDifficulty == 8) {
            codeToGuess.append(gameLogic.charArrToString(
                    gameLogic.encryptCode(code, attempts)));
            codeToGuess.setFont(new Font("SerifsSans", Font.PLAIN, 40));
            codeToGuess.setBounds(540, 50, 216, 50);
            codeToGuess.setFocusable(false);
            System.out.println("Random Code is: ");
            System.out.println("\n");
        }

        // JTextField for Code submissions
        playerGuessField.setBounds(500, 220, 300, 50);
        playerGuessField.setFont(new Font("SerifsSans", Font.PLAIN, 40));

        Submit.setText("SUBMIT");
        Submit.setBounds(550, 300, 200, 50);
        Submit.setFont(new Font("SerifsSans", Font.PLAIN, 20));
        Submit.setBackground(Color.LIGHT_GRAY);
        Submit.setFocusable(false);

        // start the gameloop, then put the button at gameloop

        bodyPanel.add(Submit);
        bodyPanel.add(playerGuessField);
        bodyPanel.add(codeToGuess);
        bodyPanel.add(labelCode);
        // bodyPanel.setBackground(Color.BLUE);
        bodyPanel.setBounds(0, 130, 1280, 590);
        bodyPanel.setLayout(null);
        setGameFrame();
    }

    private void setGameFrame() {
        // game panel and frame
        gamePanel.setLayout(null);
        gamePanel.add(headerPanel);
        gamePanel.add(bodyPanel);

        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);

    }

    private boolean isGameOver(int attempts, boolean isPlayerWin) {
        if (playerLose(attempts)) {
            // YOU LOSE
            // 0 yes, 1 no, - for closing
            int answer = JOptionPane.showConfirmDialog(null,
                    "YOU LOSE, OUT OF ATTEMPTS \n Do you want to try again",
                    "RAN OUT OF ATTEMPTS", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                new ChooseDifficulty(); // lets choose difficulty
                System.out.println("\nSTARTING NEW GAME...\n");
            } else if (answer == 1) {
                System.out.println("Redirecting to Home page");
                new StartGame();
            }
            window.setVisible(false);
            return true;
        } else if (playerWon(isPlayerWin)) {
            System.out.println("\nYOU WIN CONGRATULATIONS\n");
            JOptionPane.showMessageDialog(null, "YOU WIN CONGRATULATIONS", "YOU ARE A WINNER",
                    JOptionPane.PLAIN_MESSAGE);
            // handle winning condition
            System.out.println("Redirecting to Home page");
            new StartGame();
            return true;
        }
        return false;
    }

    private boolean playerWon(boolean isPlayerWin) {
        // if player gets correct guess
        if (isPlayerWin) {
            return true;
        }
        return false;
    }

    private boolean playerLose(int attempts) {
        // if player runs out of attempts
        if (attempts == 0) {
            return true;
        }
        return false;
    }

    public void checkGuess(int[] Code, int[] Guess, int diffculty) {
        // use playerGuess
        playerGuess.setText(null);
        for (int i = 0; i < Code.length; i++) {
            for (int j = i; j < Guess.length;) {
                if (Code[i] == Guess[j]) {
                    // reveal the code based on gameMode

                    playerGuess
                            .append(Integer.toString(Code[i]));
                    playerGuess.setFont(new Font("SerifsSans", Font.PLAIN, 40));
                    playerGuess.setBounds(540, 100, 216, 50);
                    playerGuess.setFocusable(false);
                    bodyPanel.add(playerGuess);

                    System.out.print(gameLogic.getRandomCode()[i]); // log purposes
                    break;
                } else {

                    playerGuess.append("X");
                    playerGuess.setFont(new Font("SerifsSans", Font.PLAIN, 40));
                    playerGuess.setBounds(540, 100, 216, 50);
                    playerGuess.setFocusable(false);
                    bodyPanel.add(playerGuess);

                    System.out.print('X');
                    break;
                }
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}