
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

import javax.swing.*;

import javax.swing.JFrame;


public class Main extends java.applet.Applet implements ActionListener
{
    CheckboxGroup group; // creates a group of checkboxes test
    Checkbox blocks; // creates the block checkbox
    JTextArea center2;
    JScrollPane scroll;
    Button server,print; // creates the Server and save buttons


	public void init()
	{
		setLayout(new BorderLayout(0,0)); // gives the application a border set up by the two int values
		setSize(600,800);// sets the size of the window
		
		Panel top = new Panel(); // creates the panel at the top of the application
		top.add(new Label("Robbie II Simulator")); // prints label to top panel
		add(top,"North"); // puts the top panel a the top of the application
        Panel center = new Panel();
        center.setLayout(new GridLayout(1,1));
        center2 = new JTextArea();
        
        center2.setSize(1600, 500);
        Font font = new Font("Verdana", Font.BOLD, 12);
        center2.setFont(font);
        center2.setEditable(false);
        center2.setLineWrap(true);
        //center.add(center2);
        center.add(scroll=new JScrollPane(center2));
        add(center,"Center");
        Panel east = new Panel(); // creates a new panel to hold the other pannels
        east.setLayout(new GridLayout(2,1)); // sets how many rows and columns the east panel has
        Panel box = new Panel(); // creates the panel with the blocks checkbox
        box.setLayout(new GridLayout(1,5)); // sets how many rows and columns the box panel has

        east.add(box);// adds the panel box to panel east 
        add(east,"South");// moves the pannel east to the east of the application
        
        Panel button = new Panel(); // creates the button pannel
        button.setLayout(new GridLayout(2,1,2,30));// sets rows, columns, horizontal gap and vertical gap

      
        server = new Button("Perpare Robbie for new path");
        print = new Button("Print the path");
        
        button.add(server);// adds the Server button to the button panel
        button.add(print);// adds the print button to the button panel


        east.add(button);// adds the button panel to the east panel
        server.addActionListener(this);// listens for when the Server button is pressed
        print.addActionListener(this);// listens for when the print button is pressed

	}
		
	
	public void actionPerformed(ActionEvent event) // used when a button is pressed
	{
		 if(event.getSource() == server)// if the Server button is pressed
		 {

			 Thread serv = new Thread(new Server());
			 serv.start();
			 
		 }
		 
		 if(event.getSource() == print)// if the print button is pressed
		 {

			 	String test = new String();
				test = Server.getString();
				
				
				center2.setText("");
				//center2.append(test);
				//center2.append("\nFinished");
				
				if (test==null)
				{
					center2.append("No Path to Print");
					System.out.println("path not printed to frame");
				}
				else
				{
					StringTokenizer splitter = new StringTokenizer(test, ",");//splits the code at commas
					while (splitter.hasMoreTokens()) //while there are more tokens in the list
					{
						center2.append(splitter.nextToken());//split and append
						center2.append("\n");//put next code on a new line
					}
				center2.append("Finished 			Use prolog predicate comands: 	cmd(stop)");//add this after the code has been printed
				System.out.println("path printed to frame");
				}
			 
		 }

	}
        public static void main(String args[])
        {
            Main server = new Main(); // creates new instance of server
            server.init(); // initiates the instance
            JFrame serv = new JFrame();// builds new jframe
            
            serv.setSize(1200,700); // sets the size of the jframe
            serv.setLayout(new BorderLayout(0,0)); //sets the size of the border of the jframe
            serv.setTitle("Map Builder"); // sets the title of the jframe
            serv.add(server,"Center"); // adds the instance of server to the jframe
            serv.setDefaultCloseOperation(serv.EXIT_ON_CLOSE); // closes the program when the the frame is closed
            
            serv.setResizable(true); // makes the jframe non-resizable
            
            
            serv.show();// shows the jframe
        }    
}