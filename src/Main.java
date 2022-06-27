import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static String[] enWords = {"knife","mug","tissue","television","cup","lamp","printer"};

    public static void main(String[] args) {


        System.out.println("\n" +
                "\n" +
                "  _    _                     __  __                _____                      \n" +
                " | |  | |                   |  \\/  |              / ____|                     \n" +
                " | |__| | __ _ _ __   __ _  | \\  / | __ _ _ __   | |  __  __ _ _ __ ___   ___ \n" +
                " |  __  |/ _` | '_ \\ / _` | | |\\/| |/ _` | '_ \\  | | |_ |/ _` | '_ ` _ \\ / _ \\\n" +
                " | |  | | (_| | | | | (_| | | |  | | (_| | | | | | |__| | (_| | | | | | |  __/\n" +
                " |_|  |_|\\__,_|_| |_|\\__, | |_|  |_|\\__,_|_| |_|  \\_____|\\__,_|_| |_| |_|\\___|\n" +
                "                      __/ |                                                   \n" +
                "                     |___/                                                    \n" +
                "\n");
        System.out.println("Hello Welcome to my HangMan Game! Hope you like it ;)");

        System.out.print("Would you like to see the instructions(y/n)? ");
        if (sc.nextLine().charAt(0) == 'y') {
            //RULES
            System.out.println("\n\nInstructions:");
            System.out.println("1: You can choose between chosen word or random word");
            System.out.println("2: You can risk to guess the full word/phrase by typing \"/\"");
        }

        boolean running = true;

        System.out.println("THE GAME HAS BEGUN");
        System.out.println("\nw: write your own word/phrase");
        System.out.println("r: random word/phrase (a theme is provided)");
        char op = sc.nextLine().charAt(0);

        String word;

        if (op == 'r') {
            System.out.println("\nThe theme is household items!");
            word = enWords[(int) (Math.random() * enWords.length)];
        } else {
            word = sc.nextLine().toLowerCase();
        }


        HangMan hangMan = new HangMan(word);


        //GAME LOOP
        while (running) {



            System.out.println(hangMan.getHang());
            System.out.println(hangMan.getLetters());

            char input = sc.nextLine().charAt(0);

            if (input == '/') {
                System.out.print("\n\nEnter your guess: ");
                hangMan.fullGuess(sc.nextLine());
            } else {
                hangMan.checkLetter(input);
            }

            if (hangMan.gameState.equals(HangMan.State.LOST)) {
                System.out.println("\n\n\n============================\nHaha you lost, better luck next time ;)\n============================");
                System.out.println(hangMan.getHang());
                running = false;
            }
            if (hangMan.gameState.equals(HangMan.State.WON)) {
                System.out.println("\n\n\n============================\nHaha you win, you beat me\nThank you for playing ;)\n============================");
                System.out.println(hangMan.getHang());
                running = false;
            }

        }

    }

}
