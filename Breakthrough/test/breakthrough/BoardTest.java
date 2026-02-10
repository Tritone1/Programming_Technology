package main;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test class for Board.
 * Tests game logic, move validation, and win conditions.
 */
public class BoardTest {
    
    private Board board;
    
    @Before
    public void setUp() {
        board = new Board(6); // Standard 6x6 board
    }
    
    /**
     * Test Case 1: Initial Board Setup
     */
    @Test
    public void testInitialBoardSetup() {
        // Player 1 should have dolls in rows 0 and 1
        for (int col = 0; col < 6; col++) {
            assertNotNull("Player 1 doll should exist at row 0", board.getDoll(0, col));
            assertNotNull("Player 1 doll should exist at row 1", board.getDoll(1, col));
            assertEquals("Doll should belong to Player 1", Player.PLAYER1, board.getDoll(0, col).getOwner());
        }
        
        // Player 2 should have dolls in rows 4 and 5
        for (int col = 0; col < 6; col++) {
            assertNotNull("Player 2 doll should exist at row 4", board.getDoll(4, col));
            assertNotNull("Player 2 doll should exist at row 5", board.getDoll(5, col));
            assertEquals("Doll should belong to Player 2", Player.PLAYER2, board.getDoll(4, col).getOwner());
        }
        
        // Middle rows should be empty
        for (int col = 0; col < 6; col++) {
            assertNull("Row 2 should be empty", board.getDoll(2, col));
            assertNull("Row 3 should be empty", board.getDoll(3, col));
        }
        
        // Player 1 should start
        assertEquals("Player 1 should go first", Player.PLAYER1, board.getCurrentPlayer());
    }
    
    /**
     * Test Case 2: Valid Forward Move
     */
    @Test
    public void testValidForwardMove() {
        // Player 1 moves forward from (1,3) to (2,3)
        assertTrue("Forward move should be valid", board.isValidMove(1, 3, 2, 3));
        assertTrue("Move should execute successfully", board.makeMove(1, 3, 2, 3));
        
        assertNull("Source position should be empty", board.getDoll(1, 3));
        assertNotNull("Destination should have doll", board.getDoll(2, 3));
        assertEquals("Doll should belong to Player 1", Player.PLAYER1, board.getDoll(2, 3).getOwner());
    }
    
    /**
     * Test Case 3: Invalid Backward Move
     */
    @Test
    public void testInvalidBackwardMove() {
        // Player 1 cannot move backward from (1,3) to (0,3)
        assertFalse("Backward move should be invalid", board.isValidMove(1, 3, 0, 3));
        assertFalse("Move should fail", board.makeMove(1, 3, 0, 3));
    }
    
    /**
     * Test Case 4: Valid Diagonal Capture
     */
    @Test
    public void testValidDiagonalCapture() {
        // Set up capture scenario: move Player 1 doll to (3,2) and Player 2 doll to (4,3)
        board.makeMove(1, 2, 2, 2);
        board.switchPlayer();
        board.makeMove(4, 3, 3, 3);
        board.switchPlayer();
        board.makeMove(2, 2, 3, 2);
        board.switchPlayer();
        
        // Now Player 2 at (3,3) can capture diagonally
        assertTrue("Diagonal capture should be valid", board.isValidMove(3, 3, 2, 2));
        assertTrue("Capture should execute", board.makeMove(3, 3, 2, 2));
        
        assertNull("Source should be empty", board.getDoll(3, 3));
        assertNotNull("Destination should have capturing doll", board.getDoll(2, 2));
        assertEquals("Doll should belong to Player 2", Player.PLAYER2, board.getDoll(2, 2).getOwner());
    }
    
    /**
     * Test Case 5: Invalid Diagonal Move Without Capture
     */
    @Test
    public void testInvalidDiagonalMoveWithoutCapture() {
        // Player 1 cannot move diagonally to empty square from (1,3) to (2,4)
        assertFalse("Diagonal to empty should be invalid", board.isValidMove(1, 3, 2, 4));
    }
    
    /**
     * Test Case 6: Invalid Move to Occupied Square
     */
    @Test
    public void testInvalidMoveToOccupiedSquare() {
        // Cannot move to square occupied by own piece
        assertFalse("Cannot move to own piece", board.isValidMove(0, 2, 1, 2));
        assertFalse("Move should fail", board.makeMove(0, 2, 1, 2));
    }
    
    /**
     * Test Case 7: Win Condition - Player 1 Reaches Last Row
     */
    @Test
    public void testWinConditionPlayer1() {
        // Manually place Player 1 doll at row 4 and move to row 5
        Doll doll = board.getDoll(1, 3);
        doll.setPosition(4, 3);
        board.setDoll(4, 3, doll);
        board.setDoll(1, 3, null);
        
        // Clear row 5 column 3
        board.setDoll(5, 3, null);
        
        // Move to last row
        board.makeMove(4, 3, 5, 3);
        
        assertEquals("Player 1 should win", Player.PLAYER1, board.checkWinner());
    }
    
