import java.util.Scanner;

/**
 * Class that implements a Pokemon colosseum.
 * <p>
 * The Pokemon battles that you might have grown up to know and love are now console based! The
 * Colosseum class is where all the battles will go down. We will build our Pokemon, let them
 * battle, and see who will be the winner!
 *
 * @see <a href="https://cs125.cs.illinois.edu/lab/4/">Lab 4 Description</a>
 */
public class Colosseum {
    /**
     * The maximum number of hit points we will allow a Pokemon to start with.
     */
    static final int MAX_HIT_POINTS = 50;

    /**
     * The maximum number of rounds we will let the Pokemon battle.
     */
    static final int MAX_NUM_ROUNDS = 10;

    /**
     * The first Pokemon we will be fighting.
     */
    static Pokemon firstPokemon;

    /**
     * The second Pokemon we will be fighting.
     */
    static Pokemon secondPokemon;

    /**
     * Input scanner. Use this to take in user's input for buildPokemon(). <br>
     * Useful functions: next(), nextInt() .
     */
    static Scanner myScan;

    /**
     * How we will build our Pokemon to battle.
     * <p>
     * Obtain user input to set Pokemon's member variables
     * <p>
     * Requirements we should check the user for: <br>
     * - Hit points are between 1 and MAX_HIT_POINTS <br>
     * - No more than 50 points are split between attack level and defense leve <br>
     * - Attack level and defense level must have at least 1 point each <br>
     * Example of how this will look to the user:
     * <p>
     * Please name your Pokemon: Dolphin <br>
     * How many hit points will it have? (1-50): 50 <br>
     * Split fifty points between attack level and defense level <br>
     * Enter your attack level (1-49): 47 <br>
     * Enter your defense level (1-3): 3 <br>
     * <br>
     * Example of checking for bad input: <br>
     * <br>
     * Please name your Pokemon: Fire <br>
     * How many hit points will it have? (1-50): 0 <br>
     * Sorry. Hit points must be between 1 and 50: 55 <br>
     * Sorry. Hit points must be between 1 and 50: 50 <br>
     * Split fifty points between attack level and defense level <br>
     * Enter your attack level (1-49): -10 <br>
     * Sorry. The attack level must be between 1 and 49: 73 <br>
     * Sorry. The attack level must be between 1 and 49: 27 <br>
     * Enter your defense level (1-23): 24 <br>
     * Sorry. The defense level must be between 1 and 23: 23
     *
     * @return tempPokemon - the Pokemon we built and are going to set our fighting Pokemon to <br>
     *         (Look, we can return objects too!)
     *         <p>
     *         Implement this function.
     */
    public static Pokemon buildPokemon() {
        Pokemon tempPokemon = new Pokemon();
        System.out.println("Please name your Pokemon.");
        System.out.println("Name:");
        tempPokemon.name = myScan.next();

        System.out.println("How many hit points will it have? (1-50)");
        System.out.println("Hit points:");

        do {
            tempPokemon.hitPoints = myScan.nextInt();
            if (tempPokemon.hitPoints > MAX_HIT_POINTS || tempPokemon.hitPoints <= 0) {
                System.out.println("Sorry. Invalid hit points. Try again: (1 - 50)");
            }
        } while (tempPokemon.hitPoints > MAX_HIT_POINTS || tempPokemon.hitPoints <= 0);

        /*
        DID THIS FIRST FOR EVERY LO  BUT THAT DOESN'T LOOK GOOD AND ASKS FOR INPUT TWICE
        int tempA = myScan.nextInt();
        while (tempA > MAX_HIT_POINTS || tempA <= 0) {
            System.out.println("Sorry. Hit points must be between 1 and 50. Try again:");
            tempA = myScan.nextInt();
        }
        tempPokemon.hitPoints = tempA;
        */

        System.out.println("Split " + tempPokemon.hitPoints + " between attack level and defense level.");
        int maxAttack = tempPokemon.hitPoints - 1;
        System.out.println("Attack level: (1 - " + maxAttack + ")");
        do {
            tempPokemon.attackLevel = myScan.nextInt();
            if (tempPokemon.attackLevel > maxAttack || tempPokemon.attackLevel <= 0) {
                System.out.println("Sorry. Invalid attack level. Try again: (1 - " + maxAttack + ")");
            }
        } while (tempPokemon.attackLevel > maxAttack || tempPokemon.attackLevel <= 0);

        int maxDefense = tempPokemon.hitPoints - tempPokemon.attackLevel;
        System.out.println("Defense Level: (1 - " + maxDefense + ")");
        do {
            tempPokemon.defenseLevel = myScan.nextInt();
            if (tempPokemon.defenseLevel > maxDefense || tempPokemon.defenseLevel <= 0) {
                System.out.println("Sorry. Invalid defense level. Try again: (1 - " + maxDefense + ")");
            }
        } while (tempPokemon.defenseLevel > maxDefense || tempPokemon.defenseLevel <= 0);

        System.out.println("Thank you! " + tempPokemon.name + " has been built");
        return tempPokemon;
    }

