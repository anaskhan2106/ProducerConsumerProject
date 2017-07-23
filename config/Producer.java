import java.io.*;
import java.net.Socket;
import java.util.*;
public class Producer
{
    private Socket socket;
	private String ipNo;
	private int portNo;
    private String fileName;
	private String port;
	private int time;
    public void readProprtyFile()
	{
	    Properties propertyFile = new Properties();
   	    InputStream input = null;
		int socketNo;
		try 
		{
		  input = new FileInputStream("config.properties");
		  propertyFile.load(input);		
		  ipNo = propertyFile.getProperty("ip");
		  portNo = Integer.parseInt(propertyFile.getProperty("port"));
		  port=propertyFile.getProperty("port");
          fileName = propertyFile.getProperty("fileName");
          time=Integer.parseInt(propertyFile.getProperty("port"));		  
		  System.out.println("Ip of server is:" +ipNo);
	      System.out.println("Port Connected is:" +portNo);
		  System.out.println("File name is:" +fileName);
	    }
	    catch (IOException ex)
    	{
	    	ex.printStackTrace();
	    } 
	    finally 
	    {
		    if(input!= null) 
		    {
			 try 
			 {
			   input.close();
		     }
			 catch(IOException e) 
			 {
			   e.printStackTrace();
			 }
	    	}
        }
	}
	public void getConnection()
	{		
	  try
      {
	    socket  = new Socket(ipNo,1201);
	  }
        catch(Exception e)
        {
          System.out.printf(e.getMessage());
        }	  
	}
	
    public void sendMessage()
	{
		DataOutputStream dataOut;
		DataInputStream  dataIn;
		try
        {
		  String line1,line2;    
		 
		  dataIn  = new DataInputStream(socket.getInputStream());
          dataOut = new DataOutputStream(socket.getOutputStream());
		  File file=new File(fileName);
          Scanner scanner = new Scanner(file); 
       	 
         while(scanner.hasNextLine())
		 {
            line1=scanner.nextLine()+","+scanner.nextLine(); 
            dataOut.writeUTF(line1);
			System.out.println(line1);
			Thread.sleep(time);	
         }  
		    dataOut.writeUTF("end");
		    System.out.println("message read by server");
        }			
            catch(Exception e)
            {
               System.out.printf(e.getMessage());
            }
		    try 
			{
		      socket.close();
			}
			catch (Exception e) 
		    {
			  e.printStackTrace();
		    }
    }
}