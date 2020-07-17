package ru.ruselprom.lib.base;

import com.ptc.pfc.pfcSolid.Solid;

public abstract class CreoSolid {
    protected Solid currSolid;

    public CreoSolid(Solid currSolid) {
        this.currSolid = currSolid;
    }

    public Solid getCurrSolid() {
        return currSolid;
    }

    public void setCurrSolid(Solid currSolid) {
        this.currSolid = currSolid;
    }
}
