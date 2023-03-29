// Alejandro Mijares
// February 5, 2023
// Panther ID: 3145563
// Program Version: 1.0
// Java Version: 8
// import java.util.Random;
// Imports commented out as requested
/**
 * Classic game of hangman! You have to guess what the word is, letter by letter!
 * If you make 8 incorrect guesses, you lose.
 * The current word will show up as a series of asterisks (*)
 * Each asterisk is a letter you must guess
 * As each letter is correctly guessed, they will appear in the current word.
 */
public class Hangman {
    private int guessesLeft; // Counter for remaining guesses
    private int wordsUsed; // Counter for how many words have been used
    private String[] lettersGuessed; // Array holding the letters the user has guessed for the current word
    private String[] wordList;// Array to hold potential words
    private String[] currentWord;// Array to hold characters of current word

    private String[] missingLetters;// String that displays correct guesses and missing spaces


    public int getWordListLength() {// Returns the amount of words in the word list
        return wordList.length;
    }// Returns a count of the remaining words


    public String getCurrentWord() {
        return String.join(" ", currentWord);
    }// Returns the currently selected word

    public String getMissingLetters() {// Returns a stringified version of the missing letters
        return String.join(" ", missingLetters);
    }// Returns the asterisked current word

    public int getGuessesLeft() {
        return guessesLeft;
    }// Returns current amount of incorrect guesses remaining

    public void decrementGuessesLeft() {
        guessesLeft--;
    }// Reduces guesses remaining by 1
    public Hangman() {// Default Constructor
        wordList = new String[]{    "aardvark", //Wordlist, 14 words long
                                    "badger",
                                    "chrysalis",
                                    "derelict",
                                    "erudite",
                                    "filigree",
                                    "gestate",
                                    "hubris",
                                    "irascible",
                                    "jovian",
                                    "koala",
                                    "lemons",
                                    "myopic",
                                    "nimrod",
                                    "orange"};
        guessesLeft = 8;// Incorrect guesses remaining
        lettersGuessed = new String[26];// empty array to hold which letters have been guessed
        wordsUsed = 0;// Count of how many rounds have been played
        currentWord = randomWord();// Holds the active word
        missingLetters = generateMissingLetters();// Holds the word hint (asterisked) string
    }

    private String[] randomWord(){// Function to pick words from passed array
        Random random = new Random();// Create a random int generator
        int randomInt = random.nextInt(wordList.length - wordsUsed - 1); // Generate random int in range of list
        String[] word = wordList[randomInt].split(""); // Retrieve random word and store in array
        wordList[randomInt] = wordList[wordList.length - wordsUsed++ - 1]; // Replace current index with last word in list
        wordList[wordList.length - wordsUsed - 1] = null; // Replace last word in list with null operator
        return word;
    }

    private String[] generateMissingLetters() {// Function to update hidden letters
        String[] obfuscatedLetters = new String[currentWord.length]; // Temporary array
        for (int i=0; i<currentWord.length; i++){// Add an asterisk for each letter in current word
            obfuscatedLetters[i] = "*";
        }
        return obfuscatedLetters;
    }

    public void nextWord(){// Cycles through to the next word
        currentWord = randomWord();// Select random word
        missingLetters = generateMissingLetters();// Generate word hint
        guessesLeft = 8;// Resets amount of guesses remaining
        lettersGuessed = new String[26];// Resets the letters guessed
    }

    public void solveWord(){// Adds remaining letters to the hint word
        for (int i = 0; i < currentWord.length; i++){
            if (missingLetters[i].equals("*")){
                missingLetters[i] = currentWord[i];
            }
        }
    }

    public boolean letterInWord(String letter) {  // Function to find if selected letter is in a word
                                                // Nullifies the letter if found
        boolean letterFound = false; // Marker to indicate if the letter has been found
                                    // This allows the loop to continue checking for multiple occurrences
                                    // of the currently guessed letter
        for (int i=0; i < currentWord.length; i++){// Iterate through letters in current word
            if (letter.equals(currentWord[i])){// Check current guess against each letter
                missingLetters[i] = letter;// If found, add the letter to missingLetters
                currentWord[i] = null;// Mark current letter as found
                letterFound = true; // Mark found as true if letter found.
            }
        }
        if (wordGuessed()){ // Checks if the word is solved
            guessesLeft = 0; // Set guesses left to 0 to continue to the next word
        }
        return letterFound;
    }

    public boolean wordGuessed(){// Checks to see if the current word has been successfully guessed
        for (int i=0; i < currentWord.length; i++){// Iterates through current word array
            if (currentWord[i] != null){// Checks if any letter has not been nullified
                return false;// If there are still characters in the array, returns false
            }
        }
        return true;// No characters, returns true (word has been guessed)
    }

    public boolean letterAlreadyGuessed(String letter){
        for (int i=0; i < lettersGuessed.length; i++){// Iterates through current word
            if (letter.equals(lettersGuessed[i])){// Checks if guessed word is present
                return true;
            }
            else if (lettersGuessed[i] == null){
                lettersGuessed[i] = letter;
                break;
            }
        }
        return false;
    }
}