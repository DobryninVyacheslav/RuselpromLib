package ru.ruselprom.lib.argument.assembly;

public class RefCoordSystems {
	private String compCsysName;
	private String asmCsysName;
	
	public RefCoordSystems(String compCsysName, String asmCsysName) {
		this.compCsysName = compCsysName;
		this.asmCsysName = asmCsysName;
	}

	public String getCompCsysName() {
		return compCsysName;
	}

	public void setCompCsysName(String compCsysName) {
		this.compCsysName = compCsysName;
	}

	public String getAsmCsysName() {
		return asmCsysName;
	}

	public void setAsmCsysName(String asmCsysName) {
		this.asmCsysName = asmCsysName;
	}	
}
