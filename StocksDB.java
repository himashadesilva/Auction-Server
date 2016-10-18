import java.io.*;
import java.util.*;

public class StocksDB{

class TwoValues {
    String sName;   //we cant keep two values on a hashmap, so i create a object that includes that two values and keep that object as a value in hash map
    String price;
   

    public TwoValues(String a, String b) {
        sName = a;
        price = b;
    }
}

public Map<String,TwoValues> stockList; 
    private String [] fields;

public StocksDB(String cvsFile, String key, String sName, String price)  { 

	FileReader fileRd=null; 
	BufferedReader reader=null; 

try { 
	    fileRd = new FileReader(cvsFile);    //read csv file
	    reader = new BufferedReader(fileRd); 

	     String header = reader.readLine(); 
	    fields = header.split(",");

	     int keyIndex = findIndexOf(key);  //find indext of key, key = "Symbol"
	    int sNameIndex = findIndexOf(sName); //sName = symbol name
	     int priceIndex = findIndexOf(price);  //price in csv file

	     if(keyIndex == -1 || sNameIndex == -1 || priceIndex == -1)  //if  key or two values does not exist
		throw new IOException("CVS file does not have data"); 

	stockList = new HashMap<String, TwoValues>();   //create new hashmap with key as the symbol and value as the object thaw keep name and price
	  
	    String [] tokens; 
	    for(String line = reader.readLine(); line != null; line = reader.readLine()) { 
		tokens = line.split(","); 
		stockList.put(tokens[keyIndex], new TwoValues(tokens[sNameIndex], tokens[priceIndex]) ); 
	    }

	    if(fileRd != null) fileRd.close();
	    if(reader != null) reader.close();
	}
	catch (IOException e) { 
	    System.out.println(e);
	    System.exit(-1); 
	} catch (ArrayIndexOutOfBoundsException e) { 
	    System.out.println("Malformed CSV file");
	    System.out.println(e);
	}



}

 private int findIndexOf(String key) { 
	for(int i=0; i < fields.length; i++)     //method to find index of key, velue
	    if(fields[i].equals(key)) return i; 
	return -1; 
    }
 
    public String findSName(String key) {   //method to find sname for given key
	
	return stockList.get(key).sName;   //for a given key , hashmap give a ojbect , to find name we should get object.sNAme
    }

    public String findPrice(String key) {   //same as above
	return stockList.get(key).price; 
    }

}