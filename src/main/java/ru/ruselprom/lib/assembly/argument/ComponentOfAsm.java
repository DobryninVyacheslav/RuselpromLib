package ru.ruselprom.lib.assembly.argument;

import com.ptc.pfc.pfcModel.Model;

public class ComponentOfAsm {
	private String internalName;
	private Model currCompModel;

	public ComponentOfAsm(String internalName, Model currCompModel) {
		this.internalName = internalName;
		this.currCompModel = currCompModel;
	}

	public String getInternalName() {
		return internalName;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}

	public Model getCurrCompModel() {
		return currCompModel;
	}

	public void setCurrCompModel(Model currCompModel) {
		this.currCompModel = currCompModel;
	}
}
