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
public class DangKyThiDauDoi implements Serializable{
    
    private Doi doi;
    private boolean isFirst;
    private String ghiChu;

    public DangKyThiDauDoi() {
    }

    public DangKyThiDauDoi(Doi doi, boolean isFirst, String ghiChu) {
        this.doi = doi;
        this.isFirst = isFirst;
        this.ghiChu = ghiChu;
    }

    public Doi getDoi() {
        return doi;
    }

    public void setDoi(Doi doi) {
        this.doi = doi;
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
