// Alejandro Mijares
// February 5, 2023
// Panther ID: 3145563
// Program Version: 1.0
// Java Version: 8
//import java.util.Scanner;
// Imports commented out as requested
public class HangmanTester {
    public static void main(String[] args) {// Function to launch a round of the game
        System.out.println("*******         *******");// Welcome splash screen
        System.out.println("****               ****");
        System.out.println("* Welcome to Hangman! *");
        System.out.println("****               ****");
        System.out.println("*******         *******");
        System.out.println("Classic game of hangman! You have to guess what the word is, letter by letter!");
        System.out.println("If you make 8 incorrect guesses, you lose.");// Game instructions
        System.out.println("The current word will show up as a series of asterisks (*)");
        System.out.println("Each asterisk is a letter you must guess");
        System.out.println("As each letter is correctly guessed, they will appear in the current word.\n\n");

        Hangman hangman = new Hangman();// Instantiate a game object
        Scanner input = new Scanner(System.in); // Create scanner object to take user input

        for (int i=0; i < hangman.getWordListLength() - 1; i++){// Iterates through wordlist
            // Subtract 1 from wordlist length because one word has already been prepared
            while (hangman.getGuessesLeft() > 0) {// Loops while the user still has guesses remaining
                System.out.println("Here is the current word!");
                System.out.println(hangman.getMissingLetters());// Prints status of current round
                System.out.println("You have " + hangman.getGuessesLeft() + " guesses left!\n");
                System.out.println("Please enter a letter.");
                System.out.println("Entering more than one letter will count as incorrect!");
                String guess;
                if (input.hasNext()){// Checking to ensure there is input
                    guess = input.nextLine().toLowerCase().trim();// sanitizing input. A little bit.
                }
                else{
                    continue;// If no input, loop restarts
                }
                if (hangman.letterAlreadyGuessed(guess)){ // Checks if user has already tried this letter
                    System.out.println("***\nYou already guessed that letter! Try another.\n***\n");
                    continue;
                }// If letter has not been guessed, it gets added to the AlreadyGuessed list
                boolean letterFound = hangman.letterInWord(guess); // Checks if that letter has

                if (letterFound){// Congratulates player if word is found
                    System.out.println("***\nGreat guess!\n***\n");
                }
                else{
                    hangman.decrementGuessesLeft(); // Letter did not match, one guess consumed
                    System.out.println("***\nSorry, the princess is in another castle. Also, that letter isn't in the word.\n***\n");
                }
            }
            if (hangman.wordGuessed()) {// checks if word has been guessed
                System.out.println(hangman.getMissingLetters());// Function removes word from the list to prevent doubling
                System.out.println("***\nYou solved it! Huzzah!\n***\n");
            }
            else {
                System.out.println("***\nYou didn't guess the word :(\n");
                hangman.solveWord();// Fills in the missing letters to show the user what the word was
                System.out.println(hangman.getMissingLetters());
                System.out.println("There's always next time!\n***\n");
            }
            hangman.nextWord();// Go to next word in the list randomly
            if (hangman.getCurrentWord() == null){// If no words left in the list, ends the game
                System.out.println("\n***\nThat's all the words! Thanks for playing. Hope you had fun!");
                break;
            }
            System.out.println("\n***\nWould you like to keep playing? Yes or No");
            String resp = input.nextLine().toLowerCase().trim();
            if (resp.equals("y") || resp.equals("yes")){// Allows the player to cleanly exit the game early
                System.out.println("\n***\nGreat! On to the next round!");
            }
            else{
                System.out.println("That's too bad. Hope you play again!");
                break;
            }
        }

    }
}
