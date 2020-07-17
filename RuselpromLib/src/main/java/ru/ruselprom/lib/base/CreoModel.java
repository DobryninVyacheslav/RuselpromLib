package ru.ruselprom.lib.base;

import com.ptc.pfc.pfcModel.Model;

public abstract class CreoModel {
    protected Model currModel;

    public CreoModel(Model currModel) {
        this.currModel = currModel;
    }

    public Model getCurrModel() {
        return currModel;
    }

    public void setCurrModel(Model currModel) {
        this.currModel = currModel;
    }
}
