/**
 * Model for Conway's Game of Life.
 *
 * This class stores the society in a 2D boolean array.
 * true  = live cell
 * false = empty location
 *
 * IMPORTANT FOR THIS PROJECT:
 * The board does NOT wrap around. Any location outside the array is simply
 * ignored when counting neighbors.
 */
public class GameOfLife {

    private boolean[][] society;

    /**
     * Creates an empty society with the requested number of rows and columns.
     */
    public GameOfLife(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive.");
        }
        society = new boolean[rows][cols];
    }

    /** Returns the number of rows in the society. */
    public int numberOfRows() {
        return society.length;
    }

    /** Returns the number of columns in the society. */
    public int numberOfColumns() {
        return society[0].length;
    }

    /** Makes the location at row, col alive. */
    public void growCellAt(int row, int col) {
        if (row >= 0 && row < society.length && col >= 0 && col < society[0].length) {
            society[row][col] = true;
        }
        else {
            throw new IllegalArgumentException("Row and column must be within bounds.");
        }
    }

    /** Makes the location at row, col dead. */
    public void killCellAt(int row, int col) {
        if (row >= 0 && row < society.length && col >= 0 && col < society[0].length) {
            society[row][col] = false;
        }
        else {
            throw new IllegalArgumentException("Row and column must be within bounds.");
        }
    }

    /** Returns true if the location contains a live cell. */
    public boolean cellAt(int row, int col) {
        if (row >= 0 && row < society.length && col >= 0 && col < society[0].length) {
            return society[row][col];
        }
        else {
            throw new IllegalArgumentException("Row and column must be within bounds.");
        }
    }

    /** Makes every location in the society dead. */
    public void clear() {
        for (int r = 0; r < society.length; r++) {
            for (int c = 0; c < society[0].length; c++) {
                society[r][c] = false;
            }
        }
    }

    /**
     * Counts the live neighbors surrounding one location.
     *
     * A location can have at most eight neighbors. Locations outside the
     * board DO NOT wrap around to the other side.
     */
    public int neighborCount(int row, int col) {
        int count = 0;

        if (row == 0 && col == 0) {
            if (society[row + 1][col]) count++;
            if (society[row + 1][col + 1]) count++;
            if (society[row][col + 1]) count++;
        }
        else if (row == 0 && col == society[0].length - 1) {
            if (society[row + 1][col]) count++;
            if (society[row + 1][col - 1]) count++;
            if (society[row][col - 1]) count++;
        }
        else if (row == society.length - 1 && col == 0) {
            if (society[row - 1][col]) count++;
            if (society[row - 1][col + 1]) count++;
            if (society[row][col + 1]) count++;
        }
        else if (row == society.length - 1 && col == society[0].length - 1) {
            if (society[row - 1][col]) count++;
            if (society[row - 1][col - 1]) count++;
            if (society[row][col - 1]) count++;
        }
        else if (row == 0) {
            if (society[row + 1][col]) count++;
            if (society[row + 1][col - 1]) count++;
            if (society[row + 1][col + 1]) count++;
            if (society[row][col - 1]) count++;
            if (society[row][col + 1]) count++;
        }
        else if (row == society.length - 1) {
            if (society[row - 1][col]) count++;
            if (society[row - 1][col - 1]) count++;
            if (society[row - 1][col + 1]) count++;
            if (society[row][col - 1]) count++;
            if (society[row][col + 1]) count++;
        }
        else if (col == 0) {
            if (society[row - 1][col]) count++;
            if (society[row + 1][col]) count++;
            if (society[row - 1][col + 1]) count++;
            if (society[row][col + 1]) count++;
            if (society[row + 1][col + 1]) count++;
        }
        else if (col == society[0].length - 1) {
            if (society[row - 1][col]) count++;
            if (society[row + 1][col]) count++;
            if (society[row - 1][col - 1]) count++;
            if (society[row][col - 1]) count++;
            if (society[row + 1][col - 1]) count++;
        }
        else {
            if (society[row - 1][col]) count++;
            if (society[row - 1][col + 1]) count++;
            if (society[row - 1][col - 1]) count++;
            if (society[row][col - 1]) count++;
            if (society[row][col + 1]) count++;
            if (society[row + 1][col]) count++;
            if (society[row + 1][col - 1]) count++;
            if (society[row + 1][col + 1]) count++;
        }

        return count;
    }

    /**
     * Advances the entire society by one generation.
     *
     * Rules:
     * 1. A dead cell with exactly 3 live neighbors becomes alive.
     * 2. A live cell with 2 or 3 live neighbors survives.
     * 3. A live cell with fewer than 2 neighbors dies from isolation.
     * 4. A live cell with more than 3 neighbors dies from overpopulation.
     */
    public void update() {
        int rows = numberOfRows();
        int cols = numberOfColumns();
        boolean[][] updated = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int neighbors = neighborCount(r, c);

                if (society[r][c]) {
                    updated[r][c] = neighbors == 2 || neighbors == 3;
                }
                else {
                    updated[r][c] = neighbors == 3;
                }
            }
        }

        society = updated;
    }

    /**
     * Returns a text version of the board.
     * O = live cell
     * . = dead cell
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < society.length; r++) {
            for (int c = 0; c < society[0].length; c++) {
                sb.append(society[r][c] ? "O" : ".");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}