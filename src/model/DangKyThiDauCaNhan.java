/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author HIEU
 */
public class DangKyThiDauCaNhan implements Serializable {
    
    private VanDongVien vdv;
    private boolean isFirst;
    private String ghiChu;

    public DangKyThiDauCaNhan(VanDongVien vdv, boolean isFirst, String ghiChu) {
        this.vdv = vdv;
        this.isFirst = isFirst;
        this.ghiChu = ghiChu;
    }

    public VanDongVien getVdv() {
        return vdv;
    }

    public void setVdv(VanDongVien vdv) {
        this.vdv = vdv;
    }

    public boolean isIsFirst() {
        return isFirst;
    }

    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
    
}
