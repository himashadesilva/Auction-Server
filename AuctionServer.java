import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import java.util.*; 

public class AuctionServer{

	public static ArrayList<Client> clientList = new ArrayList<>();   //array list to keep clients objects
    public static final int BASE_PORT = 2000;
    public StocksDB stockDetails=null;     
    
    private static ServerSocket serverSocket; 
    private static int socketNumber; 

       
    public AuctionServer(StocksDB stock) throws IOException { 
		serverSocket = new ServerSocket(BASE_PORT);   //create new server socket
		
		this.stockDetails = stock; 
		
    }

    public void server_loop() throws IOException { 
		while(true) {                                   //server is listning
		    Socket socket = serverSocket.accept(); 	    
		    Client c = new Client(socket,stockDetails);
		    Thread worker = new Thread(c);  //create new client
		    clientList.add(c);
		    worker.start(); 	    
		}
    }

   

    public static void main(String [] args) throws IOException { 
	    StocksDB stockDetails = new StocksDB("stocks.csv","Symbol","Security Name","Price ");  //create StockDB object , by passing csv file name and key and two values name
		AuctionServer server = new AuctionServer(stockDetails);   //call Auction server constructor , passing created object of StocksDB, from StockDB obj we can find values to given symbol
		
		JFrame frame = new JFrame("Bid results");  //create new gui
        frame.setSize(600, 600);       
  		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  		frame.add(new Display(server));   //call display constructor, 

  		frame.pack();
        frame.setVisible(true);

		server.server_loop();  

    }





}