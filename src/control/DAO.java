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
import java.sql.Timestamp;
import java.util.ArrayList;
import model.DangKyThiDauCaNhan;
import model.DangKyThiDauDoi;
import model.Doi;
import model.NoiDung;
import model.QuocGia;
import model.San;
import model.TranDau;
import model.VanDongVien;

/**
 *
 * @author HIEU
 */
public class DAO {
    
    private Connection conn;
    private String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    private String dbURL = "jdbc:sqlserver://localhost\\MSSQLSERVER:1433; databasename=BTL; username=sa; password=12345678";
    
    public DAO() {
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public QuocGia[] getListQuocGia(){
        QuocGia[] listQuocGia = null;
        String sql = "SELECT * FROM tbl_quoc_gia";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            ResultSet rs = ps.executeQuery();
            if(rs.last()){
                listQuocGia = new QuocGia[rs.getRow()];
                rs.beforeFirst();
            }
            int i=0;
            while(rs.next()){
                listQuocGia[i++] = new QuocGia(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listQuocGia;
    }
    public boolean themVDV (VanDongVien v){
        String sql = "INSERT INTO tbl_van_dong_vien(gioi_tinh, ho_ten, ngay_sinh, diem, mo_ta, anh, id_quoc_gia)"+" VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, v.getGioiTinh());
            ps.setString(2, v.getHoten());
            ps.setDate(3, v.getNgaySinh());
            ps.setInt(4, v.getDiem());
            ps.setString(5, v.getMota());
            ps.setString(6, "");
            ps.setInt(7, v.getQuocGia().getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
                
        return false;
    }
    public boolean themSan(San s){
        String sql = "INSERT INTO tbl_san(ten, mo_ta)"+" VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getTen());
            ps.setString(2, s.getMota());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean themDoi(Doi d){
        String sql = "INSERT INTO tbl_doi(ten, mo_ta)"+" VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, d.getTenDoi());
            ps.setString(2, d.getMota());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public TranDau [] getTranDauList() {
        TranDau [] listTD = null;
        String sql = "SELECT * FROM tbl_tran_dau";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                listTD = new TranDau[rs.getRow()];
                rs.beforeFirst();
                
                int i=0;
                while(rs.next()) {
                    int id = rs.getInt("id");
                    int id_san = rs.getInt("id_san");
                    int id_noi_dung = rs.getInt("id_noi_dung");
                    int vong = rs.getInt("vong");
                    int cap = rs.getInt("cap");
                    int diem = rs.getInt("diem_thuong");
                    Timestamp tg = rs.getTimestamp("thoi_gian");
                    
                    NoiDung nd = getNoiDungById(id_noi_dung);
                    San s = getSanById(id_san);
                    
                    
                    
                    TranDau td = new TranDau();
                    td.setId(id);
                    td.setSan(s);
                    td.setNoiDung(nd);
                    td.setThoiGian(tg);
                    td.setVong(vong);
                    td.setCap(cap);
                    td.setDiemThuong(diem);
                    
                    if(id_noi_dung < 3) {
                        ArrayList<DangKyThiDauCaNhan> listDK = getDangKyCaNhanByTranDau(id);
                        td.setListDangKyCN(listDK);
                    }
                    else {
                        ArrayList<DangKyThiDauDoi> listDK = getDangKyDoiByTranDau(td);
                        td.setListDangKyDoi(listDK);
                    }
                    listTD[i++] = td;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTD;
    }

public boolean checkTranDau(TranDau td) {
        String sql = "SELECT * FROM tbl_tran_dau "
                + "WHERE ((id_noi_dung = ? AND vong = ? AND cap = ?) "
                + "OR (id_san = ? AND thoi_gian = ?))";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, td.getNoiDung().getId());
            ps.setInt(2, td.getVong());
            ps.setInt(3, td.getCap());
            ps.setInt(4, td.getSan().getId());
            ps.setTimestamp(5, td.getThoiGian());
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean themTranDau(TranDau td) {
        String sql = "INSERT INTO tbl_tran_dau(id_san, thoi_gian, id_noi_dung, diem_thuong, vong, cap, is_team) "
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            if(checkTranDau(td))
                return false;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, td.getSan().getId());
            ps.setTimestamp(2, td.getThoiGian());
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
    
    public San [] getSanList() {
        San [] listSan = null;
        String sql = "SELECT * FROM tbl_san";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                listSan = new San[rs.getRow()];
                rs.beforeFirst();
                
                int i=0;
                while(rs.next()) {
                    listSan[i++] = new San(rs.getInt("id"), rs.getString("ten"), rs.getString("mo_ta"));
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSan;
    }
    
    public San getSanById(int id) {
        String sql = "SELECT * FROM tbl_san "
                + "WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                rs.beforeFirst();
                while(rs.next()) {
                    San s = new San(rs.getInt("id"), rs.getString("ten"), rs.getString("mo_ta"));
                    return s;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public NoiDung getNoiDungById(int id) {
        String sql = "SELECT * FROM tbl_noi_dung WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                rs.beforeFirst();
                while(rs.next()) {
                    NoiDung n = new NoiDung(rs.getInt("id"), rs.getString("ten"), rs.getString("mo_ta"));
                    return n;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public VanDongVien[] searchVDVbyQuocGia(int idquocgia, int gioitinh){
        String sql = "";
        VanDongVien[] listVDV = null;
        if(gioitinh == -1) 
            sql = "SELECT * FROM tbl_van_dong_vien WHERE id_quoc_gia = ?";
        if(gioitinh == 0)
            sql = "SELECT * FROM tbl_van_dong_vien WHERE id_quoc_gia = ? AND gioi_tinh = 'Nam'";
        if(gioitinh == 1)
            sql = "SELECT * FROM tbl_van_dong_vien WHERE id_quoc_gia = ? AND gioi_tinh = 'Nu'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, idquocgia);
            ResultSet rs = ps.executeQuery();
            if(rs.last()){
                listVDV = new VanDongVien[rs.getRow()];
                rs.beforeFirst();
            }
            int i=0;
            while(rs.next()){
                VanDongVien v =  new VanDongVien();
                v.setId(rs.getInt("id"));
                v.setHoten(rs.getString("ho_ten"));
                v.setGioiTinh(rs.getString("gioi_tinh"));
                listVDV[i++] = v;
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return listVDV;
    }
    
    public boolean dangKyThiDauCaNhan(TranDau td) {
        int idTranDau = td.getId();
        for(DangKyThiDauCaNhan dk : td.getListDangKyCN()) {
            String sql = "INSERT INTO tbl_dang_ky_thi_dau_ca_nhan(id_van_dong_vien, id_tran_thi_dau, ghi_chu, is_first) "
                + "VALUES(?, ?, ?, ?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, dk.getVdv().getId());
                ps.setInt(2, idTranDau);
                ps.setString(3, dk.getGhiChu());
                ps.setBoolean(4, dk.isIsFirst());
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    public boolean dangKyThiDauDoi(TranDau td) {
        int idTranDau = td.getId();
        for(DangKyThiDauDoi dk : td.getListDangKyDoi()) {
            String sql = "INSERT INTO tbl_dang_ky_thi_dau_dong_doi(id_doi, id_tran_thi_dau, ghi_chu, is_first) "
                + "VALUES(?, ?, ?, ?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, dk.getDoi().getId());
                ps.setInt(2, idTranDau);
                ps.setString(3, dk.getGhiChu());
                ps.setBoolean(4, dk.isIsFirst());
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    public boolean checkDangKyVanDongVien(TranDau td) {
        int idTranDau = td.getId();
        String sql = "SELECT * FROM tbl_tran_dau as td, tbl_dang_ky_thi_dau_ca_nhan as dk "
                + "WHERE td.id = dk.id_tran_thi_dau "
                + "AND td.id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, idTranDau);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                System.out.println("co ket qua");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<DangKyThiDauCaNhan> getDangKyCaNhanByTranDau(int idTranDau) {
        ArrayList<DangKyThiDauCaNhan> list = null;
        String sql = "SELECT DISTINCT id_van_dong_vien, id_tran_thi_dau, is_first, ghi_chu "
                + "FROM tbl_dang_ky_thi_dau_ca_nhan as dk, tbl_tran_dau as td "
                + "WHERE td.id = ? "
                + "AND td.id = dk.id_tran_thi_dau";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, idTranDau);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                list = new ArrayList<DangKyThiDauCaNhan>();
                rs.beforeFirst();
                int i=0;
                while(rs.next()) {
                    DangKyThiDauCaNhan dk = new DangKyThiDauCaNhan();
                    VanDongVien v = getVanDongVienById(rs.getInt("id_van_dong_vien"));
                    dk.setVdv(v);
                    dk.setIsFirst(rs.getBoolean("is_first"));
                    dk.setGhiChu(rs.getString("ghi_chu"));
                    list.add(dk);
                }
                return list;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public VanDongVien getVanDongVienById(int idVDV) {
        String sql = "SELECT * FROM tbl_van_dong_vien WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, idVDV);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                rs.beforeFirst();
                while(rs.next()) {
                    VanDongVien v = new VanDongVien();
                    v.setId(rs.getInt("id"));
                    v.setHoten(rs.getString("ho_ten"));
                    v.setGioiTinh(rs.getString("gioi_tinh"));
                    
                    int idQuocGia = rs.getInt("id_quoc_gia");
                    QuocGia q = getQuocGiaById(idQuocGia);
                    v.setQuocGia(q);
                    return v;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<VanDongVien> getVanDongVienByDoi(int idDoi) {
        ArrayList<VanDongVien> listVDV = null;
        String sql = "SELECT v.id as id, v.gioi_tinh as gioi_tinh, v.ho_ten as ho_ten, v.id_quoc_gia as id_quoc_gia "
                + "FROM tbl_van_dong_vien as v, tbl_van_dong_vien_doi as vd, tbl_doi as d "
                + "WHERE d.id = ? "
                + "AND vd.id_doi = d.id "
                + "AND vd.id_van_dong_vien = v.id";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, idDoi);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                listVDV = new ArrayList<VanDongVien>();
                rs.beforeFirst();
                while(rs.next()) {
                    VanDongVien v = new VanDongVien();
                    v.setId(rs.getInt("id"));
                    v.setHoten(rs.getString("ho_ten"));
                    v.setGioiTinh(rs.getString("gioi_tinh"));
                    
                    int idQuocGia = rs.getInt("id_quoc_gia");
                    QuocGia q = getQuocGiaById(idQuocGia);
                    v.setQuocGia(q);
                    listVDV.add(v);
                }
                return listVDV;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public QuocGia getQuocGiaById(int idQG) {
        String sql = "SELECT * FROM tbl_quoc_gia WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, idQG);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                rs.beforeFirst();
                while(rs.next()) {
                    QuocGia q = new QuocGia(rs.getInt("id"), rs.getString("ten"), rs.getString("anh"));
                    return q;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<DangKyThiDauDoi> getDangKyDoiByTranDau(TranDau td) {
        Doi [] listDoi = getDoiByTranDau(td);
        ArrayList<DangKyThiDauDoi> list = null;
        String sql = "SELECT DISTINCT id_doi, id_tran_thi_dau, is_first, ghi_chu "
                + "FROM tbl_dang_ky_thi_dau_dong_doi as dk, tbl_tran_dau as td "
                + "WHERE td.id = ? "
                + "AND td.id = dk.id_tran_thi_dau";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, td.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                list = new ArrayList<DangKyThiDauDoi>();
                rs.beforeFirst();
                int i=0;
                while(rs.next()) {
                    DangKyThiDauDoi dk = new DangKyThiDauDoi();
                    dk.setIsFirst(rs.getBoolean("is_first"));
                    dk.setGhiChu(rs.getString("ghi_chu"));
                    list.add(dk);
                }
                list.get(0).setDoi(listDoi[0]);
                list.get(1).setDoi(listDoi[1]);
                return list;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Doi [] getDoiByNoiDung(NoiDung nd) {
        Doi [] listDoi = null;
        String sql = "SELECT * FROM tbl_doi WHERE id_noi_dung = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, nd.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                listDoi = new Doi[rs.getRow()];
                rs.beforeFirst();
                int i=0;
                while(rs.next()) {
                    Doi d = new Doi();
                    int idDoi = rs.getInt("id");
                    d.setId(idDoi);
                    ArrayList<VanDongVien> listVDV = getVanDongVienByDoi(idDoi);
                    d.setListVDV(listVDV);
                    d.setTenDoi(rs.getString("ten"));
                    d.setNoidung(nd);
                    d.setMota(rs.getString("mo_ta"));
                    listDoi[i++] = d;
                }
                return listDoi;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Doi [] getDoiByTranDau(TranDau td) {
        Doi [] list = null;
        String sql = "SELECT d.id as id, d.ten as ten, d.mo_ta as mo_ta "
                + "FROM tbl_doi as d, tbl_dang_ky_thi_dau_dong_doi as dk, tbl_tran_dau as td "
                + "WHERE dk.id_doi = d.id "
                + "AND dk.id_tran_thi_dau = td.id "
                + "AND td.id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, td.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                list = new Doi[rs.getRow()];
                rs.beforeFirst();
                int i=0;
                while(rs.next()) {
                    Doi d = new Doi();
                    int idDoi = rs.getInt("id");
                    d.setId(idDoi);
                    ArrayList<VanDongVien> listVDV = getVanDongVienByDoi(idDoi);
                    d.setListVDV(listVDV);
                    d.setTenDoi(rs.getString("ten"));
                    d.setMota(rs.getString("mo_ta"));
                    list[i++] = d;
                }
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
