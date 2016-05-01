package sudokuSolver;

/**
 *
 * @author Miguel Sancho Peña
 * 
 * Class representing a cell of the board/grid
 */
public class Cell { 

    int row, col;

    public Cell() {
        row = 0;
        col = 0;
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Cell nextCell(Grid board) { // returns the next empty cell
        int c = col;
        int r = row;
        try {
            while (board.getCell(r, c) != 0) {
                c++;
                if (c > 8) {
                    c = 0;
                    r++;
                }
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            // return new Cell(0,0);
        }
        return new Cell(r, c);
    }

    @Override
    public boolean equals(Object o) { // override of equals method
        if (o instanceof Cell && o != null) {
            Cell c = (Cell) o;
            return (row == c.row && col == c.col);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() { // so the hashmap can search for cells
        return (row * 9 + col);
    }

};
