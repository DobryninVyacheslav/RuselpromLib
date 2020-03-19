package ru.ruselprom.base;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.wfc.wfcSession.WSession;

public class Macro extends CreoObject {

	public Macro(Session session) {
		super(session);
	}
	
	public void execute (String macroCode) throws jxthrowable {
		session.GetCurrentWindow().Activate();
		session.RunMacro(macroCode);
        ((WSession)session).ExecuteMacro();
	}
}
