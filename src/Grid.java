// Zakee Jabbar (zjabba2)
// Abdul Rehman (arehma7)
// CS 342
// Project 4

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


// Grid class that holds the two subpanels
public class Grid extends JPanel
{
    private SubGrid panel1;
    private SubGrid panel2;

    // Constructor
    public Grid()
    {
        super();
        setLayout(new GridLayout(1, 1));
        panel1 = new SubGrid();
        add(panel1);
        panel2 = new SubGrid();
        add(panel2);
    }

    // Adds action listeners to all the panels
    public void addListener(GridListener gridListener)
    {
        panel1.addListener(gridListener);
        panel2.addListener(gridListener);

    }


    // Resets the whole grid
    public void resetGrid()
    {
        panel1.resetGrid();
        panel2.resetGrid();
    }

    public void disableAttack()
    {
        panel1.disableGrid();
    }

    public void disablePlace()
    {
        panel2.disableGrid();
    }

    public void enableAttack()
    {
        panel1.enableGrid();
    }

    public void enablePlace()
    {
        panel2.enableGrid();
    }

    public void changeIconShip(int row, int col, ImageIcon p)
    {
        panel2.changeIcon(row, col, p);
    }

    public void changeIconAttack(int row, int col, ImageIcon p)
    {
        panel1.changeIcon(row, col, p);
    }





}
