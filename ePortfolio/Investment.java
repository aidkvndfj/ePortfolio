package emorse_a3.ePortfolio;

/**
 * The super class for investments. ALlows you to buy, sell, and keep track of all types of investments.
 */
public abstract class Investment {
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
    private double gain;

    /**
     * Initalizes most variables
     * @param symbol the symbol of the new investment.
     * @param name the name of the new investment
     * @param quantity the initial quantity to buy
     * @param price the initial price of the investment
     */
    protected Investment(String symbol, String name, int quantity, double price) {
        this.symbol = symbol;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.gain = 0;
    }

    /**
     * Buys a spesified amount of the investment at a given price. Will update the price, quantity, and bookValue accordingly.
     * @param quantity the quantity to buy
     * @param price the price to buy at
     */
    public void buy(int quantity, double price) {
        this.quantity += quantity;
        this.price = price;
        updateBookvalue(quantity, price);
    }

    /**
     * will sell a spesified amount of an investment at a given price. Will update the price, quantity, and book value accordingy.
     * @param quantity the  quantity to sell
     * @param price the price to sell at
     * @return Will return the amount that the user gets from selling stuff.
     */
    public double sell(int quantity, double price) {
        if (quantity > this.quantity) { // if the quantity to sell is more then current quantity, don't sell
            System.err.println("Trying to sell to much of " + this.symbol + " you only have " + this.quantity + " exiting.");
            return -1.0;
        }

        this.quantity -= quantity; // update quantity
        this.bookValue *= ((double)this.quantity / (this.quantity + quantity)); // updaate book val
        this.price = price; // update price

        return price * quantity; // return the price * quantity
    }

    /**
     * Update the gain of the investment with the algorithm defined in sub-classes
     */
    public abstract void updateGain();

    /**
     * Update the bookValue of the investment with the algorithm defined in sub-classes
     * @param quantity the quantity of the investment
     * @param price the price of the investment
     */
    public abstract void updateBookvalue(int quantity, double price);

    //~~~~~~~ getters ~~~~~~~//
    /**
     * Returns the gain of the currect investment
     * @return Will return the gain of the current investment.
     */
    public double getGain() {
        return this.gain;
    }

    /**
     * Will return the investment symbol.
     * @return Will return the investment symbol.
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Will return the investment name.
     * @return Will return the investment name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Will return the current quantity.
     * @return Will return the investment quantity.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Will return the current price.
     * @return Will return the investment price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Will return the current book value.
     * @return Will return the investment book value
     */
    public double getBookValue() {
        return this.bookValue;
    }

    //~~~~~~~ setters ~~~~~~~//
    /**
     * Sets the investment book value to a new one.
     * @param val the new book val;
     */
    public void setBookValue(double val) {
        this.bookValue = val;
    }

    /**
     * Will set the gain of the investment
     * @param gain the gain to set it to
     */
    public void setGain(double gain) {
        this.gain = gain;
    }

    /**
     * Sets the investment price to a new one.
     * @param price the new price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Will return the attributes as a string.
     * @return Return the attributes as a string.
     */
    public String toString() {
        return (
            "Symbol " + this.symbol + "\nName " + this.name + "\nPrice " + Helpers.doubleTo2DeciamlString(this.price) + "\nQuantity " + this.quantity + "\nBook Value " +
                Helpers.doubleTo2DeciamlString(this.bookValue) + "\n"
        );
    }
}
