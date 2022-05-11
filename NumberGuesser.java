//Yangyang Lian

import java.util.*;

public class GuessingGame
{
    public static void main(String[] args)
    {
        NumberGuesser numberGuesser = new NumberGuesser(1, 100);
       // RandomNumberGuesser randomNumberGuesser = new RandomNumberGuesser(1, 100);
        char response;

        do {
            numberGuesser.reset();
            //prompt user to think of the number
            System.out.println("Think of a number from 1 to 100.");

            try {
                do {
                    response = userInput(numberGuesser.getCurrentGuess());

                    if (response == 'h') numberGuesser.higher();
                    if (response == 'l') numberGuesser.lower();

                } while (response != 'c');
            }
            catch(IllegalStateException e){
                System.out.println(e.getMessage());
            }
        } while (playAgain());

    }

    //method to get user input and analyze it
    public static char userInput(int guess) {
        char response;
        Scanner input = new Scanner(System.in);

        do {

            System.out.print("Is the number " + guess + "? ('h'igh/'l'ow/'c'orrect): ");
            response = input.next().charAt(0);
        } while (response != 'h' && response != 'l' && response != 'c');

        return response;
    }

    //method to initiate the game again
    public static boolean playAgain() {
        char response;
        Scanner input = new Scanner(System.in);

        do {
            System.out.print("Do you want to play again? (y/n): ");
            response = input.next().charAt(0);
        } while (response != 'y' && response != 'n');

        return response == 'y';
    }
}

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
public class NumberGuesser {

    //high guess
    protected int high;

    //low guess
    protected int low;

    //original high
    private int originalHigh;
    //original low
    private int originalLow;

    //constuctor for Number Guesser
    public NumberGuesser(int l, int h) {
        low = originalLow = l;
        high = originalHigh = h;
    }

    //get current guess
    public int getCurrentGuess() {
        return (high + low) / 2;
    }

    //higher guess method
    public void higher() throws IllegalStateException {
        low = getCurrentGuess() + 1;

        if ((low - 1) >= high) {
            throw new IllegalStateException("We guessed your " +
                    " number already! Try again!");
        }
    }

    //lower guess method
    public void lower() throws IllegalStateException {
        high = getCurrentGuess() - 1;

        if ((high + 1) <= low) {
            throw new IllegalStateException(" We guessed your " +
                    " number already! Try again!");
        }
    }

    //reset
    public void reset() {
        low = originalLow;
        high = originalHigh;
    }
}

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
import java.util.Random;

//Random Number Guesser
public class RandomNumberGuesser extends NumberGuesser {

    //random value
    private int randomValue;

    //update random value
    private boolean updateRandomValue;
    //random value generator
    private Random generator;

    //constructor
    public RandomNumberGuesser(int low, int high) {
        super(low, high);

        updateRandomValue = true;
        generator = new Random();
    }

    //method to get current guess
    public int getCurrentGuess() {

        if (updateRandomValue) {
            randomValue = low + (generator.nextInt((high - low) + 1));
            updateRandomValue = false;
        }

        return randomValue;
    }

    //higher random value
    public void higher() {
        super.higher();
        updateRandomValue = true;
    }

    //lower random value
    public void lower() {
        super.lower();
        updateRandomValue = true;
    }

    //reset the values
    public void reset() {
        super.reset();
        updateRandomValue = true;
    }

}
