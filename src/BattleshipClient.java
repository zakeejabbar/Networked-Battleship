// Zakee Jabbar (zjabba2)
// Abdul Rehman (arehma7)
// CS 342
// Project 4


import java.net.*;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


// Client class
public class BattleshipClient extends JFrame implements ActionListener
{  
  // GUI items
  JButton sendButton;
  JButton connectButton;
  JTextField machineInfo;
  JTextField portInfo;
  JTextField message;
  JTextArea history;
  private GraphicsBoard gB;

  // Network Items
  boolean connected;
  Socket echoSocket;
  PrintWriter out;
  BufferedReader in;

   // set up GUI
   public BattleshipClient(GraphicsBoard graphicsBoard)
   {
      super( "Echo Client" );

      gB = graphicsBoard;

      // get content pane and set its layout
      Container container = getContentPane();
      container.setLayout (new BorderLayout ());
      
      // set up the North panel
      JPanel upperPanel = new JPanel ();
      upperPanel.setLayout (new GridLayout (4,2));
      container.add (upperPanel, BorderLayout.NORTH);
      
      // create buttons
      connected = false;

      upperPanel.add ( new JLabel ("Message: ", JLabel.RIGHT) );
      message = new JTextField ("");
      message.addActionListener( this );
      upperPanel.add( message );
      
      sendButton = new JButton( "Send Message" );
      sendButton.addActionListener( this );
      sendButton.setEnabled (false);
      upperPanel.add( sendButton );

      connectButton = new JButton( "Connect to Server" );
      connectButton.addActionListener( this );
      upperPanel.add( connectButton );
                      
      upperPanel.add ( new JLabel ("Server Address: ", JLabel.RIGHT) );
      machineInfo = new JTextField ("127.0.0.1");
      upperPanel.add( machineInfo );
                      
      upperPanel.add ( new JLabel ("Server Port: ", JLabel.RIGHT) );
      portInfo = new JTextField ("");
      upperPanel.add( portInfo );
                      
      history = new JTextArea ( 10, 40 );
      history.setEditable(false);
      container.add( new JScrollPane(history) ,  BorderLayout.CENTER);

      setSize( 500, 250 );
      setVisible( true );

   } // end CountDown constructor

//   public static void main( String args[] )
//   {
//      BattleshipClient application = new BattleshipClient();
//      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//   }

    /**********************************************************************************************************/
    public Boolean isClientConnected(){
        return connected;
    }

     public void establishClientConnection(){
         String machineName = machineInfo.getText();
         int portNum = Integer.parseInt(portInfo.getText());
    }

    // handle button event
    public void actionPerformed( ActionEvent event )
    {
       if ( connected && 
           (event.getSource() == sendButton || 
            event.getSource() == message ) )
       {
         doSendMessage();
       }
       else if (event.getSource() == connectButton)
       {
         doManageConnection();
       }
    }

    public void doSendMessage()
    {
      try
      {
        out.println(message.getText());
        history.insert ("From Server: " + in.readLine() + "\n" , 0);
      }
      catch (IOException e) 
      {
        history.insert ("Error in processing message ", 0);
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
    
    public void doManageConnection()
    {
      if (connected == false)
      {
        String machineName = null;
        int portNum = -1;
        try {
            machineName = machineInfo.getText();
            portNum = Integer.parseInt(portInfo.getText());
            echoSocket = new Socket(machineName, portNum );
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        echoSocket.getInputStream()));
            sendButton.setEnabled(true);
            connected = true;
            connectButton.setText("Disconnect from Server");
            gB.enablePlace();
            gB.setStatusLabel("Connection Established! Place ships!");
            new CommunicationThreadClient (echoSocket, this, gB);
        } catch (NumberFormatException e) {
            history.insert ( "Server Port must be an integer\n", 0);
        } catch (UnknownHostException e) {
            history.insert("Don't know about host: " + machineName , 0);
        } catch (IOException e) {
            history.insert ("Couldn't get I/O for "
                               + "the connection to: " + machineName , 0);
        }

      }
      else
      {
        try 
        {
          out.close();
          in.close();
          echoSocket.close();
          sendButton.setEnabled(false);
          connected = false;
          connectButton.setText("Connect to Server");
        }
        catch (IOException e) 
        {
            history.insert ("Error in closing down Socket ", 0);
        }
      }

        
    }

} // end class EchoServer3

class CommunicationThreadClient extends Thread {
    //private boolean serverContinue = true;
    private Socket serverSocket;
    private BattleshipClient gui;
    private GraphicsBoard gB;
    PrintWriter out;
    BufferedReader in;


    public CommunicationThreadClient(Socket serverSoc, BattleshipClient ec3, GraphicsBoard graphicsBoard) {
        serverSocket = serverSoc;
        gui = ec3;
        gui.history.insert("Communicating with Port" + "8081" + "\n", 0);
        gB = graphicsBoard;
        start();
    }

    public void run() {
        System.out.println("New Communication Thread Started");

        try
        {
            out = new PrintWriter(serverSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            String inputLine;
            boolean isGameDone = false;

            while ((inputLine = in.readLine()) != null)
            {
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
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Problem with Communication Server");
            //System.exit(1);
        }
    }
}






 

