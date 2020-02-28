package ru.ruselprom.base;

import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcSession.Session;

public abstract class CreoModel extends CreoObject {
    protected Model currModel;

    public CreoModel(Model currModel, Session session) {
        super(session);
        this.currModel = currModel;
    }

    public Model getCurrModel() {
        return currModel;
    }

    public void setCurrModel(Model currModel) {
        this.currModel = currModel;
    }
}
