// Zakee Jabbar (zjabba2)
// Abdul Rehman (arehma7)
// CS 342
// Project 4


import javafx.scene.control.RadioButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

// Makes the sudoku board and deals with the user input
public class GraphicsBoard
{
    private JFrame window;
    private Grid grid;
    private Board internalBoard;
    private Menus menu;
    private JLabel statusLabel;
    private boolean placeMode;
    private boolean attackMode;
    private ButtonGroup shipButtons;
    private ButtonGroup directionButtons;
    private JPanel shipPanel;
    private JRadioButton aC;
    private JRadioButton battleShip;
    private JRadioButton destroyer;
    private JRadioButton submarine;
    private JRadioButton patrolBoat;
    private JRadioButton vertical;
    private JRadioButton horizontal;
    private JPanel directionPanel;
    private JPanel sidePanel;
    private int shipsPlaced;
    private ImageIcon frontHor;
    private ImageIcon backHor;
    private ImageIcon midHor;
    private ImageIcon frontVert;
    private ImageIcon backVert;
    private ImageIcon midVert;
    private ImageIcon hitFrontHor;
    private ImageIcon hitBackHor;
    private ImageIcon hitMidHor;
    private ImageIcon hitFrontVert;
    private ImageIcon hitBackVert;
    private ImageIcon hitMidVert;
    private ImageIcon waterHit;
    private ImageIcon waterMiss;
    private ImageIcon water;
    private BattleshipServer server;
    private BattleshipClient client;
    private GridListener gridListener;
    private int shipsSunk;

    private HashMap <Character, Ship> shipSize;

