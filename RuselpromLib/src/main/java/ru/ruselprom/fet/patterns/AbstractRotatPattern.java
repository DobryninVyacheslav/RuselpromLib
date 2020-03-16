package ru.ruselprom.fet.patterns;

import com.ptc.pfc.pfcSession.Session;
import ru.ruselprom.base.CreoObject;

public abstract class AbstractRotatPattern extends CreoObject {
    
    protected String refAxisName;

    public AbstractRotatPattern(String refAxisName, Session session) {
        super(session);
        this.refAxisName = refAxisName;
    }

    public String getRefAxisName() {
        return refAxisName;
    }

    public void setRefAxisName(String refAxisName) {
        this.refAxisName = refAxisName;
    }
}
