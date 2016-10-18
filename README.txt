1. first compile and run AuctionServer.java, then connect to the localhost with port no 2000.

2. After connected , press enter again (blank line) then it displays "Enter your name : " , then 
   you should enter your name, and press enter, 

3. then it displays "Enter the symbol of item  : " in a new line, then you should enter symbol (eg : FB,TXN,GOOGL) 
    and press enter.
	
4. If you entered a symbol that not exist, it displays "There is no matching item for ' Symbol name ' Please Enter valid symbol : "

5. IF you entered a valid symbol, it displays "Now you can set bids on "+symbol + " current price is "+ stocks.findPrice(symbol)+". New Bid : "

6. then you should enter a bid, if bid that you entered is less than currrnt price, it diplsys it is a invalid bid and ask for a new bid
	if you enterd valid bid , it displays you set a new bid on 'symbol' of a 'your bid' , and ask for a new bid.
	
7. in GUI there are 4 columns which includes symbol name, Name , current price and show all bids, in Symbol name column it includes given symbols 
   in lab sheet, and name column it includes names , and price column includes current price, and show all bids column includes buttons and if you 
   clicked a button it displays all bids set on that symbol in a new window, (eg if we clicked FB button it shows all valid bids set on fb)
   if client enter a valid bid, it update on price column. and if we clicked correspomding button it shows that bids were set.
   
8. and there is a text area below the tables, if client set a new valid bid it shows in the text area , time bid was set and client name and symbol and price.
