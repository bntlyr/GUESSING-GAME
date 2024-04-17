package GameConfigurations;

import java.util.Arrays;

public class GameLogic {
    Attempts maxAttempts = new Attempts();
    int[] RandomCode;
    int[] playerGuess;

    public int[] getRandomCode() {
        return RandomCode;
    }

    public int[] getplayerGuess() {
        return playerGuess;
    }

    public void setRandomCode(int[] RandomCode) {
        this.RandomCode = RandomCode;
    }

    public void setplayerGuess(int[] playerGuess) {
        this.playerGuess = playerGuess;
    }

    // generate randome numbers for each gamemode 0-9 digits (random number gets
    // encrypted so user doesnt know)
    public int[] randomNumberGenerator(int noDigits) { // WORKING
        // repetition is allowed
        int[] code = new int[noDigits];
        int max = 9, min = 0;
        // generate randome numbers from 0-9
        for (int i = 0; i < noDigits; i++) {
            code[i] = (min + (int) (Math.random() * ((max - min) + 1)));
        }
        return code;
    } // --WORKING

    public char[] encryptCode(int[] Code, int maxGuess) { // WORKING
        char[] encrypted = new char[Code.length];
        for (int i = 0; i < Code.length; i++) {
            encrypted[i] = 'X';
        }
        return encrypted;
    }

    public String intArrToString(int[] Code) {
        return (Arrays.toString(Code));
    }

    public String charArrToString(char[] encryptCode) {
        String string = new String(encryptCode);
        return string;
    }

    public int[] stringToIntArray(String playerGuess) {
        String[] splitArray = playerGuess.split(" ");
        int[] array = new int[splitArray.length];
        // parsing the String argument as a signed decimal
        // integer object and storing that integer into the
        // array
        for (int i = 0; i < splitArray.length; i++) {
            array[i] = Integer.parseInt(splitArray[i]);
        }
        return array;
    }

    public void displayCode(int[] Code) { // WORKING
        for (int i = 0; i < Code.length; i++) {
            System.out.print(Code[i]);
        }
    }

    // check if guess is anywhere on the code
    public boolean showHint(int[] Code, int[] Guess) { // WORKING
        for (int i = 0; i < Code.length; i++) {
            for (int j = i + 1; j < Guess.length; j++) {
                if (Code[i] == Guess[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
