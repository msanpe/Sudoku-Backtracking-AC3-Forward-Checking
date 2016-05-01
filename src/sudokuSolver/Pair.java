package sudokuSolver;


/**
 *
 * @author Miguel Sancho Peña
 * 
 * Class representing a pair of cells
 */
public class Pair {

    private Cell c1;
    private Cell c2;

    public Pair() {
        c1 = null;
        c2 = null;
    }

    public Pair(Cell cell1, Cell cell2) {
        c1 = cell1;
        c2 = cell2;
    }

    public Cell left() {
        return c1;
    }

    public Cell right() {
        return c2;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Pair)) {
            return false;
        }
        Pair o = (Pair) other;

        return o.left().equals(c1) && o.right().equals(c2);
    }

}
