package com.example.ux510ux.firebase;

/**
 * Created by UX510UX on 21/04/2018.
 */

public class DiemDanh {
    private String tenlop;
    private Integer sisoLop;

    public DiemDanh() {

    }
    public DiemDanh(DiemDanh diemDanh) {
        this.tenlop= diemDanh.tenlop;
        this.sisoLop= diemDanh.sisoLop;
    }

    public DiemDanh(String tenlop, Integer sisoLop) {
        this.tenlop = tenlop;
        this.sisoLop = sisoLop;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenDT(String tenlop) {
        this.tenlop = tenlop;
    }

    public Integer getSiSolop() {
        return sisoLop;
    }

    public void setSiSoLop(Integer sisoLop) {
        this.sisoLop = sisoLop;
    }
}
