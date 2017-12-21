// Zakee Jabbar (zjabba2)
// Abdul Rehman (arehma7)
// CS 342
// Project 4



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

// Internal board for the game
// Correlates to the graphics board
public class Board
{
    private char[][] shipBoard;
    private char[][] attackBoard;

    // Constructor
    public Board()
    {
        shipBoard = new char[11][11];
        attackBoard = new char[11][11];
    }

    // Sets a value for the ship board
    public void setPosValueShip(int xPos, int yPos, char value)
    {
        shipBoard[xPos][yPos] = value;
    }

    // Sets a value for the attack board
    public void setPosValueAttack(int xPos, int yPos, char value)
    {
        attackBoard[xPos][yPos] = value;
    }

    // Returns the value of a position on the ship board
    public char getPosValueShip(int xPos, int yPos)
    {
        return shipBoard[xPos][yPos];
    }

    // Returns the value of a position on the attack board
    public char getPosValueAttack(int xPos, int yPos)
    {
        return attackBoard[xPos][yPos];
    }

    // Returns the ship board
    public char[][] getShipBoard()
    {
        return shipBoard;
    }

    // Returns the attack board
    public char[][] getAttackBoard()
    {
        return attackBoard;
    }

    // Prints the ships board
    public void shipPrintBoard() {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                System.out.print(shipBoard[i][j] + "\t");
            }
            System.out.println(" ");

        }
        System.out.println(" ");

    }

    // Print attack board
    public void attackPrintBoard()
    {
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                System.out.print(attackBoard[i][j] + "\t");
            }
            System.out.println(" ");

        }
        System.out.println(" ");

    }

    // Checks if game winner
    public boolean isWinner()
    {
        boolean winner = true;
        return winner;
    }

    // Reset the ship board
    public void resetBoardShip()
    {
        for (int i = 1; i < 11; i++)
        {
            for (int j = 1; j < 11; j++)
            {
                shipBoard[i][j] = 0;
            }
        }
    }


    // Reset the attack board
    public void resetBoardAttack()
    {
        for (int i = 1; i < 11; i++)
        {
            for (int j = 1; j < 11; j++)
            {
                attackBoard[i][j] = 0;
            }
        }
    }


    // Checks if the placements for the ships are ok
    // _______________________________________________________

    public boolean isValidAC(int row, int col, String direction)
    {
        boolean isValid = true;
        for(int i = 0; i < 5; i++)
        {
            if(direction.equals("v"))
            {
                if(row + 5 > 11)
                {
                    isValid = false;
                    break;
                }
                if(shipBoard[row + i][col] != 0)
                {
                    isValid = false;
                    break;
                }
            }
            else
            {
                if(col + 5 > 11)
                {
                    isValid = false;
                    break;
                }
                if(shipBoard[row][col + i] != 0)
                {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }

    public boolean isValidBattleship(int row, int col, String direction)
    {
        boolean isValid = true;
        for(int i = 0; i < 4; i++)
        {
            if(direction.equals("v"))
            {
                if(row + 4 > 11)
                {
                    isValid = false;
                    break;
                }
                if(shipBoard[row + i][col] != 0)
                {
                    isValid = false;
                    break;
                }
            }
            else
            {
                if(col + 4 > 11)
                {
                    isValid = false;
                    break;
                }
                if(shipBoard[row][col + i] != 0)
                {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }

    public boolean isValidDestroyer(int row, int col, String direction)
    {
        boolean isValid = true;
        for(int i = 0; i < 3; i++)
        {
            if(direction.equals("v"))
            {
                if(row + 3 > 11)
                {
                    isValid = false;
                    break;
                }
                if(shipBoard[row + i][col] != 0)
                {
                    isValid = false;
                    break;
                }
            }
            else
            {
                if(col + 3 > 11)
                {
                    isValid = false;
                    break;
                }
                if(shipBoard[row][col + i] != 0)
                {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }

    public boolean isValidSubmarine(int row, int col, String direction)
    {
        boolean isValid = true;
        for(int i = 0; i < 3; i++)
        {
            if(direction.equals("v"))
            {
                if(row + 3 > 11)
                {
                    isValid = false;
                    break;
                }
                if(shipBoard[row + i][col] != 0)
                {
                    isValid = false;
                    break;
                }
            }
            else
            {
                if(col + 3 > 11)
                {
                    isValid = false;
                    break;
                }
                if(shipBoard[row][col + i] != 0)
                {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }

    public boolean isValidPatrolBoat(int row, int col, String direction)
    {
        boolean isValid = true;
        for(int i = 0; i < 2; i++)
        {
            if(direction.equals("v"))
            {
                if(row + 2 > 11)
                {
                    isValid = false;
                    break;
                }
                if(shipBoard[row + i][col] != 0)
                {
                    isValid = false;
                    break;
                }
            }
            else
            {
                if(col + 2 > 11)
                {
                    isValid = false;
                    break;
                }
                if(shipBoard[row][col + i] != 0)
                {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }

}
