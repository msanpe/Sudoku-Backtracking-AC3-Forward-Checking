package sudokuSolver;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Miguel Sancho Peña
 * 
 * This class is needed as the forward checking algorithm needs to have a list of variables
 * that will possibly need updating their domains, in the case of the sudoku, each time we
 * operate on a cell, we need to add every cell in its row, column and matrix to the list of
 * candidates that will possibly need to update their domains if the values are not consistent.
 * If after executing the deletion of the domains, any variable has an empty domain, it means that 
 * the current solution is not consistent so all the domains must be restored before trying another value.
 * 
 */
public class DeleteQueue {
        public ArrayList<Cell> checkDelete = new ArrayList<>(); // list of candidates(variables) to be deleted
        public ArrayList<Cell> deletedList = new ArrayList<>(); // of deleted variables

        public void fcAddToDelete(Cell c) { // add to checkDelete (candidates)
            fcAddRow(c);
            fcAddColumn(c);
            fcAddMatrix(c);
        }

        private void fcAddRow(Cell c) { // add row to checkDelete
            for (int i = 0; i < 9; i++) {
                if (i != c.col) {
                    Cell newCell = new Cell(c.row, i);
                    checkDelete.add(newCell);
                }
            }
        }

        private void fcAddColumn(Cell c) { // add column to checkDelete
            for (int i = 0; i < 9; i++) {
                if (i != c.row) {
                    Cell newCell = new Cell(i, c.col);
                    checkDelete.add(newCell);
                }
            }
        }

        private void fcAddMatrix(Cell c) { // add matrix to checkDelete
            float x1Calc = 3 * (c.row / 3);
            float y1Calc = 3 * (c.col / 3);
            int x1 = Math.round(x1Calc);
            int y1 = Math.round(y1Calc);
            int x2 = x1 + 2;
            int y2 = y1 + 2;

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    if (x != c.row && y != c.col) {
                        Cell newCell = new Cell(x, y);
                        checkDelete.add(newCell);
                    }
                }
            }

        }

        public void executeDeletion(int value, HashMap<Cell, ArrayList<Integer>> map) {    // the deletions from the domains 
            for (Cell c : checkDelete) {
                updateDomain(c, value, map);
            }
        }

        private boolean fcDeleteValue(ArrayList<Integer> values, int number) {  // Returns true if if it finds the value in the domain of a variable
            for (int value : values) {
                if (value == number) {
                    return true;
                }
            }

            return false;
        }

        private void updateDomain(Cell c, int value, HashMap<Cell, ArrayList<Integer>> map) {  // Updates, if neccessary, the domain of a variable
            ArrayList<Integer> dominio = map.get(c);
            if (fcDeleteValue(dominio, value)) {
                deletedList.add(c);
                dominio.remove(Integer.valueOf(value));
                map.put(c, dominio);
            }
        }

        public boolean checkForEmptyDomains(HashMap<Cell, ArrayList<Integer>> map) {  // Iterates through the deleted list to check 
            for (Cell c : deletedList) {                                              //if any variable has an empty domain
                if (map.get(c).isEmpty()) {
                    return true;
                }
            }

            return false;
        }

        public void restoreDomains(int value, HashMap<Cell, ArrayList<Integer>> map) { // restaura los dominios de los vertices que han sido borrados
            for (Cell c : deletedList) {
                ArrayList<Integer> dominio = map.get(c);
                dominio.add(value);
                map.put(c, dominio);
            }
            deletedList.clear();
        }
}
