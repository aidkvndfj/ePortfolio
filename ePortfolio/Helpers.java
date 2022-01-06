package emorse_a3.ePortfolio;

import java.util.Scanner;
import java.util.Iterator;
import java.util.ArrayList;
import java.text.DecimalFormat;

/**
 * A class filled with useful functions to help reduce repetitive code.
 */
public class Helpers {

    /**
     * The scanner object for the entire class.
     */
    private static Scanner scannerObj = new Scanner(System. in);

    /**
     * A formatter so we can return doubles to 2 decimals
     */
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Get an integer using try catch
     * @param message A message to print to the console
     * @return Return the integer inputted by the console.
     */
    public static int getInt(String message) {
        // loop until valid number is found
        while (true) {
            try { // try to return a valid int
                System.out.println(message);
                return Integer.parseInt(scannerObj.nextLine());
            } catch (Exception e) { // if a exception is thrown, print error message and try again.
                System.out.println("Invalid Number, please try again.");
            }
        }
    }

    /**
     * Get an double using a try catch
     * @param message A message to print to the console
     * @return Return the double inputted by the console.
     */
    public static double getDouble(String message) {
        // loop until a valid number is found
        while (true) {
            try { // try to return a valid double
                System.out.println(message);
                return Double.parseDouble(scannerObj.nextLine());
            } catch (Exception e) { // if a exception is thrown, print error message and try again.
                System.out.println("Invalid Number, please try again.");
            }
        }
    }

    /**
     * Return a double from a string
     * @param val the string to make into a double
     * @return Will return a double string. Will return -1 if an error occurs.
     */
    public static double strToDouble(String val) {
        try {
            return Double.parseDouble(val); // return a parseDouble of the string
        } catch (Exception e) { // if an exception occurs
            System.out.println("Could not make double, returning with -1.");
        }
        return -1;
    }

    /**
     * Return a int from a string
     * @param val the string to make into a int
     * @return Will return a int string. Will return -1 if an error occurs.
     */
    public static int strToInt(String val) {
        try {
            return Integer.parseInt(val); // return a parseInt of the string
        } catch (Exception e) { // if an exceptiong occurs
            System.out.println("Could not make int, returning with -1.");
        }
        return -1;
    }

    /**
     * Will take a list of strings and make them all lower case
     * @param wordList the list of words
     */
    public static void makeStringListLower(String[] wordList) {
        for (int i = 0; i < wordList.length; i++) { // loop through the list
            wordList[i] = wordList[i].toLowerCase(); // make each word lowercase
        }
    }

    /**
     * will compare 2 doubles
     * @param val the value
     * @param compVal the value to compare to
     * @return Will return true if within accepable range. Will return false otherwise.
     */
    public static boolean comparDouble(Double val, Double compVal) {
        return (val < compVal + 0.0001 && val > compVal - 0.0001);
    }

    /**
     * Return a double to 2 deicmal places
     * @param val the value to round
     * @return Will return the val to 2 decimal places
     */
    public static String doubleTo2DeciamlString(Double val) {
        return df.format(val);
    }
}
