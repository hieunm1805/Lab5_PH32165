package com.hieunguyen.fpoly.lab5_ph32165;

import java.io.Serializable;

public class SinhVienModel implements Serializable {
    public String coSo;
    public String hoTen;
    public String diaChi;

    public SinhVienModel(String coSo, String hoTen, String diaChi) {
        this.coSo = coSo;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
    }
}
