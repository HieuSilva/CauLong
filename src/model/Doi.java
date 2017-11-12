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
    
}
