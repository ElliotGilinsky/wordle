import java.util.Scanner;

public class WordleGame {

    //Step 1: Declare a final variable that represents the “Wordle of the Day”
    private final char[] worldleOfTheDay = {'W', 'O', 'R', 'L', 'D'};
    //Step 2: Create an array of characters named alphabet that represents the possible letters inside the word.
    // This list will be initialized to the entire alphabet. All values should be uppercase to start the game.
    private final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final Scanner in;
    private final char[] solution = {' ', ' ', ' ', ' ', ' '};

    public WordleGame() {
        in = new Scanner(System.in);
    }
    //Step 3: Prompt the user for the guess. You must tell them the number of the guess each time.
    // This task must be done in a method that returns the guess in the form of a String.
    // This method will accept the guess number as a parameter.
    private String nextGuess(int guessNum) {
        String guess;
        do {
            System.out.print("Make guess #" + guessNum +": ");
            guess = in.nextLine();
            if (guess.length() == solution.length) return guess;
            System.out.println("Please enter exactly " + solution.length + " characters");
        } while (true);
    }

    //Step 4: Convert the user’s string to an array of characters.
    // Must be done in a method that has a return type of char[] and a String parameter.
    private char[] convertString(String str) {
        char[] ch = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ch[i] = Character.toUpperCase(str.charAt(i));
        }
        return ch;
    }

    //Step 5: Check to see if each character in the user’s guess (using the new array) is in the “Wordle of the Day”.
    private void processGuess(char[] guess) {
        for (int i = 0; i < guess.length; i++) {
            // If the character is in the Wordle and it is in the same position,
            // you should enter that character into an array called solution
            // that represents the completed word
            char nextGuessChar = guess[i];
            if (nextGuessChar == worldleOfTheDay[i]) solution[i] = nextGuessChar;
            else {
                // If the character is in the Wordle, but NOT in the correct position,
                // you should change the letter in the alphabet array to lowercase.
                boolean found=false;
                for (char c: worldleOfTheDay) {
                    if (c == nextGuessChar) {
                        setAlphabetCharToLowerCase(nextGuessChar);
                        found = true;
                    }
                }
                //If the character is NOT in the Wordle, then you should change the letter in the alphabet array to a blank.
                if (!found) {
                    setAlphabetCharToBlank(nextGuessChar);
                }
            }
        }
    }

    private void setAlphabetCharToLowerCase(char c) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i]==c) alphabet[i] = Character.toLowerCase(alphabet[i]);
        }
    }

    private void setAlphabetCharToBlank(char c) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i]==c) alphabet[i] = ' ';
        }
    }

    //Step 6: Show the values of the solution array and alphabet array with the updated information and descriptive prompts.
    // This task must be completed in array that accepts the two array parameters and prints them inside the method.
    // Use the for each loop to print the contents.
    private void printStatus(char[] solution, char[] alphabet) {
        System.out.print("Current solution: '");
        for (char c : solution) System.out.print(c);
        System.out.println("'");

        System.out.print("Current alphabet: '");
        for (char c : alphabet) System.out.print(c);
        System.out.println("'");
    }
    private boolean isSolved(){
        for (int i = 0; i < solution.length; i++) {
            if (solution[i]!=worldleOfTheDay[i]) return false;
        }
        return true;
    }
    //Step 7: Repeat steps 3 through 6 until the guess matches the “Wordle of the Day”
    // (aka, the solution array is complete with valid characters) or until they have used all six tries.
    // This repeated process should be done using a do-while loop.
    public void playGame() {
        int MaxNumOfTries = 6;
        int currentTry = 1;
        do {
            String nextGuess = nextGuess(currentTry);
            processGuess(convertString(nextGuess));
            printStatus(solution, alphabet);
        } while (!isSolved() && currentTry++ <= MaxNumOfTries);
        //Step 8: You should print either a congratulatory message or a message telling them they are out of tries, as appropriate to the situation.
        if (isSolved()) {
            System.out.println("Congrats on solving the Wordle Of The Day");
        }
        else {
            System.out.println("Out of tries. Please try again later");
        }
    }

    public static void main(String[] args) {
        WordleGame game = new WordleGame();
        game.playGame();
    }

}