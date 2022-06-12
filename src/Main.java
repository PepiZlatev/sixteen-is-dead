import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter player's name: ");
        String playerName = scan.nextLine();
        String computerName = "Computer";


        System.out.println("----------------------------------------------------------------");
        System.out.println("Game Start");
        System.out.println("----------------------------------------------------------------");

        //Calculate the sum of all dice rolls done by the computer
        int computerSum = computerRollDice(computerName);
        System.out.println();
        System.out.printf("Computer done rolling, next is: %s!%n", playerName);
        System.out.println();

        String input = "";
        //Calculate the sum of all dice rolls done by the player
        int playerSum = playerRollDice(scan, playerName, input, computerSum);

        System.out.println();
        //Print the result of the game
        printResult(playerName, computerName, computerSum, playerSum);

    }

    /**
     * Method that calculates the sum of all dice rolls done by the computer
     *
     * @param computerName Computer
     * @return the sum of all dice rolls
     */
    private static int computerRollDice(String computerName) {
        int sum = 0;
        Random random = new Random();
        while (true) {
            sum = getSumOfRolls(computerName, sum);

            // If the sum of all dice rolls is bigger than 10, then the computer
            // can decide either to roll again or not with probability of 1/2
            // If the drawProbability == 1 then the computer rolls once more
            if (sum >= 10) {
                int drawProbability = random.nextInt(0, 2);
                if (drawProbability == 0) {
                    break;
                }
            }

            System.out.printf("%s rolls again!%n", computerName);
        }
        return sum;
    }

    /**
     * Method that calculates the sum of all dice rolls
     *
     * @param name Name of the player/computer
     * @param sum  sum of all dice rolls
     * @return sum of all dice rolls
     */
    private static int getSumOfRolls(String name, int sum) {
        Random random = new Random();
        int currentNumber;
        currentNumber = random.nextInt(1, 6);

        System.out.println("The rolled number is: " + currentNumber);
        sum += currentNumber;
        System.out.println("Total sum of all dice rolls: " + sum);

        if (sum >= 16) {
            System.out.printf("%s has lost the game due to sum bigger than 16!%n", name);
            System.exit(0);
        }
        return sum;
    }

    /**
     * Method that calculates the sum of all dice rolls done by the player
     *
     * @param scan        Scanner Object
     * @param playerName  name of the player
     * @param input       letter input for either continuing the rolls or stop
     * @param computerSum sum of all dice rolls made by the computer
     * @return sum of all dice rolls
     */
    private static int playerRollDice(Scanner scan, String playerName, String input, int computerSum) {
        int sum = 0;
        while (!"s".equalsIgnoreCase(input)) {
            sum = getSumOfRolls(playerName, sum);

            if (sum > computerSum) {
                input = checkIfPlayerWins(scan);
            } else {
                System.out.println("Enter \"c\" to continue or \"s\" to stop rolling:");
                input = scan.nextLine();
                input = checkInputLetter(scan, input);
            }
        }
        return sum;
    }

    /**
     * Method that tells the player that he/she is the winner
     *
     * @param scan Scanner Object
     * @return input
     */
    private static String checkIfPlayerWins(Scanner scan) {
        System.out.println("You are the winner for the moment.");
        System.out.println("Enter \"c\" to continue or \"s\" to stop rolling:");

        String input = scan.nextLine();
        return checkInputLetter(scan, input);
    }

    /**
     * Method that checks if the right type of input was made
     *
     * @param scan  Scanner Object
     * @param input input letter
     * @return input letter
     */
    private static String checkInputLetter(Scanner scan, String input) {
        while (!input.equalsIgnoreCase("c") && !input.equalsIgnoreCase("s")) {
            System.out.println("Please re-enter \"c\" to continue or \"s\" to stop rolling:");
            input = scan.nextLine();
        }
        return input;
    }

    /**
     * Method that prints the end result
     *
     * @param playerName   name of the player
     * @param computerName name of the computer
     * @param computerSum  sum of all dice rolls made by the computer
     * @param playerSum    sum of all dice rolls made by the player
     */
    private static void printResult(String playerName, String computerName, int computerSum, int playerSum) {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        if (computerSum > playerSum) {
            System.out.printf("%s has lost, %s has won!%n", playerName, computerName);
        } else if (playerSum > computerSum) {
            System.out.printf("%s has lost, %s has won!%n", computerName, playerName);
        } else {
            System.out.println("Draw!");
        }

        System.out.println("Result: ");
        System.out.printf("Computer %d - %d %s%n", computerSum, playerSum, playerName);
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    }
}
