package emorse_a3.ePortfolio;

import java.awt. *;
import java.awt.event. *;

import javax.swing. *;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener,
WindowListener {
    // The different panels
    private JPanel welcomePanel;
    private JPanel buyPanel;
    private JPanel sellPanel;
    private JPanel updatePanel;
    private JPanel gainPanel;
    private JPanel searchPanel;

    //Other Global Vars
    private transient Portfolio userPortfolio; // the portfolio of the user
    private String message; // the message to put in the message box
    private int location; // the current investment we are looking at in update

    // menu objects
    private JMenuItem buyChoice;
    private JMenuItem sellChoice;
    private JMenuItem updateChoice;
    private JMenuItem gainChoice;
    private JMenuItem searchChoice;
    private JMenuItem exitChoice;
    //Buy IO Objects
    private JButton buyResetButton;
    private JComboBox buyInvestmentChoice;
    private JTextField buySymbolField;
    private JTextField buyNameField;
    private JTextField buyQuantityField;
    private JTextField buyPriceField;
    private JButton buyButton;
    private JTextArea buyMessageArea;
    //Sell IO Objects
    private JButton sellResetButton;
    private JTextField sellSymbolField;
    private JTextField sellQuantityField;
    private JTextField sellPriceField;
    private JButton sellButton;
    private JTextArea sellMessageArea;
    //Update IO Objects
    private JTextField updateSymbolField;
    private JTextField updateNameField;
    private JTextField updatePriceField;
    private JButton updatePrevButton;
    private JButton updateNextButton;
    private JButton updateSaveButton;
    private JTextArea updateMessageArea;
    // gain IO objects
    private JTextField gainTotalGainField;
    private JTextArea gainMessageArea;
    // search IO objects
    private JTextField searchSymbolField;
    private JTextField searchKeywordsField;
    private JTextField searchLowPriceField;
    private JTextField searchHighPriceField;
    private JButton searchResetButton;
    private JButton searchButton;
    private JTextArea searchMessageArea;

    /**
     * The Setups and defines all the panels as well as other class variables
     * @param userPortfolio The portfolio to work on
     */
    public GUI(Portfolio userPortfolio) {
        super();

        this.userPortfolio = userPortfolio;
        message = "";

        setTitle("ePortfolio");
        setSize(600, 450);
        setLayout(new CardLayout());
        addWindowListener(this);

        //~~~~~~~~ setup menu ~~~~~~~~~~//
        JMenu commandMenu = new JMenu("Commands");

        buyChoice = new JMenuItem("Buy"); // define the choice
        buyChoice.addActionListener(this); // add the choice to the action listener
        commandMenu.add(buyChoice); // add the choice to the menu

        sellChoice = new JMenuItem("Sell");
        sellChoice.addActionListener(this);
        commandMenu.add(sellChoice);

        updateChoice = new JMenuItem("Update");
        updateChoice.addActionListener(this);
        commandMenu.add(updateChoice);

        gainChoice = new JMenuItem("Gain");
        gainChoice.addActionListener(this);
        commandMenu.add(gainChoice);

        searchChoice = new JMenuItem("Search");
        searchChoice.addActionListener(this);
        commandMenu.add(searchChoice);

        exitChoice = new JMenuItem("Exit");
        exitChoice.addActionListener(this);
        commandMenu.add(exitChoice);

        //setup the menu bar
        JMenuBar bar = new JMenuBar();
        bar.add(commandMenu);
        setJMenuBar(bar);

        //panel defines
        welcomePanel = new JPanel(new GridBagLayout());
        buyPanel = new JPanel(new GridBagLayout());
        sellPanel = new JPanel(new GridBagLayout());
        updatePanel = new JPanel(new GridBagLayout());
        gainPanel = new JPanel(new GridBagLayout());
        searchPanel = new JPanel(new GridBagLayout());

        //set the borders for the panels
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buyPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        sellPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        updatePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        gainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //setup the panels components
        setupPanels();

        //add the panels
        add(welcomePanel);
        add(buyPanel);
        add(sellPanel);
        add(updatePanel);
        add(gainPanel);
        add(searchPanel);

        //show the welcome panel
        hidePanels();
        welcomePanel.setVisible(true);
    }

    /**
     * All the code for setting up the panels. This allows us to reduce the size of the constructor.
     */
    private void setupPanels() {
        //~~~~~~~~~~~ Welcome Stuff ~~~~~~~~~~~//
        JLabel welcomeHeader = new JLabel("Welcome to ePortfolio"); // new header label

        JTextArea welcomeText = new JTextArea(); // the main text
        welcomeText.setText(
            "Choose a command from the \"Commands\" menu to buy or sell an investment, update prices for all investments, get gain for the portoflio, search for relevent i" +
            "nvestments, or quit the program."
        );
        // text area styling and word wraping
        welcomeText.setWrapStyleWord(true);
        welcomeText.setLineWrap(true);
        welcomeText.setBackground(UIManager.getColor("Label.background"));
        welcomeText.setBorder(UIManager.getBorder("Label.border"));
        welcomeText.setFont(UIManager.getFont("Label.font"));

        // add componenets to the welcome panel
        addComponent(welcomePanel, welcomeHeader, 1, 1, 1, 1, 150, 150);
        addComponent(welcomePanel, welcomeText, 1, 2, 1, 1, 150, 150);

        //~~~~~~~~~~~ buy panel ~~~~~~~~~~~//
        JLabel buyHeader = new JLabel("Buy an investment"); // setup new header
        addComponent(buyPanel, buyHeader, 1, 1, 1, 1, 0, 0);

        //setup for the combo box
        String[] options = {
            "Stock",
            "Mutual Fund"
        };
        JLabel investmentLabal = new JLabel("Type: ");
        buyInvestmentChoice = new JComboBox(options);
        addComponent(buyPanel, investmentLabal, 1, 2, 1, 1);
        addComponent(buyPanel, buyInvestmentChoice, 2, 2, 1, 1);

        // Symbol text box
        JLabel symbolLabel = new JLabel("Symbol: "); // setup the label
        buySymbolField = new JTextField(); // setup the text field
        addComponent(buyPanel, symbolLabel, 1, 3, 1, 1); // add the label to the panel
        addComponent(buyPanel, buySymbolField, 2, 3, 1, 1, 100, 0); // add the text field to the panel

        // name text box
        JLabel nameLabel = new JLabel("Name: ");
        buyNameField = new JTextField();
        addComponent(buyPanel, nameLabel, 1, 4, 1, 1);
        addComponent(buyPanel, buyNameField, 2, 4, 1, 1, 100, 0);

        // quantity text box
        JLabel quantityLabel = new JLabel("Quantity");
        buyQuantityField = new JTextField();
        addComponent(buyPanel, quantityLabel, 1, 5, 1, 1);
        addComponent(buyPanel, buyQuantityField, 2, 5, 1, 1, 100, 0);

        // price textbox
        JLabel priceLabel = new JLabel("Price");
        buyPriceField = new JTextField();
        addComponent(buyPanel, priceLabel, 1, 6, 1, 1);
        addComponent(buyPanel, buyPriceField, 2, 6, 1, 1, 100, 0);

        // reset button
        buyResetButton = new JButton("Reset"); // create button
        buyResetButton.addActionListener(this); // add to action listener
        addComponent(buyPanel, buyResetButton, 3, 2, 1, 1); // add to the panel

        // but buyyton
        buyButton = new JButton("Buy");
        buyButton.addActionListener(this);
        addComponent(buyPanel, buyButton, 3, 4, 1, 1);

        // mesage box at the bottom
        JLabel messageLabel = new JLabel("Message"); // setup the label
        buyMessageArea = new JTextArea(); // setup the text area
        JScrollPane buyMessagePane = new JScrollPane(buyMessageArea); // setup the pane for scrolling
        buyMessagePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS); // always have the horizontal scrollbar show
        buyMessagePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // always have the vertical scrollbar show
        addComponent(buyPanel, messageLabel, 1, 7, 1, 1); // add the label to the panel
        addComponent(buyPanel, buyMessagePane, 1, 8, 1, 1, 0, 100, true); // add the text pane to the panel.

        //~~~~~~~~~~~ sell panel ~~~~~~~~~~~//
        JLabel sellHeader = new JLabel("sell an investment");
        addComponent(sellPanel, sellHeader, 1, 1, 1, 1, 0, 0);

        symbolLabel = new JLabel("Symbol: ");
        sellSymbolField = new JTextField();
        addComponent(sellPanel, symbolLabel, 1, 2, 1, 1);
        addComponent(sellPanel, sellSymbolField, 2, 2, 1, 1, 100, 0);

        quantityLabel = new JLabel("Quantity");
        sellQuantityField = new JTextField();
        addComponent(sellPanel, quantityLabel, 1, 3, 1, 1);
        addComponent(sellPanel, sellQuantityField, 2, 3, 1, 1, 100, 0);

        priceLabel = new JLabel("Price");
        sellPriceField = new JTextField();
        addComponent(sellPanel, priceLabel, 1, 4, 1, 1);
        addComponent(sellPanel, sellPriceField, 2, 4, 1, 1, 100, 0);

        sellResetButton = new JButton("Reset");
        sellResetButton.addActionListener(this);
        addComponent(sellPanel, sellResetButton, 3, 2, 1, 1);

        sellButton = new JButton("sell");
        sellButton.addActionListener(this);
        addComponent(sellPanel, sellButton, 3, 4, 1, 1);

        messageLabel = new JLabel("Message");
        sellMessageArea = new JTextArea();
        JScrollPane sellMessagePane = new JScrollPane(sellMessageArea);
        sellMessagePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        sellMessagePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        addComponent(sellPanel, messageLabel, 1, 5, 1, 1);
        addComponent(sellPanel, sellMessagePane, 1, 6, 1, 1, 0, 100, true);

        //~~~~~~~~~~~ update panel ~~~~~~~~~~~//
        JLabel updateHeader = new JLabel("Update Investments");
        addComponent(updatePanel, updateHeader, 1, 1, 1, 1, 0, 0);

        symbolLabel = new JLabel("Symbol: ");
        updateSymbolField = new JTextField();
        updateSymbolField.setEditable(false);
        addComponent(updatePanel, symbolLabel, 1, 2, 1, 1);
        addComponent(updatePanel, updateSymbolField, 2, 2, 1, 1, 100, 0);

        nameLabel = new JLabel("Name: ");
        updateNameField = new JTextField();
        updateNameField.setEditable(false);
        addComponent(updatePanel, nameLabel, 1, 3, 1, 1);
        addComponent(updatePanel, updateNameField, 2, 3, 1, 1, 100, 0);

        priceLabel = new JLabel("Price");
        updatePriceField = new JTextField();
        addComponent(updatePanel, priceLabel, 1, 4, 1, 1);
        addComponent(updatePanel, updatePriceField, 2, 4, 1, 1, 100, 0);

        updatePrevButton = new JButton("Prev");
        updatePrevButton.addActionListener(this);
        addComponent(updatePanel, updatePrevButton, 3, 2, 1, 1);

        updateNextButton = new JButton("Next");
        updateNextButton.addActionListener(this);
        addComponent(updatePanel, updateNextButton, 3, 3, 1, 1);

        updateSaveButton = new JButton("Save");
        updateSaveButton.addActionListener(this);
        addComponent(updatePanel, updateSaveButton, 3, 4, 1, 1);

        messageLabel = new JLabel("Message");
        updateMessageArea = new JTextArea();
        JScrollPane updateMessagePane = new JScrollPane(updateMessageArea);
        updateMessagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        updateMessagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        addComponent(updatePanel, messageLabel, 1, 5, 1, 1);
        addComponent(updatePanel, updateMessagePane, 1, 6, 1, 1, 0, 100, true);

        //~~~~~~~~~~~ gain panel ~~~~~~~~~~~//
        JLabel gainHeader = new JLabel("Update Investments");
        addComponent(gainPanel, gainHeader, 1, 1, 1, 1, 0, 0);

        JLabel gainLabel = new JLabel("Total gain");
        gainTotalGainField = new JTextField();
        gainTotalGainField.setEditable(false);
        addComponent(gainPanel, gainLabel, 1, 2, 1, 1);
        addComponent(gainPanel, gainTotalGainField, 2, 2, 1, 1, 100, 0);

        JLabel individualGainLabel = new JLabel("Individual Gains");
        gainMessageArea = new JTextArea();
        JScrollPane gainMessagePane = new JScrollPane(gainMessageArea);
        gainMessagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        gainMessagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        addComponent(gainPanel, individualGainLabel, 1, 3, 1, 1);
        addComponent(gainPanel, gainMessagePane, 1, 4, 1, 1, 0, 150, true);

        //~~~~~~~~~~~ search panel ~~~~~~~~~~~//
        JLabel serachHeader = new JLabel("Searching Investments");
        addComponent(searchPanel, serachHeader, 1, 1, 1, 1);

        symbolLabel = new JLabel("Symbol");
        searchSymbolField = new JTextField();
        addComponent(searchPanel, symbolLabel, 1, 2, 1, 1);
        addComponent(searchPanel, searchSymbolField, 2, 2, 1, 1, 100, 0);

        JLabel searchKeywordLabel = new JLabel("Name Keywords");
        searchKeywordsField = new JTextField();
        addComponent(searchPanel, searchKeywordLabel, 1, 3, 1, 1);
        addComponent(searchPanel, searchKeywordsField, 2, 3, 1, 1, 100, 0);

        JLabel searchLowPriceLabel = new JLabel("Low Price");
        searchLowPriceField = new JTextField();
        addComponent(searchPanel, searchLowPriceLabel, 1, 4, 1, 1);
        addComponent(searchPanel, searchLowPriceField, 2, 4, 1, 1, 100, 0);

        JLabel searchHighPriceLabel = new JLabel("High Price");
        searchHighPriceField = new JTextField();
        addComponent(searchPanel, searchHighPriceLabel, 1, 5, 1, 1);
        addComponent(searchPanel, searchHighPriceField, 2, 5, 1, 1, 100, 0);

        searchResetButton = new JButton("Reset");
        searchResetButton.addActionListener(this);
        addComponent(searchPanel, searchResetButton, 3, 2, 1, 1);

        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        addComponent(searchPanel, searchButton, 3, 4, 1, 1);

        JLabel searchResultLabel = new JLabel("Search Results");
        searchMessageArea = new JTextArea();
        JScrollPane searchMessagePane = new JScrollPane(searchMessageArea);
        searchMessagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        searchMessagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        addComponent(searchPanel, searchResultLabel, 1, 6, 1, 1);
        addComponent(searchPanel, searchMessagePane, 1, 7, 1, 1, 0, 150, true);

    }

    @Override public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyChoice) { // Show Buy Panel
            hidePanels(); // hide all the panels
            resetBuyFields(); // reset the buy fields
            buyPanel.setVisible(true); // show the buy panel
        } else if (e.getSource() == sellChoice) { // show sell panel
            hidePanels(); // hid all the panels
            resetSellFields(); // reset the sell fields
            sellPanel.setVisible(true); // show the sell panel
        } else if (e.getSource() == updateChoice) { // show sell panel
            hidePanels(); // hide all the panels
            updatePanel.setVisible(true); // show the update panel
            showFirstUpdateInvestment(); // update the boxes to show the first investment
        } else if (e.getSource() == gainChoice) { // show the gian panel
            hidePanels(); // hide all the panels
            gainPanel.setVisible(true); // show the gain panel
            gainTotalGainField.setText(Helpers.doubleTo2DeciamlString(userPortfolio.getGain())); // set the text of the total gain box
            displayIndividualGains(); // show the individual gains in message box
        } else if (e.getSource() == searchChoice) { // show the search panel
            hidePanels(); // hide the panels
            searchPanel.setVisible(true); // show the search panel
        } else if (e.getSource() == exitChoice) { // exit the program
            Assignment3.saveInvestments(); // save the investments
            System.exit(NORMAL); // exit
        } else if (e.getSource() == buyResetButton) { // Reset the buy panel
            resetBuyFields();
        } else if (e.getSource() == sellResetButton) { // reset the sell panel
            resetSellFields();
        } else if (e.getSource() == searchResetButton) { // reset the search panel
            resetSearchFields();
        } else if (e.getSource() == updateNextButton) { // show the next investment in the update panel
            showNextUpdateInvestment();
        } else if (e.getSource() == updatePrevButton) { // show the last investment in the update panel
            showPrevUpdateInvestment();
        } else if (e.getSource() == updateSaveButton) { // save the current invest in the update panel
            saveCurrentUpdateInvestment();
        } else if (e.getSource() == buyButton) { // buy investment
            boolean isStock = false;

            // set isStock if investment is a stock
            if (buyInvestmentChoice.getSelectedItem() == "Stock") {
                isStock = true;
            }

            // Buy the investment
            try {
                // buy the investment with the info from the buy text fields
                userPortfolio.buyInvestment(isStock, buySymbolField.getText(), buyPriceField.getText(), buyQuantityField.getText(), buyNameField.getText());
                message += "Investment Added\n"; // proof it got added
                updateMessageBoxes(); // update all message boxes
                resetBuyFields(); // reset the buy fields
            } catch (Exception exception) { // if something goes wrong
                message += exception.getMessage() + "\n"; // print the error message to the message box
                updateMessageBoxes(); // update all message boxes
            }

        } else if (e.getSource() == sellButton) { // sell investment
            double priceReturn = -1;

            try {
                // sell the invetments with the info from the sell text fields
                priceReturn = userPortfolio.sellInvestment(sellSymbolField.getText(), sellPriceField.getText(), sellQuantityField.getText());
                message += "You have recieved $" + Helpers.doubleTo2DeciamlString(priceReturn) + "\n"; // put the amoutn recived from selling in the message box
                updateMessageBoxes(); // update all the messageboxes
            } catch (Exception exception) { // if something goes wrong
                message += exception.getMessage() + "\n"; // put the error message to the message box
                updateMessageBoxes(); // update all the message boxes
            }
        } else if (e.getSource() == searchButton) { // Search for investments
            searchInvestments();
        } // END OF IF
    }

    /**
     * Adds a componenet to the spesified panel with the following parameters.
     * @param panel the panel to add the comp to
     * @param comp the comp to add to the panel
     * @param gridx the x location to put the component
     * @param gridy the y location to put the component
     * @param weightx the x weight of the component
     * @param weighty the y weight of the component
     */
    private void addComponent(JPanel panel, Component comp, int gridx, int gridy, double weightx, double weighty) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST; // set the anchor

        gbc.gridx = gridx; // set the x grid
        gbc.gridy = gridy; // set the y grid

        gbc.weightx = weightx; // set the x weight
        gbc.weighty = weighty; // set the y weight

        panel.add(comp, gbc); // add the component to the panel
    }

    /**
     * Adds a componenet to the spesified panel with the following parameters.
     * @param panel the panel to add the comp to
     * @param comp the comp to add to the panel
     * @param gridx the x location to put the component
     * @param gridy the y location to put the component
     * @param weightx the x weight of the component
     * @param weighty the y weight of the component
     * @param padx the padding in the x to put on the component
     * @param pady the padding in the y to put on the component
     */
    private void addComponent(JPanel panel, Component comp, int gridx, int gridy, double weightx, double weighty, int padx, int pady) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST; // set the anchor

        gbc.gridx = gridx; // set the x grid
        gbc.gridy = gridy; // set the y grid

        gbc.weightx = weightx; // set the x weight
        gbc.weighty = weighty; // set the y weight

        gbc.ipadx = padx; // set the x padding
        gbc.ipady = pady; // set the y padding

        panel.add(comp, gbc); // add the component to the panel
    }

    /**
     * Adds a componenet to the spesified panel with the following parameters.
     * @param panel the panel to add the comp to
     * @param comp the comp to add to the panel
     * @param gridx the x location to put the component
     * @param gridy the y location to put the component
     * @param weightx the x weight of the component
     * @param weighty the y weight of the component
     * @param padx the padding in the x to put on the component
     * @param pady the padding in the y to put on the component
     * @param fill true if the component should fill the entire X axis, false otherwise.
     */
    private void addComponent(JPanel panel, Component comp, int gridx, int gridy, double weightx, double weighty, int padx, int pady, boolean fill) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST; // set the anchor

        gbc.gridx = gridx; // set the x grid
        gbc.gridy = gridy; // set the y grid

        gbc.weightx = weightx; // set the x weight
        gbc.weighty = weighty; // set the y weight

        gbc.ipadx = padx; // set the x padding
        gbc.ipady = pady; // set the y padding

        if (fill) { // if fill
            gbc.fill = GridBagConstraints.HORIZONTAL; // make the component fill the entire horizontal of the scrren
            gbc.gridwidth = 3;
        }

        panel.add(comp, gbc); // add the component to the panel
    }

    /**
     * Hides all of the panels
     */
    private void hidePanels() {
        welcomePanel.setVisible(false);
        buyPanel.setVisible(false);
        sellPanel.setVisible(false);
        updatePanel.setVisible(false);
        gainPanel.setVisible(false);
        searchPanel.setVisible(false);
    }

    /**
     * Will reset all of the text fields in the buy panel
     */
    private void resetBuyFields() {
        buySymbolField.setText("");
        buyNameField.setText("");
        buyQuantityField.setText("");
        buyPriceField.setText("");
    }

    /**
     * Will reset all of the text fields in the sell panel
     */
    private void resetSellFields() {
        sellSymbolField.setText("");
        sellQuantityField.setText("");
        sellPriceField.setText("");
    }

    /**
     * Will reset all of the text fields in the search panel
     */
    private void resetSearchFields() {
        searchSymbolField.setText("");
        searchKeywordsField.setText("");
        searchLowPriceField.setText("");
        searchHighPriceField.setText("");
    }

    /**
     * Will update the text boxes in the upate panel to show the information of the first investment
     */
    private void showFirstUpdateInvestment() {
        if (userPortfolio.getLength() == 0) { // if there are no investments
            message += "There are no investments to look at\n"; // add a warning message to the message boxes
            updateMessageBoxes(); // update all message boxes
            return; // return without doing anything
        }

        location = 0; // set the location to 0(the begining)

        refreshUpdateButtonVisibility(); // refresh the prev and next buttons

        // update the text fields with the correct informatoin
        updateSymbolField.setText(userPortfolio.getSymbolAtIndex(location));
        updateNameField.setText(userPortfolio.getNameAtIndex(location));
        updatePriceField.setText(Helpers.doubleTo2DeciamlString(userPortfolio.getIndexedInvestment(location).getPrice()));
    }

    /**
     * Save the current investments price.
     */
    private void saveCurrentUpdateInvestment() {
        double newPrice;
        try { // try to parse the price string to a double
            newPrice = Double.parseDouble(updatePriceField.getText());
            if (newPrice < 0) { // if the price is less then 0, add a error to the message box
                message += "Invalid price, please try again";
                updateMessageBoxes();
                return;
            }
        } catch (Exception e) { // if another exception occurs, add a error to the message box
            message += "Invalid price, please try again";
            updateMessageBoxes();
            return;
        }

        // set the price of the current investment
        userPortfolio.getIndexedInvestment(location).setPrice(newPrice);

        // show the current investment in the message box
        message += userPortfolio.getIndexedInvestment(location).toString();

        //update all the message boxes
        updateMessageBoxes();
    }

    /**
     * Increment the location count and show the new investments information
     */
    private void showNextUpdateInvestment() {
        location++; // increment

        refreshUpdateButtonVisibility(); // update the prev and next button visibility

        // Update the text fields accordingly
        updateSymbolField.setText(userPortfolio.getSymbolAtIndex(location));
        updateNameField.setText(userPortfolio.getNameAtIndex(location));
        updatePriceField.setText(Helpers.doubleTo2DeciamlString(userPortfolio.getIndexedInvestment(location).getPrice()));
    }

    /**
     * Decrement the location counter and show the new investments infromation
     */
    private void showPrevUpdateInvestment() {
        location--; // decrement

        refreshUpdateButtonVisibility(); // update the prev and next button visibility

        // update the text fields accordingly
        updateSymbolField.setText(userPortfolio.getSymbolAtIndex(location));
        updateNameField.setText(userPortfolio.getNameAtIndex(location));
        updatePriceField.setText(Helpers.doubleTo2DeciamlString(userPortfolio.getIndexedInvestment(location).getPrice()));

    }

    /**
     * Update the visibility of the prev and next buttons so we don't try to access investments that aren't there
     */
    private void refreshUpdateButtonVisibility() {
        updateNextButton.setVisible(location != (userPortfolio.getLength() - 1)); // if we aren't at the last investment, show the button
        updatePrevButton.setVisible(location != 0); // if we aren't at the first investment show the button
    }

    /**
     * loop through the investments and show their gains.
     */
    private void displayIndividualGains() {
        for (int i = 0; i < userPortfolio.getLength(); i++) { // loop through all investments
            message += userPortfolio.getNameAtIndex(i) + ": " + Helpers.doubleTo2DeciamlString(userPortfolio.getGainAtIndex(i)) + "\n"; // add the gain to the message box
        }
        updateMessageBoxes(); // update all message boxes
    }

    /**
     * Process what is in the search text boxes and serach the protfolio with said parameters.
     */
    private void searchInvestments() {
        ArrayList<Integer> indicies = new ArrayList<>();
        double price1;
        double price2;

        // Get the correct upper and lower prices
        try {
            if (searchLowPriceField.getText().isBlank()) { // if there is no price, set it to -1
                price1 = -1;
            } else {
                price1 = Double.parseDouble(searchLowPriceField.getText()); // make the price a double

                if (price1 < 0) { // if the price is < 0 display and error mesage
                    message += "Invalid lower price, please enter a price larger then 0\n";
                    updateMessageBoxes();
                    return;
                }
            }
        } catch (Exception e) { // if some other exception occurs, display and error message
            message += "Invalid lower price, please enter a different price\n";
            updateMessageBoxes();
            return;
        }

        try {
            if (searchHighPriceField.getText().isBlank()) { // if the field is blank, set to -1
                price2 = -1;
            } else {
                price2 = Double.parseDouble(searchHighPriceField.getText()); // make the price a double

                if (price2 < price1) { // if the price is less then the lower price
                    message += "Invalid higher price, please enter a price larger " + price1 + "\n"; // display an error message
                    updateMessageBoxes(); // update all message boxes
                    return;
                }
            }
        } catch (Exception e) { // if some other exception occurs, display an error message
            message += "Invalid higher price, please enter a different price\n";
            updateMessageBoxes();
            return;
        }

        // run the search commands with proper parameters
        indicies = userPortfolio.findInvestments(searchKeywordsField.getText(), searchSymbolField.getText(), price1, price2);

        if (indicies.isEmpty()) { // if the indicies list is empty, no investments where found
            message += "No Investments Found\n"; // add message to message box
            updateMessageBoxes(); // update all message boxes
            return;
        }

        for (int currIndex : indicies) { // looop through the indicies
            message += userPortfolio.getIndexedInvestment(currIndex).toString() + "\n"; // add each investment to the message box
        }

        updateMessageBoxes(); // update all message boxes
    }

    /**
     * Set the text of all the message boxes to what is currently inside of message
     */
    private void updateMessageBoxes() {
        buyMessageArea.setText(message);
        sellMessageArea.setText(message);
        updateMessageArea.setText(message);
        gainMessageArea.setText(message);
        searchMessageArea.setText(message);
    }

    @Override public void windowOpened(WindowEvent e) {
        Assignment3.loadInvestments();
    }

    @Override public void windowClosing(WindowEvent e) {
        Assignment3.saveInvestments();
        System.exit(0);
    }

    @Override public void windowClosed(WindowEvent e) {
        // Not needed, however won't compile without. So added but does nothing.

    }

    @Override public void windowIconified(WindowEvent e) {
        // Not needed, however won't compile without. So added but does nothing.

    }

    @Override public void windowDeiconified(WindowEvent e) {
        // Not needed, however won't compile without. So added but does nothing.

    }

    @Override public void windowActivated(WindowEvent e) {
        // Not needed, however won't compile without. So added but does nothing.

    }

    @Override public void windowDeactivated(WindowEvent e) {
        // Not needed, however won't compile without. So added but does nothing.

    }

}
