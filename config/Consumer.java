import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
public class Consumer implements Runnable
{
	private	Socket socket;
	private	ServerSocket server;
    private String message;	
	Consumer(){}
	Consumer(String message)
	{
	 this.message=message;
	}
    public void run()
	{
	 System.out.println(Thread.currentThread().getName()+" (Start) message = "+this.message);  
     System.out.println(Thread.currentThread().getName()+" (End)" +this.message);//prints thread name which end but alive in pool
	}
    public void readMessage()
    {
		DataOutputStream dataOut;
		DataInputStream  dataIn;

 	    try
        {
         server=new ServerSocket(1201);
	     System.out.println("Listening Client");
         socket=server.accept();
         dataIn=new DataInputStream(socket.getInputStream());
         dataOut=new DataOutputStream(socket.getOutputStream());
         message="";
		 System.out.println("Reading message");
		 ExecutorService executor = Executors.newFixedThreadPool(3);//create five threads in a pool	
		 
         while(!message.equals("end"))
        {
		  message=dataIn.readUTF();
		  Runnable worker=new Consumer(message);
		  executor.execute(worker);//calling execute method of ExecutorService which will assign thread to message through run()
	    }
		 executor.shutdown();  
	    }
        catch(Exception e)
        {
          System.out.printf(e.getMessage());
        }
	}
 
}