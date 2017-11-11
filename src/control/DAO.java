/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HIEU
 */
public class DAO {
    
    private Connection conn;
    private String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    private String dbURL = "jdbc:sqlserver://localhost\\EVALUATION:1433; databasename=BTL; username=sa; password=12345678";
    
    public DAO() {
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
