package sudokuSolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Miguel Sancho Peña
 * 
 * This class represents the gaming board/grid, it contains all the methods to load
 * a Sudoku from a txt file and to check if the given answer is correct. Having this board
 * gives the player the option to play the game without executing any of the algorithms, or to 
 * execute them halfway through a game.
 */
public class Grid {
    private File file;
    private int[][] m_board;

    
    public Grid() {
        file = null;
        m_board = new int[9][9];

        //Initialise board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                m_board[i][j] = 0;
            }
        }

    }

    /**
     * Copy constructor
     * 
     */
    public Grid(Grid original) {
        file = original.file;
        m_board = new int[9][9];

        //Copy board
        for (int i = 0; i < 9; i++) {
            System.arraycopy(original.m_board[i], 0, m_board[i], 0, 9);
        }
    }

    /**
     * returns cell value
     *
     */
    public int getCell(int i, int j) {
        return m_board[i][j];
    }

    /**
     * Sets value to the given cell
     */
    public void setCell(int value, int row, int col) {
        m_board[row][col] = value;
    }

    /**
     * sets file name
     */
    public void setFile(File fi) {
        file = fi;
    }

    /**
     * returns file
     */
    public File getFile() {
        return file;
    }

    /**
     * Loads Sudoku from a file
     */
    public void loadBoard() {
        FileReader fr = null;
        String line;
        int i;

        try {
            fr = new FileReader(file.getPath());
            BufferedReader bf = new BufferedReader(fr);
            try {
                i = 0;
                while ((line = bf.readLine()) != null) {
                    String[] numeros = line.split(" "); // get the numbers from the line

                    for (int j = 0; j < numeros.length; j++) {
                        m_board[i][j] = Integer.parseInt(numeros[j]); // inserts the numbers into the board
                    }
                    i++;
                }
            } catch (IOException e1) {
                System.out.println("Error reading file:" + file.getName());
            }
        } catch (FileNotFoundException e2) {
            System.out.println("Error opening file: " + file.getName());
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e3) {
                System.out.println("Error closing file: " + file.getName());
            }
        }
    }

    /**
     * Sets all the cells to 0
     */
    public void cleanBoard() {
        //Recorre el tablero
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                m_board[i][j] = 0;
            }
        }
    }

    /**
     * Check if the board is empty
     */
    public boolean emptyBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (m_board[i][j] != 0) { // if it finds any number it returns false
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if there are any repeated numbers in a sub matrix
     */
    public boolean checkMatrix(int row, int col) {
        int i, j, central;
        boolean correct = false;

        central = m_board[row][col];
        i = row - 1;
        while (i <= row + 1 && correct) {
            j = col - 1;
            while (j <= col + 1 && correct) {
                if (i != row && j != col && central == m_board[i][j]) {
                    correct = true;
                }
                j++;
            }
            i++;
        }
        return correct;
    }

    /**
     * Checks if the given answer is correct
     * 
     */
    public boolean checkBoard() {
        int value;

        //Iterate through the board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                value = m_board[i][j];

                if (value == 0) {
                    return false;
                }

                //Check for repeated numbers on the row
                for (int z = 0; z < 9; z++) {
                    if (j != z) {
                        if (value == m_board[i][z]) {
                            return false;
                        }
                    }
                }

                //Check for repeated numbers on the column
                for (int z = 0; z < 9; z++) {
                    if (i != z) {
                        if (value == m_board[z][j]) {
                            return false;
                        }
                    }
                }
            }
        }
        //check sub matrices
        for (int i = 1; i <= 7; i += 3) {
            for (int j = 1; j <= 7; j += 3) {
                if (checkMatrix(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }
}
