import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class MazeSolverImplTest {

    private int[][] smallWriteupMaze;
    private int[][] bigWriteupMaze;

    private int[][] bigWriteupMazeNoWork;

    @Before
    public void setupTestMazes() {
        smallWriteupMaze = new int[][]{
                {0, 0, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };

        bigWriteupMaze = new int[][]{
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        bigWriteupMazeNoWork = new int[][]{
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
        };

    }

    @Test
    public void testReturnsSmallMazeSolutionPathInWriteup() {
        int[][] solutionPath = {
                {1, 1, 1, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0},
                {1, 1, 0, 0}
        };
        assertArrayEquals(solutionPath, MazeSolverImpl.solveMaze(smallWriteupMaze,
                new Coordinate(0, 0), new Coordinate(0, 2)));
    }



    @Test
    public void testReturnsBigMazeSolutionPathInWriteup() {
        int[][] bigWriteupSolution = new int[][]{
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] returnedPath = MazeSolverImpl.solveMaze(bigWriteupMaze, new Coordinate(2, 0),
                new Coordinate(4, 0));
        assertArrayEquals(bigWriteupSolution, returnedPath);


    /*
      Note: the above tests are the ones included in the writeup and NOT exhaustive. The autograder
      uses other test cases not listed above. Please thoroughly read all stub files, including
      CoordinateTest.java!

      For help with creating test cases, please see this link:
      https://www.seas.upenn.edu/~cis121/current/testing_guide.html
     */

    }
    @Test(expected = IllegalArgumentException.class)
    public void testGoalCoordOut() {
        MazeSolverImpl.solveMaze(smallWriteupMaze,
                new Coordinate(0, 0), new Coordinate(0, 5));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSourceCoordOut() {
        MazeSolverImpl.solveMaze(smallWriteupMaze,
                new Coordinate(-1, 0), new Coordinate(0, 2));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSourceCoordBlocked() {
        MazeSolverImpl.solveMaze(smallWriteupMaze,
                new Coordinate(0, 1), new Coordinate(0, 5));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGoalCoordBlocked() {
        MazeSolverImpl.solveMaze(smallWriteupMaze,
                new Coordinate(0, 0), new Coordinate(2, 3));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testNullMaze() {
        int[][] nullMaze = null;
        MazeSolverImpl.solveMaze(nullMaze,
                new Coordinate(0, 0), new Coordinate(2, 3));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSmallMaze() {
        int[][] small = {{0,0,0,0}};
        MazeSolverImpl.solveMaze(small,
                new Coordinate(0, 0), new Coordinate(2, 3));
    }

    @Test
    public void testSmallMazeNoSolution() {
        Assert.assertNull(MazeSolverImpl.solveMaze(smallWriteupMaze,
                new Coordinate(0, 0), new Coordinate(3, 3)));
    }

    @Test
    public void testBigMazeNoSolution() {
        Assert.assertNull(MazeSolverImpl.solveMaze(bigWriteupMazeNoWork,
                new Coordinate(0, 0), new Coordinate(9, 9)));
    }
    @Test
    public void pathfoundSmall() {
        assertTrue(MazeSolverImpl.path(smallWriteupMaze,  new Coordinate(0, 0),
                new Coordinate(0, 2), new LinkedList<>()));
    }

    @Test
    public void pathNotfoundSmall() {
        assertFalse(MazeSolverImpl.path(smallWriteupMaze,  new Coordinate(0, 0),
                new Coordinate(0, 1), new LinkedList<>()));
    }

    @Test
    public void pathfoundSmall2() {
        assertTrue(MazeSolverImpl.path(smallWriteupMaze,  new Coordinate(0, 0),
                new Coordinate(3, 1), new LinkedList<>()));
    }

    @Test
    public void pathNotfoundSmall2() {
        assertFalse(MazeSolverImpl.path(smallWriteupMaze,  new Coordinate(0, 0),
                new Coordinate(3, 3), new LinkedList<>()));
    }

    @Test
    public void pathfoundLarge() {
        assertTrue(MazeSolverImpl.path(bigWriteupMazeNoWork,  new Coordinate(0, 0),
                new Coordinate(2, 5), new LinkedList<>()));
    }

    @Test
    public void pathNotfoundLarge() {
        assertFalse(MazeSolverImpl.path(bigWriteupMazeNoWork,  new Coordinate(0, 0),
                new Coordinate(9, 9), new LinkedList<>()));
    }

    @Test
    public void pathfoundLarge2() {
        assertTrue(MazeSolverImpl.path(bigWriteupMazeNoWork,  new Coordinate(0, 0),
                new Coordinate(7, 3), new LinkedList<>()));
    }

    @Test
    public void pathNotfoundLarge2() {
        assertFalse(MazeSolverImpl.path(bigWriteupMazeNoWork,  new Coordinate(0, 0),
                new Coordinate(4, 2), new LinkedList<>()));
    }
    @Test(expected = IllegalArgumentException.class)
    public void emptyMaze() {
        int[][] emp = {};
        MazeSolverImpl.solveMaze(emp, new Coordinate(0, 0), new Coordinate(3, 3));
    }

}
