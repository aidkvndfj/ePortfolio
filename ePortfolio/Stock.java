package emorse_a3.ePortfolio;

/**
 * An extention of investment in order to do the correct math for stocks
 */
public class Stock extends Investment {

    private static final double fee = 9.99;

    /**
     * Sets all the attributes for a stock and does the correct buy math.
     * @param symbol the symbol of the stock.
     * @param name the name of the stock.
     * @param quantity the initial quantity of the stock.
     * @param price the initial price of the stock.
     */
    public Stock(String symbol, String name, int quantity, double price) {
        super(symbol, name, quantity, price); // call the investment constructor
        super.setBookValue(quantity * price + fee); // set the book value of the stock with good math
    }

    /**
     * The sell command for a stock. Does the correct math when selling a stock
     * @param quantity the quantity of the investment to sell
     * @param price the price to sell at
     * @return Will return the amount of money the user gets.
     */
    @Override public double sell(int quantity, double price) {
        return super.sell(quantity, price) - fee;
    }

    /**
     * update the gain
     */
    public void updateGain() {
        super.setGain((super.getPrice() * super.getQuantity() - fee) - super.getBookValue());
    }

    /**
     * Update the bookValue
     */
    public void updateBookvalue(int quantity, double price) {
        super.setBookValue(super.getBookValue() + (quantity * price + fee));
    }

}
