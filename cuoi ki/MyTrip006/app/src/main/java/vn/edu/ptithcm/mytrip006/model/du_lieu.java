package vn.edu.ptithcm.mytrip006.model;

public class du_lieu {

    public String thanh_pho;
    public String ten;
    public String mota;
    public String hinh_anh;
    public String khu_vuc;
    public String loai_hinh;
    public String name_User;


    public du_lieu(String thanh_pho, String ten, String mota, String hinh_anh, String khu_vuc, String loai_hinh, String name_User) {
        this.thanh_pho = thanh_pho;
        this.ten = ten;
        this.mota = mota;
        this.hinh_anh = hinh_anh;
        this.khu_vuc = khu_vuc;
        this.loai_hinh = loai_hinh;
        this.name_User = name_User;
    }

    public du_lieu(){

    }

    public String getName_User() {
        return name_User;
    }

    public void setName_User(String name_User) {
        this.name_User = name_User;
    }

    public String getThanh_pho() {
        return thanh_pho;
    }

    public void setThanh_pho(String thanh_pho) {
        this.thanh_pho = thanh_pho;
    }


    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinh_anh() {
        return hinh_anh;
    }

    public void setHinh_anh(String hinh_anh) {
        this.hinh_anh = hinh_anh;
    }

    public String getKhu_vuc() {
        return khu_vuc;
    }

    public void setKhu_vuc(String khu_vuc) {
        this.khu_vuc = khu_vuc;
    }

    public String getLoai_hinh() {
        return loai_hinh;
    }

    public void setLoai_hinh(String loai_hinh) {
        this.loai_hinh = loai_hinh;
    }

    public String toText() {
        String text = this.khu_vuc + "\n" + this.thanh_pho + "\n" + this.ten + "\n" + this.mota + "\n" + this.hinh_anh;
        return text;
    }

}