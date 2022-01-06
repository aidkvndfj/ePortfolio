package emorse_a3.ePortfolio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Portfolio {
    private ArrayList<Investment> investments;
    private HashMap<String, ArrayList<Integer>> investmentMap;

    /**
     * Initalizes the ArrayList and HashMap.
     */
    public Portfolio() {
        //Initalization
        this.investments = new ArrayList<>();
        this.investmentMap = new HashMap<>();
    }

    //~~~~~~~~~~ Public Funcs ~~~~~~~~~~//

    /**
     * Runs the buy command to buy a investment that already exsists at the correction location, with the correct parameters.
     * @param symbol the symbol of the investment.
     * @param price the price to buy the investment at.
     * @param amount the amount of the investment to buy.
     */
    public void buyInvestment(String symbol, double price, int amount) {
        int location = investementLocation(symbol); // get the location
        this.investments.get(location).buy(amount, price);
    }

    /**
     * Runs the buy command to buy a new investment with the correct parameters.
     * @param isStock to know if the investment is a stock or not.
     * @param symbol the symbol of the stock.
     * @param price the initial price of the stock.
     * @param amount the initial amount of the stock to buy.
     * @param name the name of the stock.
     * @throws CustomException throws a custom exception if a parameter doesn't fit
     */
    public void buyInvestment(boolean isStock, String symbol, String price, String amount, String name)throws CustomException {
        double investmentPrice;
        int investmentQuantity;

        //make sure there is a symbol
        if (symbol.isBlank()) {
            throw new CustomException("Please indicate the investment symbol");
        }

        // check the price to make sure it is valid
        try {
            investmentPrice = Double.parseDouble(price);
        } catch (Exception e) {
            throw new CustomException("Invalid price, please enter a different price");
        }
        if (investmentPrice < 0) { // throw exception if price less then 0
            throw new CustomException("Price less then 0, please enter a different price");
        }

        // check the quantity to make sure it is valid
        try {
            investmentQuantity = Integer.parseInt(amount);
        } catch (Exception e) {
            throw new CustomException("Invalid quantity, please enter a different quantity");
        }
        if (investmentQuantity <= 0) { // throw exception if quantity is less then or = to 0
            throw new CustomException("Quantity less then 0, please enter a different quantity");
        }

        // if the location isn't -1 then we already have some, use other buy command.
        if (investementLocation(symbol) != -1) {
            buyInvestment(symbol, investmentPrice, investmentQuantity);
            return;
        }

        //check to make sure there is a name
        if (name.isBlank()) {
            throw new CustomException("Please include a investment name");
        }

        if (isStock) { // add a new stock if isstock
            this.investments.add(new Stock(symbol, name, investmentQuantity, investmentPrice));
        } else { // add a new mutual fund if not isstock
            this.investments.add(new MutualFund(symbol, name, investmentQuantity, investmentPrice));
        }

        //add the new investment to the hashmap
        addToHashMap(this.investments.size() - 1, name);
    }

    /**
     * Sells an amount of symbol investment at price, price.
     * @param symbol the symbol of the investment.
     * @param price the price to sell at.
     * @param amount the amount to sell.
     * @throws CustomException throws a custom exception if a parameter doesn't fit
     */
    public double sellInvestment(String symbol, String price, String amount)throws CustomException {
        double sellPrice;
        int sellAmount;
        int location = investementLocation(symbol);
        double amountRecieved;

        if (symbol.isBlank()) {
            throw new CustomException("Please enter a symbol");
        }

        if (location == -1) { // if location is -1, stock doesn't exsist, thorw exception
            throw new CustomException("You don't have any " + symbol);
        }

        try { // make sure quantity is valid
            sellAmount = Integer.parseInt(amount);
        } catch (Exception e) {
            throw new CustomException("Invalid Quantity");
        }
        if (sellAmount > investments.get(location).getQuantity()) { // if the quantity is more then the amoutn we have, throw excepiton.
            throw new CustomException(
                "Amount to sell greater then current quantity, you only have " + investments.get(location).getQuantity() + " " + investments.get(location).getName()
            );
        }

        try { // make sure price is valid
            sellPrice = Double.parseDouble(price);
        } catch (Exception e) {
            throw new CustomException("Invalid Price");
        }
        if (sellPrice < 0) { // if price is less then 0, throw expception
            throw new CustomException("Sell price less then 0");
        }

        // run the sell comannd at the investment at location and set the amount recived to the retun value.
        amountRecieved = this.investments.get(location).sell(sellAmount, sellPrice);

        // if the quantity is 0, remove the stock
        if (this.investments.get(location).getQuantity() <= 0) {
            removeFromHashMap(location); // remove from the hashmap
            this.investments.remove(this.investments.get(location)); // remove from the arraylist.
        }

        return amountRecieved;
    }

    /**
     * Given the 6 parameters of a investment, create a new investment of type type, and add it to the array list.
     * @param type the type of investment
     * @param symbol the symbol of the inevstment
     * @param name the name of the investment
     * @param price the current price of the investment
     * @param quantity the current quantity of the investment
     * @param bookValue the current bookvalue of the investment
     */
    public void addInvestment(String type, String symbol, String name, double price, int quantity, double bookValue) {
        if (type.equalsIgnoreCase("stock")) { // if a stock
            investments.add(new Stock(symbol, name, quantity, price)); // add new stock to arraylist
            investments.get(investementLocation(symbol)).setBookValue(bookValue); // set the book value
        } else if (type.equalsIgnoreCase("mutualfund")) { // if mutual fund
            investments.add(new MutualFund(symbol, name, quantity, price)); // add new fund to arraylist
            investments.get(investementLocation(symbol)).setBookValue(bookValue); // set the bookvalue
        }
        // add the new investment to the hashmap.
        addToHashMap(investementLocation(symbol), name);
    }

    /**
     * Given a string of keywrods, a symbol, and/or price range, find all investments that meet all criteria.
     * @param keywords the keywords to look for.
     * @param symbol the symbol of the investment.
     * @param price1 the min price in the range.
     * @param price2 the max price in the range.
     */
    public ArrayList<Integer> findInvestments(String keywords, String symbol, double price1, double price2) {
        ArrayList<Integer> investmentsToPrint;
        boolean checkPrice = (price1 != -1 || price2 != -1);

        // do keywords
        investmentsToPrint = getListOfInvestments(keywords);

        // if there are no keywords and the keywrods string isn't empty
        if (investmentsToPrint == null && !keywords.equalsIgnoreCase("")) {
            // There are no investments found, print such.
            return investmentsToPrint;
        }

        // if there are no keywords
        if (keywords.isBlank()) {
            // make a new arraylist
            investmentsToPrint = new ArrayList<>();
            // loop through the investments, and add the index to the new arraylist
            for (int i = 0; i < this.investments.size(); i++) {
                investmentsToPrint.add(i);
            }
        }

        // iterate through the investments list
        for (Iterator<Integer> iterator = investmentsToPrint.iterator(); iterator.hasNext();) {
            Integer val = iterator.next();

            // if the symbol isn't empty, and the current inestment doesn't match the symbol
            if (!symbol.equalsIgnoreCase("") && !this.investments.get(val).getSymbol().equalsIgnoreCase(symbol)) {
                iterator.remove(); // remove
            } else if (checkPrice) {
                if (Helpers.comparDouble(price1, -1.0)) { // if price 1 is -1
                    if (this.investments.get(val).getPrice() > price2) { // if the price of the current investment is > price 2
                        iterator.remove(); // remove
                    }
                } else if (Helpers.comparDouble(price2, -1.0)) { // if price 2 is -1
                    if (this.investments.get(val).getPrice() < price1) { // check if the price of the current invesment is < price 1
                        iterator.remove(); // remove
                    }
                } else if (this.investments.get(val).getPrice() < price1 || this.investments.get(val).getPrice() > price2) { // check the curr price to price 1 and prie 2
                    iterator.remove(); // if it is outside the range, remove.
                }
            }
        }

        return investmentsToPrint;
    }

    /**
     * will update the price of an investment at index with price price.
     * @param index the index of the investment to update
     * @param price the price to update it to
     */
    public void updatePrice(int index, double price) {
        investments.get(index).setPrice(price);
    }

    /**
     * Is used to check if the portfolio has a certain investment based on given symbol
     * @param symbol the symbol to looks for.
     * @return Will return true if there is a stock in the portoflio with the symbol given. Will return false otherwise.
     */
    public boolean hasInvestment(String symbol) {
        for (Investment currInvestment : investments) { // loop through investments
            if (currInvestment.getSymbol().equalsIgnoreCase(symbol)) { // if the curr investment equals the symbol
                return true;
            }
        }
        return false;
    }

    /**
     * Given a symbol, will return the location of the investment as an integer.
     * @param symbol the symbol to look for.
     * @return Will return the integer location of the investment symbol. If not found will return -1.
     */
    private int investementLocation(String symbol) {
        int index = 0;

        for (Investment currInvestment : investments) { // loop through investents
            if (currInvestment.getSymbol().equalsIgnoreCase(symbol)) { // checks if the curr investment is equal to the symbol.
                return index;
            }
            index++; // increment
        }

        // return -1 if we didn't return before.
        return -1;
    }

    /**
     * Will get the symbol at a given index.
     * @param index the index to find the symbol at.
     * @return Will return a String symbol if found. Will return null otherwise.
     */
    public String getSymbolAtIndex(int index) {
        try {
            return investments.get(index).getSymbol(); // return the symbol
        } catch (Exception e) { // if we get and exception return null
            return null;
        }
    }

    /**
     * Will get the name at a given index
     * @param index the index to find the name at
     * @return Will return a String name if found. Will return null otherwise
     */
    public String getNameAtIndex(int index) {
        try {
            return investments.get(index).getName();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Will get the price at a given index
     * @param index the index to find the price at
     * @return Will return a double price if found. Will return -1 otherwise
     */
    public double getPriceAtIndex(int index) {
        try {
            return investments.get(index).getPrice();
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Will get the gain at a given index
     * @param index the index to find the gain at
     * @return Will return a double gain if found. Will return -1 otherwise
     */
    public double getGainAtIndex(int index) {
        try {
            return investments.get(index).getGain();
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Will return the investment at a given index.
     * @param index the index to return the investment from.
     * @return Will return an investment at the index provided. Will return null if an exception occurs.
     */
    public Investment getIndexedInvestment(int index) {
        try {
            return investments.get(index); // return the investment
        } catch (Exception e) { // if we get and exception, return null.s
            return null;
        }
    }

    /**
     * Calculate the total gain of the portfolio by updating then getting the gain of each individual investment.
     * @return Return the cumulative gain of all the investments.
     */
    public double getGain() {
        double gain = 0.0;

        // loop through investments
        for (Investment currInvestment : this.investments) {
            currInvestment.updateGain(); // update gain
            gain += currInvestment.getGain(); // get the gain
        }

        //return the gain
        return gain;
    }

    /**
     * Will return the length of all the investments
     * @return The length of the investments ArrayList
     */
    public int getLength() {
        return investments.size();
    }

    /**
     * loop through all the investments and print their information
     */
    public void printAll() {
        for (Investment currInvestment : investments) {
            System.out.println(currInvestment.toString());
        }
    }

    //~~~~~~~~~~ Private Funcs ~~~~~~~~~~//
    /**
     * Will add the index of an investment to the hashmap at every key in keywords.
     * @param index the index of an investment
     * @param keywords the keywords to add the investment to
     */
    private void addToHashMap(Integer index, String keywords) {
        ArrayList<Integer> tempList;
        String[] splitWords = keywords.split("[ ]+");
        Helpers.makeStringListLower(splitWords); // make all words lowercase

        for (String currWord : splitWords) { // loop through the words
            tempList = this.investmentMap.get(currWord); // get the list at the current word

            if (tempList == null) { // if the list doesn't exsist
                tempList = new ArrayList<>(); // make a new list
            }

            // add the index to the list and put it to the hashmap at the current key(word).
            tempList.add(index);
            this.investmentMap.put(currWord, tempList);
        }
    }

    /**
     * Will remove and index from the hashmap, and update all indexes greater then the index in order to account for the indexes shift by 1 when we remove and investment.
     * @param index and Integer index
     */
    private void removeFromHashMap(Integer index) {
        for (ArrayList<Integer> currList : investmentMap.values()) { // loop through every array list at every key in the hashmap
            currList.remove(index); // remove the index from the current list
            for (int i = 0; i < currList.size(); i++) { // loop through the list
                if (currList.get(i) > index) { // if the current index is greater then the index we removed
                    currList.set(i, currList.get(i) - 1); // subract by 1 to account for the index no longer exsisting.
                }
            }
        }
    }

    /**
     * Gets a list of investments that matches some keywords.
     * @param keywords the keywords to look for.
     * @return Will return and ArrayList of Integers that are indexes that match all keywords.
     */
    private ArrayList<Integer> getListOfInvestments(String keywords) {
        ArrayList<Integer> tempList = null;
        ArrayList<Integer> listOfIndexs = null;

        String[] splitWords = keywords.split("[ ]+");
        Helpers.makeStringListLower(splitWords);

        for (String currWord : splitWords) { // loop through the keywords
            if (listOfIndexs == null) { // if the list of indexes is null
                listOfIndexs = this.investmentMap.get(currWord); // get the list at the current key
            } else { // otherwise
                tempList = this.investmentMap.get(currWord); // get the list at the current key and set it to a temp list
                for (Integer currIndex : listOfIndexs) { // loop through the list of indexes
                    if (!tempList.contains(currIndex)) { // if the current index isn't in the temp list
                        listOfIndexs.remove(currIndex); // remove the index from the list of indexes
                    }
                }
            }
        }

        return listOfIndexs;
    }

}
