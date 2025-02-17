package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                // Prepare SQL statement to insert registration
            String sql = "INSERT INTO registration (studentid, termid, crn) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            
            // Set parameters
            ps.setInt(1, studentid);
            ps.setInt(2, termid);
            ps.setInt(3, crn);
            
            // Execute update and check if a row was inserted
            int rowsAffected = ps.executeUpdate();
            result = (rowsAffected == 1);
                
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                // Prepare SQL statement to delete specific registration
            String sql = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
            ps = conn.prepareStatement(sql);
            
            // Set parameters
            ps.setInt(1, studentid);
            ps.setInt(2, termid);
            ps.setInt(3, crn);
            
            // Execute delete and check if a row was deleted
            int rowsAffected = ps.executeUpdate();
            result = (rowsAffected == 1);
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                // Prepare SQL statement to delete all registrations for student in specific term
            String sql = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
            ps = conn.prepareStatement(sql);
            
            // Set parameters
            ps.setInt(1, studentid);
            ps.setInt(2, termid);
            
            // Execute delete and check if any rows were deleted
            int rowsAffected = ps.executeUpdate();
            result = (rowsAffected > 0);
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                 // Prepare SQL statement to list registrations
            String sql = "SELECT studentid, termid, crn FROM registration WHERE studentid = ? AND termid = ? ORDER BY crn";
            ps = conn.prepareStatement(sql);
            
            // Set parameters
            ps.setInt(1, studentid);
            ps.setInt(2, termid);
            
            // Execute query
            rs = ps.executeQuery();
            
            // Convert ResultSet to JSON
            result = DAOUtility.getResultSetAsJson(rs);
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }
    
}
