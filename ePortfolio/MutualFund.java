package emorse_a3.ePortfolio;

/**
 * An extention of investment in order to do the correct math for mutual funds.
 */
public class MutualFund extends Investment {

    private static final double commision = 45.0;

    /**
     * Sets all the attributes for a mutual fund
     * @param symbol the symbol of the mutual fund
     * @param name the name of the mutual fund
     * @param quantity the initial quantity
     * @param price the inital price to buy at
     */
    public MutualFund(String symbol, String name, int quantity, double price) {
        super(symbol, name, quantity, price); // call the investment constructor
        super.setBookValue(quantity * price); // Set the book value of the inestment
    }

    /**
     * The sell command for a mutual fund. Does the correct math when selling a mutual fund.
     * @param quantity the quantity to sell
     * @param price the price to sell at
     * @return Will return the amount of money the user gets.
     */
    @Override public double sell(int quantity, double price) {
        return super.sell(quantity, price) - commision;
    }

    /**
     * update the gain
     */
    public void updateGain() {
        super.setGain((super.getPrice() * super.getQuantity() - commision) - super.getBookValue());
    }

    /**
     * Update the bookvalue.
     */
    public void updateBookvalue(int quantity, double price) {
        super.setBookValue(super.getBookValue() + (quantity * price));
    }

}
