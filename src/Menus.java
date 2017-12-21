// Zakee Jabbar (zjabba2)
// Abdul Rehman (arehma7)
// CS 342
// Project 4


import javax.swing.*;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;


// Makes the menus for the game
public class Menus extends JMenuBar
{
    private GraphicsBoard graphicsBoard;
    private Board internalBoard;
    private int attempts;

    // Constructor
    public Menus(GraphicsBoard gB, Board b)
    {
        super();

        graphicsBoard = gB;
        internalBoard = b;
        attempts = 0;

        // File Menu
        JMenu fileMenu = new JMenu( "File" );
        fileMenu.setMnemonic( 'F' );


        // set up Exit menu item
        JMenuItem exitItem = new JMenuItem( "Exit" );
        exitItem.setMnemonic( 'x' );
        fileMenu.add( exitItem );
        exitItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // terminate application when user clicks Exit
                    public void actionPerformed( ActionEvent event )
                    {
                        System.exit( 0 );
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        add(fileMenu);

        // Info menu
        JMenu infoMenu = new JMenu( "Info" );
        fileMenu.setMnemonic( 'I' );

        // set up About... menu item
        JMenuItem aboutItem = new JMenuItem( "About..." );
        aboutItem.setMnemonic( 'A' );
        infoMenu.add( aboutItem );
        aboutItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display message dialog when user selects About...
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( Menus.this,
                                "Authors:\nZakee Jabbar (zjabba2)\n" +
                                        "Abdul Rehman (arehma7)\n",
                                "About", JOptionPane.PLAIN_MESSAGE);
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener


        // How to use game item
        JMenuItem howItem = new JMenuItem( "How to use game..." );
        infoMenu.add( howItem );
        howItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display message dialog when user selects how to play
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( Menus.this,
                                "File -> Quit: To Quit Game\n" +
                                        "Info -> About...: Authors information\n" +
                                        "Info -> How to use game: Get to here\n" +
                                        "Info -> Network connection: How to connect to other player\n" +
                                        "Info -> Statistics: Displays some simple statistics\n" +
                                        "Actions -> Establish Connections: Connect to other player\n",
                                "How to use game", JOptionPane.PLAIN_MESSAGE );
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        // Network connection
        JMenuItem networkItem = new JMenuItem( "Network connection" );
        infoMenu.add( networkItem );
        networkItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display network connection info
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( Menus.this,
                                "Steps to connect to network\n" +
                                        "One program chooses the server and the other chooses the client\n" +
                                        "First click start listening. Then enter I.P address and the port.\n",
                                "Network Connection", JOptionPane.PLAIN_MESSAGE );
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        JMenuItem statsItem = new JMenuItem( "Statistics");
        infoMenu.add( statsItem );
        statsItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    // display message dialog when user how to play sudoku
                    public void actionPerformed( ActionEvent event )
                    {
                        JOptionPane.showMessageDialog( Menus.this,
                                "Stats will be shown here",
                                "Statistics", JOptionPane.PLAIN_MESSAGE );
                    }

                }  // end anonymous inner class

        ); // end call to addActionListener

        add(infoMenu);


        // Menu for the hints
        JMenu actionsMenu = new JMenu( "Actions" );
        fileMenu.setMnemonic( 'A' );



        // set up Single algorithm
        JMenuItem establishServerItem = new JMenuItem( "Establish server side connection");
        actionsMenu.add( establishServerItem );
        establishServerItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    //Uses the single algorithm to resolve a cell
                    public void actionPerformed( ActionEvent event )
                    {
                        BattleshipServer server = new BattleshipServer(graphicsBoard);
                        graphicsBoard.setServer(server);

                    }

                }  // end anonymous inner class

        ); // end call to


        // set up Single algorithm
        JMenuItem establishClientItem = new JMenuItem( "Establish client side connection");
        actionsMenu.add( establishClientItem );
        establishClientItem.addActionListener(

                new ActionListener() {  // anonymous inner class

                    //Uses the single algorithm to resolve a cell
                    public void actionPerformed( ActionEvent event )
                    {
                        BattleshipClient client = new BattleshipClient(graphicsBoard);
                        graphicsBoard.setClient(client);

                    }

                }  // end anonymous inner class

        ); // end call to



        add(actionsMenu);
    }

}