    /**
     * Test Case 8: Win Condition - Player 2 Reaches First Row
     */
    @Test
    public void testWinConditionPlayer2() {
        board.switchPlayer(); // Switch to Player 2
        
        // Manually place Player 2 doll at row 1 and move to row 0
        Doll doll = board.getDoll(4, 2);
        doll.setPosition(1, 2);
        board.setDoll(1, 2, doll);
        board.setDoll(4, 2, null);
        
        // Clear row 0 column 2
        board.setDoll(0, 2, null);
        
        // Move to first row
        board.makeMove(1, 2, 0, 2);
        
        assertEquals("Player 2 should win", Player.PLAYER2, board.checkWinner());
    }
    
    /**
     * Test Case 9: Out of Bounds Move
     */
    @Test
    public void testOutOfBoundsMove() {
        assertFalse("Move outside board should be invalid", board.isValidMove(0, 0, -1, 0));
        assertFalse("Move outside board should be invalid", board.isValidMove(5, 5, 6, 5));
        assertFalse("Move outside board should be invalid", board.isValidMove(3, 0, 3, -1));
        assertFalse("Move outside board should be invalid", board.isValidMove(3, 5, 3, 6));
    }
    
    /**
     * Test Case 10: Invalid Multi-Square Forward Move
     */
    @Test
    public void testInvalidMultiSquareForwardMove() {
        // Cannot move more than one square forward
        assertFalse("Cannot move 2 squares forward", board.isValidMove(1, 3, 3, 3));
    }
    
    /**
     * Test Case 11: Invalid Sideways Move
     */
    @Test
    public void testInvalidSidewaysMove() {
        // Cannot move horizontally
        assertFalse("Cannot move sideways", board.isValidMove(1, 3, 1, 4));
        assertFalse("Cannot move sideways", board.isValidMove(1, 3, 1, 2));
    }
    
    /**
     * Test Case 12: Board Size 8x8
     */
    @Test
    public void testBoardSize8x8() {
        Board board8 = new Board(8);
        assertEquals("Board should be 8x8", 8, board8.getSize());
        
        // Check initialization
        assertNotNull("Should have dolls in first rows", board8.getDoll(0, 0));
        assertNotNull("Should have dolls in last rows", board8.getDoll(7, 7));
    }
    
    /**
     * Test Case 13: Board Size 10x10
     */
    @Test
    public void testBoardSize10x10() {
        Board board10 = new Board(10);
        assertEquals("Board should be 10x10", 10, board10.getSize());
        
        // Check initialization
        assertNotNull("Should have dolls in first rows", board10.getDoll(0, 5));
        assertNotNull("Should have dolls in last rows", board10.getDoll(9, 5));
    }
    
    /**
     * Test Case 14: Player Turn Switching
     */
    @Test
    public void testPlayerTurnSwitching() {
        assertEquals("Should start with Player 1", Player.PLAYER1, board.getCurrentPlayer());
        
        board.switchPlayer();
        assertEquals("Should switch to Player 2", Player.PLAYER2, board.getCurrentPlayer());
        
        board.switchPlayer();
        assertEquals("Should switch back to Player 1", Player.PLAYER1, board.getCurrentPlayer());
    }
    
    /**
     * Test Case 15: Cannot Move Opponent's Doll
     */
    @Test
    public void testCannotMoveOpponentDoll() {
        // Player 1's turn, try to move Player 2's doll
        assertFalse("Cannot move opponent's doll", board.isValidMove(4, 3, 3, 3));
    }
    
    /**
     * Test Case 16: Edge Capture Scenario
     */
    @Test
    public void testEdgeCaptureScenario() {
        // Set up edge capture at column 0
        board.makeMove(1, 0, 2, 0);
        board.switchPlayer();
        board.makeMove(4, 1, 3, 1);
        board.switchPlayer();
        
        // Player 1 at (2,0) can capture diagonally at (3,1)
        assertTrue("Edge diagonal capture should be valid", board.isValidMove(2, 0, 3, 1));
        assertTrue("Capture should succeed", board.makeMove(2, 0, 3, 1));
        
        assertNotNull("Captured position should have doll", board.getDoll(3, 1));
        assertEquals("Should be Player 1's doll", Player.PLAYER1, board.getDoll(3, 1).getOwner());
    }
    
    /**
     * Test Case 17: No Winner in Progress Game
     */
    @Test
    public void testNoWinnerInProgress() {
        // Make a few moves
        board.makeMove(1, 3, 2, 3);
        board.switchPlayer();
        board.makeMove(4, 3, 3, 3);
        
        assertNull("Should be no winner yet", board.checkWinner());
    }
}
