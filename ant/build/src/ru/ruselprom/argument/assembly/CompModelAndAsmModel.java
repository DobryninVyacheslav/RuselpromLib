package ru.ruselprom.argument.assembly;

import com.ptc.pfc.pfcModel.Model;

public class CompModelAndAsmModel {
	
	Model compModel;
	Model asmModel;
	
	public CompModelAndAsmModel(Model compModel, Model asmModel) {
		this.compModel = compModel;
		this.asmModel = asmModel;
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
