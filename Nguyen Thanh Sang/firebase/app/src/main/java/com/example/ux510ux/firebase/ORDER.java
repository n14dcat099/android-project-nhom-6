package com.example.ux510ux.firebase;

/**
 * Created by UX510UX on 21/04/2018.
 */

public class ORDER {
    private String tenlop;
    private Integer sisoLop;

    public ORDER() {

    }
    public ORDER(ORDER order) {
        this.tenlop= order.tenlop;
        this.sisoLop= order.sisoLop;
    }

    public ORDER(String tenlop, Integer sisoLop) {
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
