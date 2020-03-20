package ru.ruselprom.base;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSolid.RegenInstructions;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.pfc.pfcSolid.pfcSolid;

public class Regeneration extends CreoObject {

	public Regeneration(Session session) {
		super(session);
	}

	public void regenerateSolid (Solid currSolid) throws jxthrowable {
		session.SetConfigOption("regen_failure_handling", "resolve_mode");
		RegenInstructions instrForReg = pfcSolid.RegenInstructions_Create(Boolean.TRUE, null, null);
		currSolid.Regenerate(instrForReg);
	}
}
