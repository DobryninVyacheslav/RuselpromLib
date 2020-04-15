package ru.ruselprom.assembly.adding;

import com.ptc.pfc.pfcModel.Model;

import ru.ruselprom.base.CreoModel;

public abstract class AbstractComponentOfAsm extends CreoModel {

    protected String compName;
    
    public AbstractComponentOfAsm(Model currModel) {
        super(currModel);
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }
}