    // Constructor 1
    public GraphicsBoard()
    {
        // _______________________________________________________

        // Hashmap for ship information

        this.shipSize = new HashMap<Character, Ship>();
        shipSize.put('A', new Ship("h", 'A', 5));
        shipSize.put('B', new Ship("h", 'B', 4));
        shipSize.put('D', new Ship("h", 'D', 3));
        shipSize.put('S', new Ship("h", 'S', 3));
        shipSize.put('P', new Ship("h", 'P', 2));

        // ________________________________________________________

        // GUI Layout and SidePanel Stuff


        window = new JFrame("Networked Battleship Game"); // JFrame that holds everything
        window.setBackground(Color.BLACK);
        grid = new Grid(); // Panel holding the subgrids
        statusLabel = new JLabel("Status: ");
        statusLabel.setVisible(true);
        statusLabel.setForeground(Color.RED);
        shipsSunk = 0;

        internalBoard = new Board(); // Makes the internal board
        menu = new Menus(this, internalBoard); // Makes the menus

        gridListener = new GridListener(internalBoard, this);
        grid.addListener(gridListener);

        shipButtons = new ButtonGroup();

        aC = new JRadioButton("Aircraft Carrier");
        aC.setActionCommand("a");
        aC.setForeground(Color.BLUE);
        aC.setBackground(Color.BLACK);

        battleShip = new JRadioButton("Battleship");
        battleShip.setActionCommand("b");
        battleShip.setForeground(Color.BLUE);
        battleShip.setBackground(Color.BLACK);

        destroyer = new JRadioButton("Destroyer");
        destroyer.setActionCommand("d");
        destroyer.setForeground(Color.BLUE);
        destroyer.setBackground(Color.BLACK);

        submarine = new JRadioButton("Submarine");
        submarine.setActionCommand("s");
        submarine.setBackground(Color.BLACK);
        submarine.setForeground(Color.BLUE);

        patrolBoat = new JRadioButton("Patrol Boat");
        patrolBoat.setActionCommand("p");
        patrolBoat.setForeground(Color.BLUE);
        patrolBoat.setBackground(Color.BLACK);

        shipButtons.add(aC);
        shipButtons.add(battleShip);
        shipButtons.add(destroyer);
        shipButtons.add(submarine);
        shipButtons.add(patrolBoat);

        JLabel shipLabel = new JLabel(" Type of boat: ");
        shipLabel.setForeground(Color.BLUE);

        shipPanel = new JPanel(new GridLayout(0, 1));
        shipPanel.setBackground(Color.BLACK);
        shipPanel.setForeground(Color.BLUE);
        shipPanel.add(shipLabel);
        shipPanel.add(aC);
        shipPanel.add(battleShip);
        shipPanel.add(destroyer);
        shipPanel.add(submarine);
        shipPanel.add(patrolBoat);

        directionButtons = new ButtonGroup();

        vertical = new JRadioButton("Vertical");
        vertical.setActionCommand("v");
        vertical.setBackground(Color.BLACK);
        vertical.setForeground(Color.BLUE);

        horizontal = new JRadioButton("Horizontal");
        horizontal.setActionCommand("h");
        horizontal.setBackground(Color.BLACK);
        horizontal.setForeground(Color.BLUE);


        directionButtons.add(vertical);
        directionButtons.add(horizontal);

        JLabel directionLabel = new JLabel(" Orientation: ");
        directionLabel.setForeground(Color.BLUE);

        directionPanel = new JPanel(new GridLayout(0,1));
        directionPanel.setBackground(Color.BLACK);
        directionPanel.setForeground(Color.BLUE);
        directionPanel.add(directionLabel);
        directionPanel.add(vertical);
        directionPanel.add(horizontal);

        sidePanel = new JPanel(new GridLayout(2, 1));
        sidePanel.add(shipPanel);
        sidePanel.add(directionPanel);

        directionPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLUE));
        shipPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLUE));

        placeMode = true;
        grid.disableAttack();
        grid.disablePlace();

        statusLabel.setText("Waiting for connection to establish!");

        // Change the visual layout of the frame/panel
        window.setSize(1250, 600);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setJMenuBar(menu);
        window.add(grid,BorderLayout.CENTER);
        window.add(statusLabel, BorderLayout.SOUTH);
        window.add(sidePanel, BorderLayout.EAST);

        //_________________________________________________________

        // Unhit ship images

        frontHor = new ImageIcon(((new ImageIcon(
                "resources/batt5.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));

        backHor = new ImageIcon(((new ImageIcon(
                "resources/batt1.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));

        midHor = new ImageIcon(((new ImageIcon(
                "resources/batt2.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));

        frontVert = new ImageIcon(((new ImageIcon(
                "resources/batt10.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));

        backVert = new ImageIcon(((new ImageIcon(
                "resources/batt6.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));

        midVert = new ImageIcon(((new ImageIcon(
                "resources/batt9.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));


        //________________________________________________________________

        // Hit Ship Images

        hitFrontHor = new ImageIcon(((new ImageIcon(
                "resources/batt203.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));

        hitBackHor = new ImageIcon(((new ImageIcon(
                "resources/batt201.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));

        hitMidHor = new ImageIcon(((new ImageIcon(
                "resources/batt202.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));

        hitFrontVert = new ImageIcon(((new ImageIcon(
                "resources/batt206.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));

        hitBackVert = new ImageIcon(((new ImageIcon(
                "resources/batt204.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));

        hitMidVert = new ImageIcon(((new ImageIcon(
                "resources/batt205.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));


        // ______________________________________________________________

        // Water images

        waterHit = new ImageIcon(((new ImageIcon(
                "resources/batt103.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));

        waterMiss = new ImageIcon(((new ImageIcon(
                "resources/batt102.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));

        water = new ImageIcon(((new ImageIcon(
                "resources/batt101.gif").getImage()
                .getScaledInstance(50, 50,
                        java.awt.Image.SCALE_SMOOTH))));

        // ___________________________________________________________________




        // Make frame visible
        window.setVisible(true);

    }


    // Set/Get/Reset/Load functions
    public void setStatusLabel(String text)
    {
        statusLabel.setText(text);
    }

    public void resetBoard()
    {
        grid.resetGrid();
    }

    public boolean isAttackMode()
    {
        return attackMode;
    }

    public boolean isPlaceMode()
    {
        return placeMode;
    }

    public void disableAttack()
    {
        grid.disableAttack();
    }

    public void disablePlace()
    {
        grid.disablePlace();
    }

    public void enableAttack()
    {
        grid.enableAttack();
    }

    public void enablePlace()
    {
        grid.enablePlace();
    }

    public int getShipsPlaced()
    {
        return shipsPlaced;
    }

    public String getShipType()
    {
        if(shipButtons.getSelection() != null)
        {
            return shipButtons.getSelection().getActionCommand();
        }
        return null;
    }

    public String getDirection()
    {
        if(directionButtons.getSelection() != null)
        {
            return directionButtons.getSelection().getActionCommand();
        }
        return null;
    }

    public void clearShip()
    {
        shipButtons.clearSelection();
    }

    public void disableShip()
    {
      shipButtons.getSelection().setEnabled(false);
    }

    public boolean placeAC(int row, int col, String direction)
    {
        if(direction.equals("v"))
        {
            if(internalBoard.isValidAC(row, col, direction))
            {
                grid.changeIconShip(row, col, backVert);
                grid.changeIconShip(row + 1, col, midVert);
                grid.changeIconShip(row + 2, col, midVert);
                grid.changeIconShip(row + 3, col, midVert);
                grid.changeIconShip(row + 4, col, frontVert);

                internalBoard.setPosValueShip(row, col, 'A');
                internalBoard.setPosValueShip(row + 1, col, 'A');
                internalBoard.setPosValueShip(row + 2, col, 'A');
                internalBoard.setPosValueShip(row + 3, col, 'A');
                internalBoard.setPosValueShip(row + 4, col, 'A');

                shipsPlaced++;


            }
            else
            {
                return false;
            }
        }
        else
        {
            if(internalBoard.isValidAC(row, col, direction))
            {
                grid.changeIconShip(row, col, backHor);
                grid.changeIconShip(row, col + 1, midHor);
                grid.changeIconShip(row, col + 2, midHor);
                grid.changeIconShip(row, col + 3, midHor);
                grid.changeIconShip(row, col + 4, frontHor);

                internalBoard.setPosValueShip(row, col, 'A');
                internalBoard.setPosValueShip(row, col + 1, 'A');
                internalBoard.setPosValueShip(row, col + 2, 'A');
                internalBoard.setPosValueShip(row, col + 3, 'A');
                internalBoard.setPosValueShip(row, col + 4, 'A');

                shipsPlaced++;

            }
            else
            {
                return false;
            }
        }

        return true;
    }

    public boolean placeBattleShip(int row, int col, String direction)
    {
        if(direction.equals("v"))
        {
            if(internalBoard.isValidBattleship(row, col, direction))
            {
                grid.changeIconShip(row, col, backVert);
                grid.changeIconShip(row + 1, col, midVert);
                grid.changeIconShip(row + 2, col, midVert);
                grid.changeIconShip(row + 3, col, frontVert);

                internalBoard.setPosValueShip(row, col, 'B');
                internalBoard.setPosValueShip(row + 1, col, 'B');
                internalBoard.setPosValueShip(row + 2, col, 'B');
                internalBoard.setPosValueShip(row + 3, col, 'B');

                shipsPlaced++;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(internalBoard.isValidBattleship(row, col, direction))
            {
                grid.changeIconShip(row, col, backHor);
                grid.changeIconShip(row, col + 1, midHor);
                grid.changeIconShip(row, col + 2, midHor);
                grid.changeIconShip(row, col + 3, frontHor);

                internalBoard.setPosValueShip(row, col, 'B');
                internalBoard.setPosValueShip(row, col + 1, 'B');
                internalBoard.setPosValueShip(row, col + 2, 'B');
                internalBoard.setPosValueShip(row, col + 3, 'B');

                shipsPlaced++;
            }
            else
            {
                return false;
            }
        }

        return true;
    }

    public boolean placeDestroyer(int row, int col, String direction)
    {
        if(direction.equals("v"))
        {
            if(internalBoard.isValidDestroyer(row, col, direction))
            {
                grid.changeIconShip(row, col, backVert);
                grid.changeIconShip(row + 1, col, midVert);
                grid.changeIconShip(row + 2, col, frontVert);

                internalBoard.setPosValueShip(row, col, 'D');
                internalBoard.setPosValueShip(row + 1, col, 'D');
                internalBoard.setPosValueShip(row + 2, col, 'D');

                shipsPlaced++;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(internalBoard.isValidDestroyer(row, col, direction))
            {
                grid.changeIconShip(row, col, backHor);
                grid.changeIconShip(row, col + 1, midHor);
                grid.changeIconShip(row, col + 2, frontHor);

                internalBoard.setPosValueShip(row, col, 'D');
                internalBoard.setPosValueShip(row, col + 1, 'D');
                internalBoard.setPosValueShip(row, col + 2, 'D');

                shipsPlaced++;

            }
            else
            {
                return false;
            }
        }

        return true;
    }

    public boolean placeSubmarine(int row, int col, String direction)
    {
        if(direction.equals("v"))
        {
            if(internalBoard.isValidSubmarine(row, col, direction))
            {
                grid.changeIconShip(row, col, backVert);
                grid.changeIconShip(row + 1, col, midVert);
                grid.changeIconShip(row + 2, col, frontVert);

                internalBoard.setPosValueShip(row, col, 'S');
                internalBoard.setPosValueShip(row + 1, col, 'S');
                internalBoard.setPosValueShip(row + 2, col, 'S');

                shipsPlaced++;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(internalBoard.isValidSubmarine(row, col, direction))
            {
                grid.changeIconShip(row, col, backHor);
                grid.changeIconShip(row, col + 1, midHor);
                grid.changeIconShip(row, col + 2, frontHor);

                internalBoard.setPosValueShip(row, col, 'S');
                internalBoard.setPosValueShip(row, col + 1, 'S');
                internalBoard.setPosValueShip(row, col + 2, 'S');

                shipsPlaced++;
            }
            else
            {
                return false;
            }
        }

        return true;
    }

    public boolean placePatrolBoat(int row, int col, String direction)
    {
        if(direction.equals("v"))
        {
            if(internalBoard.isValidPatrolBoat(row, col, direction))
            {
                grid.changeIconShip(row, col, backVert);
                grid.changeIconShip(row + 1, col, frontVert);

                internalBoard.setPosValueShip(row, col, 'P');
                internalBoard.setPosValueShip(row + 1, col, 'P');

                shipsPlaced++;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(internalBoard.isValidPatrolBoat(row, col, direction))
            {
                grid.changeIconShip(row, col, backHor);
                grid.changeIconShip(row, col + 1, frontHor);

                internalBoard.setPosValueShip(row, col, 'P');
                internalBoard.setPosValueShip(row, col + 1, 'P');

                shipsPlaced++;

            }
            else
            {
                return false;
            }
        }

        return true;
    }

    public void setAttackMode(boolean attackMode)
    {
        this.attackMode = attackMode;
    }

    public void setPlaceMode(boolean placeMode)
    {
        this.placeMode = placeMode;
    }

    public void setServer(BattleshipServer server) {
        this.server = server;
        gridListener.setServer(server);
    }

    public void setClient(BattleshipClient client) {
        this.client = client;
        gridListener.setClient(client);
    }

    // Parses the input from the server
    public String parseInput(String input)
    {
        if(input.contains("hit"))
        {
            statusLabel.setText("Attack was a hit!");
            String[] both = input.split(" ");
            int row = Integer.parseInt(both[1]);
            int col = Integer.parseInt(both[2]);
            grid.changeIconAttack(row, col, waterHit);
            return null;
        }
        else if(input.contains("sunk"))
        {
            statusLabel.setText("Ship was sunk!");
            String[] both = input.split(" ");
            int row = Integer.parseInt(both[1]);
            int col = Integer.parseInt(both[2]);
            grid.changeIconAttack(row, col, waterHit);
            return null;
        }
        else if(input.contains("win"))
        {
            statusLabel.setText("You are a winner!");
            String[] both = input.split(" ");
            int row = Integer.parseInt(both[1]);
            int col = Integer.parseInt(both[2]);
            grid.changeIconAttack(row, col, waterHit);
            return "lose";
        }
        else if(input.contains("lose"))
        {
            statusLabel.setText("Sorry! You lost!");
            return null;
        }
        else if(input.contains("miss"))
        {
            statusLabel.setText("Attack was a miss!");
            String[] both = input.split(" ");
            int row = Integer.parseInt(both[1]);
            int col = Integer.parseInt(both[2]);
            grid.changeIconAttack(row, col, waterMiss);
            return null;
        }
        else
        {
            String[] both = input.split(" ");
            int row = Integer.parseInt(both[0]);
            int col = Integer.parseInt(both[1]);

            if(internalBoard.getPosValueShip(row, col) != 0)
            {
                char c = internalBoard.getPosValueShip(row, col);
                Ship temp = shipSize.get(c);
                temp.incrementCount();

                if(temp.isSunk())
                {
                    shipsSunk++;
                    if(shipsSunk == 5)
                    {
                        grid.changeIconShip(row, col, hitMidHor);
                        return ("win " + row + " " + col);
                    }
                    else
                    {
                        grid.changeIconShip(row, col, hitMidHor);
                        return ("sunk " + row + " " + col);
                    }
                }

                grid.changeIconShip(row, col, hitMidHor);
                return ("hit " + row + " " + col);

            }
            grid.changeIconShip(row, col, waterMiss);
            return ("miss " + row + " " + col);


        }

    }

    public HashMap <Character, Ship> getShips()
    {
        return this.shipSize;
    }
}
