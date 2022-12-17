
import java.util.*;


public class MazeSolverImpl {

    /**
     * You should write your code within this method. A good rule of thumb, especially with
     * recursive problems like this, is to write your input exception handling within this
     * method and then use helper methods to carry out the actual recursion.
     * <p>
     * How you set up the recursive methods is up to you, but note that since this is a *static*
     * method, all helper methods that it calls must *also* be static. Make them package-private
     * (i.e. without private or public modifiers) so you can test them individually.
     *
     * @param maze See the writeup for more details about the format of the input maze.
     * @param sourceCoord The source (starting) coordinate
     * @param goalCoord The goal (ending) coordinate
     * @return a matrix of the same dimension as the input maze containing the solution
     * path marked with 1's, or null if no path exists. See the writeup for more details.
     * @throws IllegalArgumentException in the following instances:
     * 1. If the maze is null
     * 2. If m * n <= 1 where m and n are the dimensions of the maze
     * 3. If either the source coordinate OR the goal coordinate are out of the matrix bounds.
     * 4. If your source or goal coordinate are on a blocked tile.
     */
    public static int[][] solveMaze(int[][] maze, Coordinate sourceCoord, Coordinate goalCoord) {
        //Illegal Cases START
        //Maze is null
        if (maze == null || maze.length == 0) {
            throw new IllegalArgumentException("Maze is Null");
        }
        int cols = maze.length;
        int rows = maze[0].length;
        int[][] solved = new int[rows][cols];
        //Maze is either one row or collum or one block
        if (cols < 2 || rows < 2) {
            throw new IllegalArgumentException("Maze is too Small");
        }
        //Source or Goalcoord out of bounds of maze
        if (sourceCoord.getY() >= rows || sourceCoord.getX() >= cols ||
                goalCoord.getY() >= rows || goalCoord.getX() >= cols ||
                sourceCoord.getY() < 0 || sourceCoord.getX() < 0 ||
                goalCoord.getY() < 0 || goalCoord.getX() < 0) {
            throw new IllegalArgumentException("Coords out of bounds");
        }
        //Source or Goal coord is on a blocked tile
        if (maze[sourceCoord.getY()][sourceCoord.getX()] == 1 ||
                maze[goalCoord.getY()][goalCoord.getX()] == 1) {
            throw new IllegalArgumentException("Goal or Source Coord Blocked");
        }

        //Illegal cases END

        //recurse on all possible
        List<Coordinate> visited = new LinkedList<>();
        boolean result = path(maze, sourceCoord, goalCoord, visited);
        if (visited.isEmpty()) {
            return null;
        }
        for (Coordinate coords : visited) {
            solved[coords.getY()][coords.getX()] = 1;
        }
        return solved;
    }

    static boolean path(int[][] maze, Coordinate s, Coordinate g, List<Coordinate> visited) {
        if (s.getX() >= maze.length || s.getY() >= maze[0].length
                || s.getX() < 0 || s.getY() < 0 || maze[s.getY()][s.getX()] == 1) {
            return false;
        }
        Coordinate down = new Coordinate(s.getX(), s.getY() + 1);
        Coordinate up = new Coordinate(s.getX(), s.getY() - 1);
        Coordinate left = new Coordinate(s.getX() - 1, s.getY());
        Coordinate right = new Coordinate(s.getX() + 1, s.getY());
        if (s.equals(g)) {
            visited.add(s);
            return true;
        }
        maze[s.getY()][s.getX()] = 1;
        visited.add(s);

        boolean bDown =  path(maze, down, g, visited);
        if (bDown) {
            return true;
        }
        boolean bUp =  path(maze, up, g, visited);
        if (bUp) {
            return true;
        }
        boolean bLeft =  path(maze, left, g, visited);
        if (bLeft) {
            return true;
        }
        boolean bRight =  path(maze, right, g, visited);
        if (bRight) {
            return true;
        }


        if (!bLeft && !bRight && !bDown && !bUp) {
            visited.remove(s);
            maze[s.getY()][s.getX()] = 1;
        }

        return bDown || bRight || bUp || bLeft;
    }

}

