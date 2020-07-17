package ru.ruselprom.lib.base;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.RegenInstructions;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.pfc.pfcSolid.pfcSolid;

public class Regeneration {

	private Regeneration() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void regenerateSolid (Solid currSolid) throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		session.SetConfigOption("regen_failure_handling", "resolve_mode");
		RegenInstructions instrForReg = pfcSolid.RegenInstructions_Create(Boolean.TRUE, null, null);
		currSolid.Regenerate(instrForReg);
	}
}
