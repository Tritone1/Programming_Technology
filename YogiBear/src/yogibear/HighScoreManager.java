/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yogibear;

import java.sql.*;
/**
 *
 * @author Kanan
 */
public class HighScoreManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/yogibear_game";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root123";
    
    public static void saveScore(String name, int score, int level, long time) {
        String sql = "INSERT INTO highscores (player_name, score, level_reached, time_seconds) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.setInt(3, level);
            pstmt.setLong(4, time);
            pstmt.executeUpdate();
            
            System.out.println("Score saved successfully to database!");
            
        } catch (SQLException e) {
            System.err.println("Error saving score to database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static String getTopScoresString() {
        StringBuilder sb = new StringBuilder("TOP 10 HIGH SCORES\n\n");
        sb.append("Rank | Name              | Score  | Level | Time\n");
        sb.append("--------------------------------------------------\n");
        
        String sql = "SELECT player_name, score, level_reached, time_seconds FROM highscores ORDER BY score DESC LIMIT 10";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            int rank = 1;
            while (rs.next()) {
                String name = rs.getString("player_name");
                int score = rs.getInt("score");
                int level = rs.getInt("level_reached");
                long time = rs.getLong("time_seconds");
                
                sb.append(String.format("%-4d | %-17s | %-6d | %-5d | %s\n", 
                    rank++, name, score, level, formatTime(time)));
            }
            
            if (rank == 1) {
                sb.append("\nNo high scores yet. Be the first!\n");
            }
            
        } catch (SQLException e) {
            sb.append("\nError loading scores: ").append(e.getMessage());
            System.err.println("Database error: " + e.getMessage());
        }
        
        return sb.toString();
    }
    
    /**
     * Formats time in seconds to MM:SS format
     */
    private static String formatTime(long seconds) {
        long minutes = seconds / 60;
        long secs = seconds % 60;
        return String.format("%d:%02d", minutes, secs);
    }
    
    /**
     * Test database connection
     */
    public static boolean testConnection() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Database connection successful!");
            return true;
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            return false;
        }
    }
}
