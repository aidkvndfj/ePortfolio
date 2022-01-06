package emorse_a3.ePortfolio;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The main class for this assignment
 */
public class Assignment3 {
    private static Portfolio userPortfolio;
    private static String fileName;

    public static void main(String[] args) {
        userPortfolio = new Portfolio();

        if (args.length == 1) { // if we have a filename, set the filename to it
            fileName = args[0];
        }

        // Run the GUI
        GUI gui = new GUI(userPortfolio);
        gui.setVisible(true);
    }

    /**
     * Will load the investment from a file using class var fileName
     */
    public static void loadInvestments() {
        if (fileName.isBlank()) {
            return;
        }
        File inFile;
        Scanner fileScanner;
        String[] splitString;

        int counter = 0;

        String type = "";
        String symbol = "";
        String name = "";
        double price = 0;
        double bookValue = 0;
        int quantity = 0;

        try {
            inFile = new File(fileName); // create an io stream
            fileScanner = new Scanner(inFile); // create the sacnner

            while (fileScanner.hasNextLine()) { // loop till end of file

                splitString = fileScanner.nextLine().split("\"");
                if (splitString.length == 1) { // if the line is empty add the investment and reset vars
                    userPortfolio.addInvestment(type, symbol, name, price, quantity, bookValue);
                    type = "";
                    symbol = "";
                    name = "";
                    price = 0;
                    bookValue = 0;
                    quantity = 0;
                    counter = 0;
                } else {
                    switch (counter) { // given the counter, set the correct value
                        case 0: // first time set the type
                            type = splitString[1];
                            break;
                        case 1: // 2nd time set the symbol
                            symbol = splitString[1];
                            break;
                        case 2: // 3rd time set the name
                            name = splitString[1];
                            break;
                        case 3: // 4th time set the quantity
                            quantity = Helpers.strToInt(splitString[1]);
                            break;
                        case 4: // 5th time set the price
                            price = Helpers.strToDouble(splitString[1]);
                            break;
                        case 5: // 6th time set the book value
                            bookValue = Helpers.strToDouble(splitString[1]);
                            break;
                        default: // if something goes wrong we know about it.
                            System.err.println("Something went wrong in load, we shouldn't ever get here.");
                    }
                    counter++; // increment counter
                }
            }

            fileScanner.close(); // close the scanner.
        } catch (Exception e) {
            System.err.println("Could not open file \"" + fileName + "\"");
        }

    }

    /**
     * will save the current investments to a file.
     */
    public static void saveInvestments() {
        if (fileName.isBlank()) {
            return;
        }
        PrintWriter fileWriter;
        Investment currInvestment;
        String investmentType;
        int currIndex = 0;

        try {
            fileWriter = new PrintWriter(fileName, "UTF-8"); // define the print writer for output
            currInvestment = userPortfolio.getIndexedInvestment(currIndex);

            while (currInvestment != null) {
                if (currInvestment instanceof Stock) {
                    investmentType = "stock";
                } else {
                    investmentType = "mutualfund";
                }

                fileWriter.println("type = \"" + investmentType + "\"");
                fileWriter.println("symbol = \"" + currInvestment.getSymbol() + "\"");
                fileWriter.println("name = \"" + currInvestment.getName() + "\"");
                fileWriter.println("quantity = \"" + currInvestment.getQuantity() + "\"");
                fileWriter.println("price = \"" + currInvestment.getPrice() + "\"");
                fileWriter.println("bookValue = \"" + currInvestment.getBookValue() + "\"");
                fileWriter.println();

                currIndex++;
                currInvestment = userPortfolio.getIndexedInvestment(currIndex);
            }

            fileWriter.close();
        } catch (Exception e) {
            System.err.println("Could not write to file \"" + fileName + "\"");
        }

    }
}