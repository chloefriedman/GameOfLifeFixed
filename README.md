# GameOfLifeFixed
-Why is a 2D array appropriate for the Game of Life?
    -For the Game of Life, a 2D array is appropriate because the grid is a set number of squares that doesn't change the amount of rows or columns, so an array has unchangeable dimensions to match that. Similairly, the 2D array is a better representation of the grids compared to a long array that is chopped up.
-What do society.length and society[row].length represent?
   -Society.length represents the amount of rows in society while society[row].length represents the number of columns.
     -Why could changing society directly while traversing it produce incorrect results?
     -This will cause the generation to advance more than once. For example, in a line of three vertical alive blocks, the top and bottom block will die on the first round, but the square next to the middle on either side will become alive since it has 3 neighbors at that moment. By changing the entire array at once, the squares that follow won't be properly updated.
-Why must neighborCount() check array boundaries?
    -The squares along the edges and in corners have less neighbors and checking all 8 will cause an index out of bound error. For example, the squares in the first row(indexed 0) have no neighbors at row-1 since that is out of bounds.
-Why do we need a second 2D array inside update()?
    -this builds the new array by assesing one square at a time for the entire generation before repeating the process and creating a new generation. In other words, the whole screen must be updated based on the previous state, not the "in-action/change" state.
-How can the same GameOfLife object be displayed as both text and graphics?
    -the graphics could be the grid with color coded/filled in squares, or a series of x and o to represent dead/alive/
-What happens to a glider when it reaches the edge of our board, and how is that different 
from wraparound?

-with a wraparound, the glider will keep going since it will come out the other side. With our gliders, when they reach the edge of the board they interact with the boundary as if they have only 5 or 3 neighbors, changing its state.


