/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.NoiDung;
import model.TranDau;

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
    
    public boolean themTranDau(TranDau td) {
        String sql = "INSERT INTO tbl_tran_dau(id_san, thoi_gian, id_noi_dung, diem_thuong, vong, cap, is_team) "
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, td.getSan().getId());
            ps.setDate(2, td.getThoiGian());
            ps.setInt(3, td.getNoiDung().getId());
            ps.setInt(4, td.getDiemThuong());
            ps.setInt(5, td.getVong());
            ps.setInt(6, td.getCap());
            ps.setBoolean(7, td.isIsTeam());
            
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public NoiDung[] getNoiDungList() {
        NoiDung [] listND = null;
        String sql = "SELECT * FROM tbl_noi_dung";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                listND = new NoiDung[rs.getRow()];
                rs.beforeFirst();
                
                int i=0;
                while(rs.next()) {
                    listND[i++] = new NoiDung(rs.getInt("id"), rs.getString("ten"), rs.getString("mo_ta"));
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listND;
    }
    
}
