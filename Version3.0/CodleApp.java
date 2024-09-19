import java.awt.event.*;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class CodleApp implements ActionListener {
    // Static Variables declarations
    public final int screenSize[] = {1280, 720};
    JFrame gameWindow = new JFrame("Codle by sealdev");
    JPanel panelHeader = new JPanel();
    JPanel panelMain = new JPanel();
    JPanel panelFooter = new JPanel();
    JPanel panelEast = new JPanel();
    JPanel panelWest = new JPanel();
    JPanel panelNorth = new JPanel();

    JLabel labelTitle = new JLabel("CODLE NA PARANG WORDLE");
    JLabel labelcreator = new JLabel("created by sealdev");
    JTextField inputFields[][] = new JTextField[6][5]; // For 30 boxes 5 x 6 grid
    JButton buttonSubmit = new JButton("SUBMIT");

    int currentRow = 0; // Track the current row being edited

    GameLogic gameLogic = new GameLogic();
    int[] randomCode = gameLogic.randomNumberGenerator(5); // Generate a 5-digit random code
    boolean isCodeGuessed = false; // Track if the code has been guessed correctly

    // Constructors
    public CodleApp() {
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setMinimumSize(new Dimension(screenSize[0], screenSize[1]));
        gameWindow.setVisible(true);
        gameWindow.setLayout(new BorderLayout());

        // Panel Setting
        panelHeader.setPreferredSize(new Dimension(100, 70));
        panelWest.setPreferredSize(new Dimension(300, 100));
        panelMain.setPreferredSize(new Dimension(100, 100));
        panelEast.setPreferredSize(new Dimension(300, 100));
        panelFooter.setPreferredSize(new Dimension(100, 100));

        // Header Panel
        panelHeader.setLayout(new BorderLayout());
        panelHeader.add(labelTitle, BorderLayout.CENTER);
        panelHeader.add(labelcreator, BorderLayout.SOUTH);
        labelTitle.setFont(new Font("sans-serif", Font.BOLD, 25));
        labelTitle.setVerticalAlignment(JLabel.BOTTOM);
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelcreator.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        labelcreator.setVerticalAlignment(JLabel.TOP);
        labelcreator.setHorizontalAlignment(JLabel.CENTER);

        // Main Panel
        panelMain.setLayout(new BorderLayout()); // Center alignment with 5px gaps

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(6, 5, 5, 5)); // 5 rows, 5 columns with gaps
        gridPanel.setPreferredSize(new Dimension(300, 350)); // Adjust size as needed

        // Initialize the input fields and add them to gridPanel
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 5; col++) {
                inputFields[row][col] = new JTextField();
                inputFields[row][col].setFont(new Font("sans-serif", Font.BOLD, 40));
                inputFields[row][col].setPreferredSize(new Dimension(50, 50));
                inputFields[row][col].setHorizontalAlignment(JTextField.CENTER);

                // Add document filter to restrict input to numbers and one character only
                ((AbstractDocument) inputFields[row][col].getDocument()).setDocumentFilter(new NumericFilter());

                gridPanel.add(inputFields[row][col]);

                // Disable all fields except the first row
                if (row != 0) {
                    inputFields[row][col].setEditable(false);
                }
            }
        }

        // Footer Panel
        panelFooter.setLayout(new FlowLayout());
        panelFooter.add(buttonSubmit);
        buttonSubmit.setVerticalAlignment(JButton.CENTER);
        buttonSubmit.setHorizontalAlignment(JButton.CENTER);
        buttonSubmit.setPreferredSize(new Dimension(200, 50));

        // JFRAME CONFIGURATIONS
        gameWindow.add(panelHeader, BorderLayout.NORTH);
        gameWindow.add(panelMain, BorderLayout.CENTER);
        gameWindow.add(panelFooter, BorderLayout.SOUTH);
        panelMain.add(panelEast, BorderLayout.EAST);

        panelHeader.setBackground(Color.GRAY);
        panelMain.setBackground(Color.DARK_GRAY);
        panelFooter.setBackground(Color.LIGHT_GRAY);

        // Add ActionListener for the submit button
        buttonSubmit.addActionListener(this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CodleApp();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSubmit) {
            // Check if the current row is filled
            boolean rowFilled = true;
            int[] playerGuess = new int[5];
            for (int col = 0; col < 5; col++) {
                String text = inputFields[currentRow][col].getText();
                if (text.isEmpty()) {
                    rowFilled = false;
                    break;
                }
                playerGuess[col] = Integer.parseInt(text);
            }

            if (rowFilled) {
                // Check the guess and highlight the boxes accordingly
                checkAndHighlightGuess(playerGuess);

                // Check if the guess is correct
                if (Arrays.equals(playerGuess, randomCode)) {
                    isCodeGuessed = true;
                    displayCongratulatoryMessage(); // Display congrats and ask for new game
                } else {
                    // Enable the next row if available
                    if (currentRow < 5) {
                        currentRow++; // Move to the next row
                        for (int col = 0; col < 5; col++) {
                            inputFields[currentRow][col].setEditable(true);
                        }
                    } else if (!isCodeGuessed) {
                        // All rows filled, reveal the correct code if not guessed
                        revealCorrectCode("Attempts exhausted! The correct code was:");
                    }
                }
            }
        }
    }

    private void checkAndHighlightGuess(int[] playerGuess) {
        boolean[] correctPositions = new boolean[5]; // Track correct position guesses
        boolean[] usedInCode = new boolean[5]; // Track which digits of the random code are used

        // First pass: check for correct positions (Green)
        for (int col = 0; col < 5; col++) {
            if (playerGuess[col] == randomCode[col]) {
                inputFields[currentRow][col].setBackground(Color.GREEN);
                correctPositions[col] = true;
                usedInCode[col] = true;
            }
        }

        // Second pass: check for correct digits in wrong positions (Yellow)
        for (int col = 0; col < 5; col++) {
            if (!correctPositions[col]) { // If not already marked green
                boolean found = false;
                for (int j = 0; j < 5; j++) {
                    if (!usedInCode[j] && playerGuess[col] == randomCode[j]) {
                        inputFields[currentRow][col].setBackground(Color.YELLOW);
                        usedInCode[j] = true;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    // If the digit is not found in the code, set the background to gray
                    inputFields[currentRow][col].setBackground(Color.GRAY);
                }
            }
        }
    }

    private void revealCorrectCode(String message) {
        // Display the correct code using a JOptionPane
        StringBuilder code = new StringBuilder();
        for (int num : randomCode) {
            code.append(num).append(" ");
        }

        JOptionPane.showMessageDialog(gameWindow, message + " " + code.toString().trim(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
        gameWindow.dispose();
        new CodleApp();
    }

    private void displayCongratulatoryMessage() {
        // Show congratulatory message and ask if the user wants to play again
        int response = JOptionPane.showConfirmDialog(gameWindow, "Congratulations! You've guessed the code. Do you want to play again?", "New Game", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            gameWindow.dispose();
            new CodleApp();
        } else {
            System.exit(0); // Exit if the user doesn't want to play again
        }
    }

    // DocumentFilter class to restrict input to numbers and limit to one character
    class NumericFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("\\d") && fb.getDocument().getLength() == 0) { // Only allow one digit
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text.matches("\\d") && fb.getDocument().getLength() == 0) { // Only allow one digit
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }

    public class GameLogic {
        public int[] randomNumberGenerator(int noDigits) {
            int[] code = new int[noDigits];
            int max = 9, min = 0;
            for (int i = 0; i < noDigits; i++) {
                code[i] = (min + (int) (Math.random() * ((max - min) + 1)));
            }
            return code;
        }
    }
}
