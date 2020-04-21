package ru.ruselprom.templates;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcModel.pfcModel;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcWindow.Window;

public class TemplateModel {
    private String tempName;
    private String tempPath;

    public TemplateModel(String tempName, String tempPath) {
		this.tempName = tempName;
		this.tempPath = tempPath;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}

	public Model retrAndCopyInNewFileWithDisp(String newModelName) throws jxthrowable {
    	Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		Model template = retrieve();
		Model asmMdl = session.GetModel(newModelName, ModelType.MDL_ASSEMBLY);
		Model partMdl = session.GetModel(newModelName, ModelType.MDL_PART);
		Model dwrMdl = session.GetModel(newModelName, ModelType.MDL_DRAWING);
		if (asmMdl != null || partMdl != null || dwrMdl != null) {
			template.Erase();
			if (asmMdl != null) {
				return asmMdl;
			}
			return partMdl != null ? partMdl : dwrMdl;
		}
		Model newMdl = template.CopyAndRetrieve(newModelName, null);
		Window newWindow = session.CreateModelWindow(newMdl); 
	    newMdl.Display(); 
	    session.SetCurrentWindow(newWindow); 
	    newWindow.Activate();
	    template.Erase();
	    return newMdl;
	}
	
	public Model retrAndCopyInNewFile(String newModelName) throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		Model template = retrieve();
		Model asmMdl = session.GetModel(newModelName, ModelType.MDL_ASSEMBLY);
		Model partMdl = session.GetModel(newModelName, ModelType.MDL_PART);
		Model dwrMdl = session.GetModel(newModelName, ModelType.MDL_DRAWING);
		if (asmMdl != null || partMdl != null || dwrMdl != null) {
			template.Erase();
			if (asmMdl != null) {
				return asmMdl;
			}
			return partMdl != null ? partMdl : dwrMdl;
		}
		Model newMdl = template.CopyAndRetrieve(newModelName, null);
	    template.Erase();
	    return newMdl;
	}
	
	public Model retrieve() throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		ModelDescriptor desc = pfcModel.ModelDescriptor_CreateFromFileName(tempName);
		desc.SetPath (tempPath);
		return session.RetrieveModelWithOpts(desc, pfcSession.RetrieveModelOptions_Create());
	}
}