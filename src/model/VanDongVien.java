/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author HIEU
 */
public class VanDongVien {
    
    private int id;
    private String hoten;
    private Date ngaySinh;
    private String gioiTinh;
    private String mota;
    private String anh;
    private int diem;
    private QuocGia quocGia;

    public VanDongVien() {
        
    }
    
    public VanDongVien(String hoten, Date ngaySinh, String gioiTinh, String mota, String anh, int diem, QuocGia quocGia) {
        this.hoten = hoten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.mota = mota;
        this.anh = anh;
        this.diem = diem;
        this.quocGia = quocGia;
    }

    public VanDongVien(int id, String hoten, Date ngaySinh, String gioiTinh, String mota, String anh, int diem, QuocGia quocGia) {
        this.id = id;
        this.hoten = hoten;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.mota = mota;
        this.anh = anh;
        this.diem = diem;
        this.quocGia = quocGia;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public QuocGia getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(QuocGia quocGia) {
        this.quocGia = quocGia;
    }
    
}
