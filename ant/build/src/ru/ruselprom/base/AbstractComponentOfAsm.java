package ru.ruselprom.base;

import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcSession.Session;

public abstract class AbstractComponentOfAsm extends CreoModel {

    protected String compName;
    
    public AbstractComponentOfAsm(Model currModel, Session session) {
        super(currModel, session);
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }
}
