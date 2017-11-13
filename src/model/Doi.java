/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author HIEU
 */
public class Doi implements Serializable{
    private int id;
    private String tenDoi;
    private String mota;
    private ArrayList<VanDongVien> listVDV;
    private NoiDung noidung;

    public Doi() {
    }
    
    

    public Doi(String tenDoi, String mota) {
        this.tenDoi = tenDoi;
        this.mota = mota;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDoi() {
        return tenDoi;
    }

    public void setTenDoi(String tenDoi) {
        this.tenDoi = tenDoi;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public ArrayList<VanDongVien> getListVDV() {
        return listVDV;
    }

    public void setListVDV(ArrayList<VanDongVien> listVDV) {
        this.listVDV = listVDV;
    }

    public NoiDung getNoidung() {
        return noidung;
    }

    public void setNoidung(NoiDung noidung) {
        this.noidung = noidung;
    }
    
    
    
}
