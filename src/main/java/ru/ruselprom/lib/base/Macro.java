package ru.ruselprom.lib.base;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.wfc.wfcSession.WSession;

public class Macro {
	
	private Macro() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void execute (String macroCode) throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		session.GetCurrentWindow().Activate();
		session.RunMacro(macroCode);
        ((WSession)session).ExecuteMacro();
	}
}
