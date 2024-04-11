package DAY_1_RESET.GuessingGameNOGUI;

import java.util.Scanner;

public class GuessingGame {
    
    static int width = 50;
    //gamemode
    static int easy = 3;
    static int meduim = 5;
    static int hard = 7;
    boolean isRunning;

    public void setGameState(boolean isRunning)  {
        this.isRunning = isRunning;
    }   
    public boolean getGameState() {
        return isRunning;
    }
    public static void StartMenu() {
        for (int i = 0; i < width; i++) { //generating ======= for rows
            System.out.print("=");
        }
        System.err.println("");
        for(int j = 0; j < width; j++) {
            if(j == 11) {
                System.out.println("WELCOME TO GUESS MY CODEEE!");
                break;
            } else {
                System.out.print(" ");
            }
        }
        for (int i = 0; i < width; i++) { //generating ======= for footer
            System.out.print("=");
        }
        System.err.println("\n");
        //options
        for(int i = 0; i < width; i++) {
            if(i == 10) {
                System.out.println("[ 1. Play Game ] [ 2. Exit ]");
            } else {
                System.out.print(" ");
            }
        }
        
    }

    public static void MainMenu(Scanner input) {
        GuessingLogic gameMode = new GuessingLogic();
        for (int i = 0; i < width; i++) { //generating ======= for footer
            System.out.print("=");
        }
        System.out.println("\n| Choose Level of Diffuclty |\n");
        System.out.println("1. Easy - [3 digit code]");
        System.out.println("2. Meduim - [5 digit code]");
        System.out.println("3. Hard - [7 digit code]");
        System.out.print("\nOptions: ");
        int options = input.nextInt();
        switch (options) {
            case 1:
                gameMode.StartGame(input, easy);
                break;
            case 2:
                gameMode.StartGame(input, meduim);
                break;
            case 3:
                gameMode.StartGame(input, hard);
                break;
            default:
            System.out.println("\nWrong Input, Please try Again");
                break;
        }
    }
    
    
    
    
    
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GuessingGame game = new GuessingGame();
        game.setGameState(true);
        while (game.getGameState()) {
            StartMenu();
            System.out.print("\nEnter Options: ");
            int options = input.nextInt();
            switch (options) {
                case 1:
                    MainMenu(input);
                    break;
                case 2: 
                    game.setGameState(false);
                    game.getGameState();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nWrong Input, Please try Again");
                    break;
            }
        }
        
    }
}
