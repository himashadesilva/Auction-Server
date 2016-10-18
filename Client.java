import java.net.*;
import java.io.*;
import java.util.*;
public class Client implements Runnable {

	private Socket connectionSocket; 
	private StocksDB stocks;   //to call symbols and values
    public ArrayList<Double> bids;  //to keep bids ,that one client set
    public int no_of_bids;   //index of arraylist bids
   	public String  clientName , symbol=null;   //keep client name and symble of item thst client bids
    public boolean newbid=false;

    public Client(Socket socket, StocksDB stocks) throws IOException { 
		connectionSocket = socket; 
		bids = new ArrayList<>(); //create new arraylist
		this.stocks = stocks;
    }
	
	public void run(){
		try {      

		    BufferedReader in = new BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream())); //to get input stream
		    PrintWriter out = new PrintWriter(new OutputStreamWriter(this.connectionSocket.getOutputStream()));	  //to give output stream
		    String line;
		    int count =0; 
		    no_of_bids = 0;
		    //bids = new ArrayList<Integer>();
		    out.print("Enter your name : ");  //when connected, client should press enter , then ask from client for name
		    for(line = in.readLine(); line != null && !line.equals("quit"); line = in.readLine()) { 
				if(count==1){  //when enter second line ,it is client name, 
					clientName = line;
					out.print("\nEnter the symbol of item  : "); //after get client name, ask for symbol of item, that client willing to bid on
				
				}
				if(count==2){
					
					symbol = line;
					 if (stocks.stockList.containsKey(symbol)){  //if client entered valid symbol
					 out.print("\nNow you can set bids on "+symbol + " current price is "+ stocks.findPrice(symbol)+". New Bid : ");  
					 
					 }
					 else{  //if client enterd wrong symbol
					 	out.print("\nThere is no matching item for "+symbol + ", Please Enter valid symbol : ");
					 	count = 1;
					 }
				}
				if(count>2){
					if(count ==3){
						if(Double.compare(Double.parseDouble(stocks.findPrice(symbol)),Double.parseDouble(line))<0 ){  //if client enterd valid bid,(bid greater than currrnt price)
						bids.add(Double.parseDouble(line));
						newbid = true;
						no_of_bids++;	
						 out.print("\nYou set a bid of "+line+" on "+symbol+". New Bid : ");
						 

						}
						else{
						out.print("\nYour Bid is not valid, current price is "+ stocks.findPrice(symbol)+". New Bid : ");
						count--;	
						}
					}

					if(count>3){
						if(Double.compare(bids.get(no_of_bids-1),Double.parseDouble(line))<0 ) { //if client enterd valid bid,(bid greater than currrnt price)
						bids.add(Double.parseDouble(line));
						newbid = true;
						no_of_bids++;	
						out.print("\nYou set a bid of "+line+" on "+symbol + ". New Bid : ");

						}
						else{
						out.print("\nYour Bid is not valid, current price is "+ bids.get(no_of_bids-1)+". New Bid : ");
						count--;	
						}
					}
					
					
				}


				 
				//out.print(line + "\n"); 
				out.flush();
				count++;	    
		    } 
		}
		catch (IOException e) { 
		    System.out.println(e); 
		}finally { 	    
		    try{this.connectionSocket.close(); }catch(Exception e){}	
		} 
	}
}