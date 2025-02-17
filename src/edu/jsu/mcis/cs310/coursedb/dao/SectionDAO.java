package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SectionDAO {
    
    private static final String QUERY_FIND = "SELECT * FROM section WHERE termid = ? AND subjectid = ? AND num = ? ORDER BY crn";
    
    private final DAOFactory daoFactory;
    
    SectionDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public String find(int termid, String subjectid, String num) {
        
        String result = "[]";
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                // Prepare the PreparedStatement using the predefined QUERY_FIND
                ps = conn.prepareStatement(QUERY_FIND);
                
                // Set the parameters for the prepared statement
                ps.setInt(1, termid);
                ps.setString(2, subjectid);
                ps.setString(3, num);
                
                // Execute the query
                rs = ps.executeQuery();
                
                // Convert the ResultSet to JSON using DAOUtility
                result = DAOUtility.getResultSetAsJson(rs);
            }
        }
        catch (Exception e) { 
            e.printStackTrace(); 
            // In case of any exception, keep the default empty JSON array
            result = "[]";
        }
        finally {
            // Close resources
            if (rs != null) { 
                try { 
                    rs.close(); 
                } catch (Exception e) { 
                    e.printStackTrace(); 
                } 
            }
            
            if (ps != null) { 
                try { 
                    ps.close(); 
                } catch (Exception e) { 
                    e.printStackTrace(); 
                } 
            }
        }
        
        return result;
    }
}