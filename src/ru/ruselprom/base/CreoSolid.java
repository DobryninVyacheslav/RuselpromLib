package ru.ruselprom.base;

import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSolid.Solid;

public abstract class CreoSolid extends CreoObject {
    protected Solid currSolid;

    public CreoSolid(Solid currSolid, Session session) {
        super(session);
        this.currSolid = currSolid;
    }

    public Solid getCurrSolid() {
        return currSolid;
    }

    public void setCurrSolid(Solid currSolid) {
        this.currSolid = currSolid;
    }
}
