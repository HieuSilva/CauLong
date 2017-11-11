/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author HIEU
 */
public class TranDau implements Serializable{
    
    private int id;
    private San san;
    private Date thoiGian;
    private NoiDung noiDung;
    private boolean isTeam;
    private int vong;
    private int cap;
    private int diemThuong;
    private ArrayList<DangKyThiDauCaNhan> listDangKyCN;
    private ArrayList<DangKyThiDauDoi> listDangKyDoi;
    private int set11, set12, set21, set22, set31, set32;

    public TranDau(int id, San san, Date thoiGian, NoiDung noiDung,
                    boolean isTeam, int vong, int cap, int diemThuong,
                    ArrayList<DangKyThiDauCaNhan> listDangKyCN,
                    ArrayList<DangKyThiDauDoi> listDangKyDoi,
                    int set11, int set12, int set21, int set22, int set31, int set32) {
        this.id = id;
        this.san = san;
        this.thoiGian = thoiGian;
        this.noiDung = noiDung;
        this.isTeam = isTeam;
        this.vong = vong;
        this.cap = cap;
        this.diemThuong = diemThuong;
        this.listDangKyCN = listDangKyCN;
        this.listDangKyDoi = listDangKyDoi;
        this.set11 = set11;
        this.set12 = set12;
        this.set21 = set21;
        this.set22 = set22;
        this.set31 = set31;
        this.set32 = set32;
    }

    public TranDau(San san, Date thoiGian, NoiDung noiDung, boolean isTeam, int vong, int cap, int diemThuong, ArrayList<DangKyThiDauCaNhan> listDangKyCN, ArrayList<DangKyThiDauDoi> listDangKyDoi, int set11, int set12, int set21, int set22, int set31, int set32) {
        this.san = san;
        this.thoiGian = thoiGian;
        this.noiDung = noiDung;
        this.isTeam = isTeam;
        this.vong = vong;
        this.cap = cap;
        this.diemThuong = diemThuong;
        this.listDangKyCN = listDangKyCN;
        this.listDangKyDoi = listDangKyDoi;
        this.set11 = set11;
        this.set12 = set12;
        this.set21 = set21;
        this.set22 = set22;
        this.set31 = set31;
        this.set32 = set32;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public San getSan() {
        return san;
    }

    public void setSan(San san) {
        this.san = san;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public NoiDung getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(NoiDung noiDung) {
        this.noiDung = noiDung;
    }

    public boolean isIsTeam() {
        return isTeam;
    }

    public void setIsTeam(boolean isTeam) {
        this.isTeam = isTeam;
    }

    public int getVong() {
        return vong;
    }

    public void setVong(int vong) {
        this.vong = vong;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public int getDiemThuong() {
        return diemThuong;
    }

    public void setDiemThuong(int diemThuong) {
        this.diemThuong = diemThuong;
    }

    public ArrayList<DangKyThiDauCaNhan> getListDangKyCN() {
        return listDangKyCN;
    }

    public void setListDangKyCN(ArrayList<DangKyThiDauCaNhan> listDangKyCN) {
        this.listDangKyCN = listDangKyCN;
    }

    public ArrayList<DangKyThiDauDoi> getListDangKyDoi() {
        return listDangKyDoi;
    }

    public void setListDangKyDoi(ArrayList<DangKyThiDauDoi> listDangKyDoi) {
        this.listDangKyDoi = listDangKyDoi;
    }

    public int getSet11() {
        return set11;
    }

    public void setSet11(int set11) {
        this.set11 = set11;
    }

    public int getSet12() {
        return set12;
    }

    public void setSet12(int set12) {
        this.set12 = set12;
    }

    public int getSet21() {
        return set21;
    }

    public void setSet21(int set21) {
        this.set21 = set21;
    }

    public int getSet22() {
        return set22;
    }

    public void setSet22(int set22) {
        this.set22 = set22;
    }

    public int getSet31() {
        return set31;
    }

    public void setSet31(int set31) {
        this.set31 = set31;
    }

    public int getSet32() {
        return set32;
    }

    public void setSet32(int set32) {
        this.set32 = set32;
    }
    
    
    
}
