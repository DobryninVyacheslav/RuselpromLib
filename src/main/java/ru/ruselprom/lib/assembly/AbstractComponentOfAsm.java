package ru.ruselprom.lib.assembly;

import com.ptc.pfc.pfcModel.Model;

import ru.ruselprom.lib.base.CreoModel;

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
