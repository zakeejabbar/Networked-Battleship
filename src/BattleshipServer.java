// Zakee Jabbar (zjabba2)
// Abdul Rehman (arehma7)
// CS 342
// Project 4


import java.net.*;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


// Server class for the program
public class BattleshipServer extends JFrame implements ActionListener{
  
  // GUI items
  JButton ssButton;
  JLabel machineInfo;
  JLabel portInfo;
  JTextArea history;
  private boolean running;
  private GraphicsBoard gB;

  // Network Items
  boolean serverContinue;
  ServerSocket serverSocket;
  PrintWriter out;
  BufferedReader in;


   // set up GUI
   public BattleshipServer(GraphicsBoard graphicsBoard)
   {
      super( "Battleship Server" );

      gB = graphicsBoard;

      // get content pane and set its layout
      Container container = getContentPane();
      container.setLayout( new FlowLayout() );

      // create buttons
      running = false;
      ssButton = new JButton( "Start Listening" );
      ssButton.addActionListener( this );
      container.add( ssButton );

      String machineAddress = null;
      try
      {  
        InetAddress addr = InetAddress.getLocalHost();
        machineAddress = addr.getHostAddress();
      }
      catch (UnknownHostException e)
      {
        machineAddress = "127.0.0.1";
      }
      machineInfo = new JLabel (machineAddress);
      container.add( machineInfo );
      portInfo = new JLabel ("Port: 8081");
      container.add( portInfo );

      history = new JTextArea ( 10, 40 );
      history.setEditable(false);
      container.add( new JScrollPane(history) );

      setSize( 500, 250 );
      setVisible( true );

   } // end CountDown constructor

//   public static void main( String args[] )
//   {
//       BattleshipServer application = new BattleshipServer();
//       application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//   }

    // handle button event
    public void actionPerformed( ActionEvent event )
    {
        if (running == false)
       {
           this.serverContinue = true;
           try
           {
               this.serverSocket = new ServerSocket(8081);
               this.portInfo.setText("Listening on Port: " + this.serverSocket.getLocalPort());
               System.out.println ("Connection Socket Created");
               try
               {
                   System.out.println ("Waiting for Connection");
                   this.ssButton.setText("Stop Listening");
                   Socket client = this.serverSocket.accept();
                   out = new PrintWriter(client.getOutputStream(),true);
                   in = new BufferedReader(new InputStreamReader( client.getInputStream()));
                   gB.enablePlace();
                   gB.setStatusLabel("Connection Established! Place ships!");
                   new CommunicationThread (client, this, gB);
               }
               catch (IOException e)
               {
                   System.err.println("Accept failed.");
                   System.exit(1);
               }
           }
           catch (IOException e)
           {
               System.err.println("Could not listen on port: 10008.");
               System.exit(1);
           }
           finally
           {
               try {
                   this.serverSocket.close();
               }
               catch (IOException e)
               {
                   System.err.println("Could not close port: 10008.");
                   System.exit(1);
               }
           }
       }
       else
       {
         serverContinue = false;
         ssButton.setText ("Start Listening");
         portInfo.setText (" Not Listening ");
       }
    }

    public void doSendCoords(String coord)
    {
        //try
        {
            out.println("" + coord);
            //history.insert ("From Server: " + in.readLine() + "\n" , 0);
        }
        //catch (IOException e)
        //{
          //  history.insert ("Error in processing message ", 0);
        //}
    }


 } // end class BattleshipServer


class CommunicationThread extends Thread
{ 
 //private boolean serverContinue = true;
 private Socket clientSocket;
 private BattleshipServer gui;
 private GraphicsBoard gB;
 PrintWriter out;
 BufferedReader in;



 public CommunicationThread (Socket clientSoc, BattleshipServer ec3, GraphicsBoard graphicsBoard)
   {
    clientSocket = clientSoc;
    gui = ec3;
    gui.history.insert ("Communicating with Port" + clientSocket.getLocalPort()+"\n", 0);
    gB = graphicsBoard;
    start();
   }

 public void run()
   {
    System.out.println ("New Communication Thread Started");

    try {
         out = new PrintWriter(clientSocket.getOutputStream(),true);
         in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));

         String inputLine;
         boolean isGameDone = false;

         while ((inputLine = in.readLine()) != null)
         {
//              System.out.println ("Server Received: " + inputLine);
//              gui.history.insert ( inputLine+"\n", 0);
             String response = gB.parseInput(inputLine);
             System.out.println ("Server: " + inputLine);
             gui.history.insert (inputLine+"\n", 0);
             if(response != null)
             {
                 out.println(response);
             }

         }

         out.close(); 
         in.close(); 
         clientSocket.close(); 
        } 
    catch (IOException e) 
        { 
         System.err.println("Problem with Communication Server");
         //System.exit(1); 
        } 
    }
} 





 

