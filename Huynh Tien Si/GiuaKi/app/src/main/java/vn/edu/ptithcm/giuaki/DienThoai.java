package vn.edu.ptithcm.giuaki;

/**
 * Created by Tien Si Huynh on 4/20/2018.
 */

public class  DienThoai {
    private String tenDT;
    private Integer giaDT;

    public DienThoai() {

    }
    public DienThoai(DienThoai dienThoai) {
        this.tenDT=dienThoai.tenDT;
        this.giaDT=dienThoai.giaDT;
    }

    public DienThoai(String tenDT, Integer giaDT) {
        this.tenDT = tenDT;
        this.giaDT = giaDT;
    }

    public String getTenDT() {
        return tenDT;
    }

    public void setTenDT(String tenDT) {
        this.tenDT = tenDT;
    }

    public Integer getGiaDT() {
        return giaDT;
    }

    public void setGiaDT(Integer giaDT) {
        this.giaDT = giaDT;
    }
}
