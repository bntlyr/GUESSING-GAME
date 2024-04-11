package DAY_1_RESET.GuessingGameNOGUI;
import java.util.Arrays;
import java.util.Scanner;


public class GuessingLogic {
    int maxGuess = 5;
    int[] RandomCode;
    int[] playerGuess;

    public void setMaxGuess(int maxGuess) {
        this.maxGuess = maxGuess;
    }
    public void setRandomCode(int[] RandomCode) {
        this.RandomCode = RandomCode;
    }
    public void setplayerGuess(int[] playerGuess) {
        this.playerGuess = playerGuess;
    }

    public int getmaxGuess() {  
        return maxGuess;
    }
    public int[] getRandomCode() {
        return RandomCode;
    }
    public int[] getplayerGuess() {
        return playerGuess;
    }


    //generate randome numbers for each gamemode 0-9 digits (random number gets encrypted so user doesnt know)
    public static int[] randomNumberGenerator(int noDigits) { //repetition is allowed
        int[] code = new int[noDigits]; 
        int max=9, min=0;
        //generate randome numbers from 0-9
        for (int i = 0; i < noDigits; i++) { 
            code[i] = (min + (int)(Math.random() * ((max - min) + 1)));   
        }
        return code;
    } //--WORKING

    public static char[] encryptCode(int[] Code, int maxGuess) {
        char[] encrypted = new char[Code.length];
        for(int i = 0; i < Code.length; i++) {
            encrypted[i] = 'X';  
        }
        return encrypted;
    }

    public void displayCode(int[] Code) {
        for (int i = 0; i < Code.length; i++) {
            System.out.print(Code[i]);
        }
        System.out.println("");
    }

    //check if right or wrong
    public void checkGuess(int[] Code, int[]Guess) {
        System.out.print("Code: ");
        for(int i = 0; i < Code.length; i++) {
            for(int j = i; j < Code.length;) {
                if(Code[i] == Guess[j]) {
                    System.out.print(Code[i]);
                    break;
                } else {
                    System.out.print('X');
                    break;
                }
            }
        }
        //display the guess for comparison
        System.out.println("");
        System.out.print("Guess: ");
        displayCode(Guess);
    }

    //check if guess is anywhere on the code
    public boolean showHint(int[] Code, int[] Guess) { //WORKING
        for (int i = 0; i < Code.length; i++) {
            for (int j = i+1; j < Code.length; j++) {
                if(Guess[j] == Code[i]) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void StartGame(Scanner input, int noDigits) { //WORKING
        //set values
        int attempts = getmaxGuess();
        setRandomCode(randomNumberGenerator(noDigits));

        while (attempts != 0) {
            //generate random number display it in ***
            System.out.print("Your Code is: ");
            System.out.println(encryptCode(getRandomCode(), noDigits));
            displayCode(getRandomCode());

            //input of player
            System.out.println("Enter Guess(X X X): ");
            int[] Guess = new int[noDigits];
            for(int i = 0; i < noDigits; i++) {
                Guess[i] = input.nextInt();
            }

            setplayerGuess(Guess);
            checkGuess(getRandomCode(), getplayerGuess());
            System.out.println("");
            
            //condition for winning
            if (Arrays.equals(getRandomCode(), Guess)) {
                System.out.println("YOU WIN CONGRATULATIONS\n");
                attempts = 0;
            } else if (showHint(getRandomCode(), Guess)){
                System.out.println("Some are correct but in wrong place\n");
                attempts--;
                System.out.println("You now have " + attempts + " attemps left\n");
            } else {
                System.out.println("\nWRONG!!!");
                attempts--;
                System.out.println("You now have " + attempts + " attemps left\n");
            }
        }
        
    }

}
