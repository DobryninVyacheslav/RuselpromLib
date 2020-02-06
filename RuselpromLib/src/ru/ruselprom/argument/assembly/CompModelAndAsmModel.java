package ru.ruselprom.argument.assembly;

import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcSession.Session;

public class CompModelAndAsmModel {
	private Session session;
	Model compModel;
	Model asmModel;
	
	public CompModelAndAsmModel(Session session, Model compModel, Model asmModel) {
		this.session = session;
		this.compModel = compModel;
		this.asmModel = asmModel;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Model getCompModel() {
		return compModel;
	}

	public void setCompModel(Model compModel) {
		this.compModel = compModel;
	}

	public Model getAsmModel() {
		return asmModel;
	}

	public void setAsmModel(Model asmModel) {
		this.asmModel = asmModel;
	}
	
}
