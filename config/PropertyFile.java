import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
public class PropertyFile
{
  public void createFile()
  {
	Properties propertyFile = new Properties();
	OutputStream output = null;

	try 
	{
		output = new FileOutputStream("config.properties");
		propertyFile.setProperty("ip","127.0.0.1");
		propertyFile.setProperty("port", "1201");
		propertyFile.setProperty("fileName", "anas.txt");
		propertyFile.setProperty("time", "1000");
		propertyFile.store(output, null);

	} 
	catch (IOException io) 
	{
		io.printStackTrace();
	} 
	finally 
	{
		if (output != null) 
		{
			try 
			{
				output.close();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}

	}
  
  }
}