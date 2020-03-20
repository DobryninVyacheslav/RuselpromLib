package ru.ruselprom.fet.operations;

import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSolid.Solid;

import ru.ruselprom.base.CreoSolid;

public abstract class FetOperations extends CreoSolid {
	
	protected static final String OPTION_NAME = "regen_failure_handling";
	protected static final String OPTION_VALUE = "resolve_mode";

    public FetOperations(Solid currSolid, Session session) {
        super(currSolid, session);
    }
}