    /**
     * Prints who is ahead.
     * <p>
     * Compares the two Pokemon to see if there's a tie, or if a pokemon is currently winning.
     * <p>
     * Example: <br>
     * Fire has 41 hit points <br>
     * Dolphin has 44 hit points <br>
     * <br>
     * Print "Dolphin is currently ahead!"
     * <p>
     * Implement this function.
     */
    public static void printWhoIsAhead() {
        if (firstPokemon.hitPoints > secondPokemon.hitPoints) {
            System.out.println(firstPokemon.name + " is currently ahead!");
        } else if (secondPokemon.hitPoints > firstPokemon.hitPoints) {
            System.out.println(secondPokemon.name + " is currently ahead!");
        } else {
            System.out.println(firstPokemon.name + " and " + secondPokemon.name + " are tied!");
        }

    }

    /**
     * Prints out the overall winner of the battle.
     * <p>
     * This will only be called if there is not a tie, so you don't need to worry about this case.
     * <p>
     * Write this function.
     */
    public static void determineWinner() {
        if (secondPokemon.hitPoints <= 0) {
            System.out.println(firstPokemon.name + " DESTROYED " + secondPokemon.name);
        } else if (firstPokemon.hitPoints <= 0) {
            System.out.println(secondPokemon.name + " DESTROYED " + firstPokemon.name);
        }
    }

    /**
     * Initializes the member Pokemons.
     * <p>
     * You do not need to modify this function.
     */
    public static void initializePokemon() {
        System.out.println("Player 1, build your Pokemon!");
        System.out.println("=================");
        firstPokemon = buildPokemon();

        System.out.println("");

        System.out.println("Player 2, build your Pokemon!");
        System.out.println("==================");
        secondPokemon = buildPokemon();
    }

    /**
     * Determines the order of which Pokemon will go first.
     * <p>
     * Uses a 2-sided die to roll for first.
     * <p>
     * You do not need to modify this function.
     */
    public static void determineOrder() {
        /*
         * Use random throw to decide ordering.
         */
        final Dice d2 = new Dice(2);
        System.out.println("\nPlayer 1 will roll a D2, to decide who goes first.");
        final int firstTurn = d2.roll();
        System.out.print("Player 1 rolls a " + firstTurn + " and will go ");
        if (firstTurn == 1) {
            System.out.print("first");
        } else {
            /*
             * Swap Pokemon for second outcome.
             */
            System.out.print("second");
            Pokemon tempPokemon = new Pokemon();
            tempPokemon = firstPokemon;
            firstPokemon = secondPokemon;
            secondPokemon = tempPokemon;
        }
    }
    /**
     * Conducts the Pokemon battle.
     * <p>
     * You do not need to modify this function.
     *
     * @param unused unused input arguments.
     */
    public static void main(final String[] unused) {
        myScan = new Scanner(System.in);
        initializePokemon();
        determineOrder();
        System.out.println("");
        boolean ifWinner = false;

        /*
         * Let the battle begin!
         */
        for (int i = 0; i < MAX_NUM_ROUNDS && !ifWinner; i++) {
            System.out.println("");
            System.out.println("Round " + (i + 1) + "!");
            System.out.println("");

            ifWinner = firstPokemon.attack(secondPokemon);
            if (!ifWinner) {
                ifWinner = secondPokemon.attack(firstPokemon);
                if (!ifWinner) {
                    printWhoIsAhead();
                }

            }
        }
        System.out.println("");

        if (!ifWinner) {
            System.out.println("It's a tie!");
        } else {
            determineWinner();
        }

        myScan.close();
    }
}
