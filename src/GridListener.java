// Zakee Jabbar (zjabba2)
// Abdul Rehman (arehma7)
// CS 342
// Project 4


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Handles the user clicks on the grid
public class GridListener implements ActionListener
{
    private Board board;
    private GraphicsBoard graphicsBoard;
    private BattleshipClient client;
    private BattleshipServer server;

    // Constructor
    public GridListener(Board b, GraphicsBoard gB)
    {
        board = b;
        graphicsBoard = gB;
    }

    // Does the actions for a click
    public void actionPerformed(ActionEvent event)
    {
        MyJButton clicked = (MyJButton) event.getSource();
        int row = clicked.getRow();
        int col = clicked.getCol();
        char cCol = clicked.getCharCol();

        if(graphicsBoard.isAttackMode())
        {
            if(client != null)
            {
                client.doSendCoords("" + row + " " + col);
            }
            else
            {
                server.doSendCoords("" + row + " " + col);
            }
        }

        if(graphicsBoard.isPlaceMode())
        {
            String ship = graphicsBoard.getShipType();
            String direction = graphicsBoard.getDirection();

            if(ship == null || direction == null)
            {
                return;
            }

            boolean retVal;

            if(ship.equals("a"))
            {
                retVal = graphicsBoard.placeAC(row, col, direction);
            }
            else if(ship.equals("b"))
            {
                retVal = graphicsBoard.placeBattleShip(row, col, direction);
            }
            else if(ship.equals("d"))
            {
                retVal = graphicsBoard.placeDestroyer(row, col, direction);
            }
            else if(ship.equals("s"))
            {
                retVal = graphicsBoard.placeSubmarine(row, col, direction);
            }
            else
            {
                retVal = graphicsBoard.placePatrolBoat(row, col, direction);
            }

            if(!retVal)
            {
                JOptionPane.showMessageDialog(null, "Invalid position to place boat");
            }
            else
            {
                graphicsBoard.disableShip();
                graphicsBoard.clearShip();
            }

            if(graphicsBoard.getShipsPlaced() == 5)
            {
                graphicsBoard.disablePlace();
                graphicsBoard.enableAttack();
                graphicsBoard.setAttackMode(true);
                graphicsBoard.setPlaceMode(false);
                graphicsBoard.setStatusLabel("Ships set! Begin attack!");

            }


        }



    }

    // Sets the client for the board
    public void setClient(BattleshipClient client) {
        this.client = client;
    }

    // Sets the server for the board
    public void setServer(BattleshipServer server) {
        this.server = server;
    }
}
