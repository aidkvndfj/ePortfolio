<!-- @format -->

# Eric Morse (1141504) Assignment 3

## 1. The Problem

Create a program that is able to keep track of stocks and mutual funds be implementing a super class investment. A user should be able to buy and sell
investments, as well as update the price of all their investment, get the portfolios gain, and search for investments. There should be an included GUI to make
navigation easier for the end user.

## 2. Assumptions and Limiations

We have assumed the following.

- You can only have 1 stock/mutual fund per symbol. (e.x. APPL can only be a stock or mutual fund, but not both)

Limitations.

- No way to see how much you've made from selling stocks.
- There is no way to spesify if you want to save your changes or not.

## 3. How To use

make sure the files are complied by first making sure you are in whatever directory emorse_a2 is in and run:

    javac emorse_a3/ePortfolio/\*.java

Then in the same directory run:

    java emorse_a3/ePortfolio.Assignment3

## 4. Testing

Knowing the last assignment and how I did, testing was more straight forward. For testing buy and sell, we should buy/sell and then use search to print all to
know that everything worked. We would test update price similarly to buy and sell by updating price and using search to see if it worked.

To test get gain, since I got perfect last time, I would test the same combonations of buy an sell in both programs to make sure that the output is correct. I
would also follow by hand incase something went wrong in both.

For load an sell, I would test by buying and selling lots of stocks and ending the program to save. Then look at the file to ensure that they saved proporly,
then load the file and use search to print them all, and make sure they loaded the same as they saved.

Finally, I would test search in a variety of ways. I would test each field individually, then 2 at a time, then all 3 at the same time to ensure that all the
searches worked.

I would also check each and every input using garbage to make sure it never crashed, and allowed the user to try again.

## 5. Improvements

- The spacing could be improved in order to make the GUI more astetically pleasing.
- Dialoge boxes that ask the user for filename and if they want to save the changes.
