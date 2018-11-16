import java.net.*;
import java.util.ArrayList;
import java.io.*;

import javax.swing.JOptionPane;
 
public class Server implements Runnable 
{
    	 BufferedReader in;
    	 PrintWriter out;
    	 static String string;
    	 static ArrayList test;
    	 
    	 public Server ()
    	 {
    		 System.out.println("Server Ready to recieve message");
    	 
		        ServerSocket serverSocket = null;//sets up the server socket
		        try 
		        {
		            serverSocket = new ServerSocket(2010);
		        } 
		        catch (IOException e) 
		        {
		            System.err.println("Could not listen on port: 2010.");
		            System.exit(1);
		        }
		 
		        Socket clientSocket = null;//tried to open a client socket
		        try 
		        {
		            clientSocket = serverSocket.accept();
		        } 
		        catch (IOException e) 
		        {
		            System.err.println("Accept failed.");
		            System.exit(1);
		        }
		        
		        try 
		        {
					out= new PrintWriter(clientSocket.getOutputStream(), true);
				} 
		        catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        try 
		        {
					in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				} 
		        catch (IOException e) 
		        {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        string = null;
    	 

			try 
			{
				string = in.readLine();//reads the information sent
				string.endsWith("Finished");//tests if it ends in finished
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null,"Path Has Been Recieved");
			System.out.println(string); // prints the recieved string to the console
			
			
    	 out.close(); // closes the print writer
    	 
    	 
        try //closes the sockets
        {
			in.close();
		} catch (IOException e) 
		{
			System.out.println("Reader Closed");
			e.printStackTrace();
		}
        try 
        {
			clientSocket.close();
		} catch (IOException e) 
		{
			System.out.println("Client Socket Closed");
			e.printStackTrace();
		}
		
        try 
        {
			serverSocket.close();
		} 
        catch (IOException e) 
		{
			System.out.println("Server Socket Closed");
			e.printStackTrace();
		}
        System.out.println("Server Closed.");
    }
    	 public static String getString()//returns the string
    	 {
    		 return string;
    	 }

    	 
	public void run() 
	{
		
	}
}