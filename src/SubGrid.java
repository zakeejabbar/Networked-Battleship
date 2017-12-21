// Zakee Jabbar (zjabba2)
// Abdul Rehman (arehma7)
// CS 342
// Project 4

import javax.swing.*;
import java.awt.*;

// Implements one of the boards
public class SubGrid extends JPanel
{
    private MyJButton buttons[][];

    // Constructor
    public SubGrid()
    {
        super();
        setLayout(new GridLayout(11,11));

        buttons = new MyJButton[11][11];

        buttons[0][0] = new MyJButton(0,'0', -1);
        buttons[0][0].setEnabled(false);

        for(int i = 1; i < 11; i++)
        {
            buttons[i][0] = new MyJButton(0,'0', -1);
            buttons[0][i] = new MyJButton(0,'0', -1);

            char c = getLetter(i);

            buttons[i][0].setText("" + i);
            buttons[i][0].setEnabled(false);

            buttons[0][i].setText("" + c);
            buttons[0][i].setEnabled(false);

        }

        ImageIcon water = new ImageIcon(((new ImageIcon(
                "resources/batt101.gif").getImage()
                .getScaledInstance(64, 64,
                        java.awt.Image.SCALE_SMOOTH))));
        for(int i = 1; i < 11; i++)
        {
            for(int j = 1; j < 11; j++)
            {
                char c = getLetter(j);
                buttons[i][j] = new MyJButton(i, c,0, j);
                buttons[i][j].setIcon(water);
            }
        }

        for(int i = 0; i < 11; i++)
        {
            for(int j = 0; j < 11; j++)
            {

                add(buttons[i][j]);
            }
        }

        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red));

    }

    // Adds a action listener to all the buttons
    public void addListener(GridListener gridListener)
    {
        for(int i = 1; i < 11; i++)
        {
            for(int j = 1; j < 11; j++)
            {
                buttons[i][j].addActionListener(gridListener);
            }
        }
    }

    // Set value for a button
    public void setValue(int row, int col, int value)
    {
        buttons[row][col].setValue(value);
        buttons[row][col].setText("" + value);
    }

    // Reset the whole grid
    public void resetGrid()
    {
        for(int i = 1; i < 11; i++)
        {
            for(int j = i; j < 11; j++)
            {
                buttons[i][j].setValue(0);
                buttons[i][j].setText("");
            }
        }
    }

    public char getLetter(int col)
    {
        switch (col)
        {
            case 1:
                return 'A';

            case 2:
                return 'B';

            case 3:
                return 'C';

            case 4:
                return 'D';

            case 5:
                return 'E';

            case 6:
                return 'F';

            case 7:
                return 'G';

            case 8:
                return 'H';

            case 9:
                return 'I';

            case 10:
                return 'J';

            default:
                return 'Z';

        }

    }

    public void disableGrid()
    {
        for(int i = 1; i < 11; i++)
        {
            for(int j = 1; j < 11; j++)
            {
                buttons[i][j].setDisabledIcon(buttons[i][j].getIcon());
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public void enableGrid()
    {
        for(int i = 1; i < 11; i++)
        {
            for(int j = 1; j < 11; j++)
            {
                buttons[i][j].setEnabled(true);
            }
        }
    }

    public void changeIcon(int row, int col, ImageIcon p)
    {
        buttons[row][col].setIcon(p);
        buttons[row][col].setDisabledIcon(buttons[row][col].getIcon());
        buttons[row][col].setEnabled(false);
    }





}
