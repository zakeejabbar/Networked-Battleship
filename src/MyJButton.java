// Zakee Jabbar (zjabba2)
// Abdul Rehman (arehma7)
// CS 342
// Project 4

import javax.swing.*;
import java.awt.*;

// My own JButton Class
public class MyJButton extends JButton
{
    private int row;
    private char charCol;
    private int col;
    private int value;

    // Multiple Constructors
    public MyJButton()
    {
        super("");
    }

    public MyJButton(int x, char y, int val, int numC)
    {
        super("");
        row = x;
        charCol = y;
        col = numC;


        value = val;
    }

    public MyJButton(int x, char y, int numC)
    {
        super("");
        row = x;
        charCol = y;
        col = numC;

    }


    // Get/Set functions


    public char getCharCol() {
        return charCol;
    }

    public int getRow() {
        return row;
    }

    public int getValue() {
        return value;
    }

    public int getCol() {
        return col;
    }

    public void setCol(char col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
