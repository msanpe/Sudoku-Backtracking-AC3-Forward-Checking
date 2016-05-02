package sudokuSolver;

/**
 *
 * @author Miguel Sancho Peña
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grid tablero = new Grid();
        
        //Creates and starts game interface
        Interface interfaz = new Interface(tablero);
        interfaz.setVisible(true);
    }

}
