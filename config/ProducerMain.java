import java.io.*;
import java.net.Socket;
import java.util.*;
import java.io.PrintWriter;
public class ProducerMain
{
		
	public static void main(String args[]) 
	{
      Producer producer=new Producer();
	  producer.getConnection();
      producer.readProprtyFile();
	  producer.sendMessage(); 
	}			  
}