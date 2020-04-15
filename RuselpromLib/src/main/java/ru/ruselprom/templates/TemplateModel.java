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
    private String newModelName;
	

    public TemplateModel(String tempName, String tempPath, String newModelName) {
		this.tempName = tempName;
		this.tempPath = tempPath;
		this.newModelName = newModelName;
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

	public String getNewModelName() {
        return newModelName;
    }

    public void setNewModelName(String newModelName) {
        this.newModelName = newModelName;
    }

    public void createModelWithDisp() throws jxthrowable {
    	Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		Model template = retrieveTemp();
		if (session.GetModel(newModelName, ModelType.MDL_ASSEMBLY) != null ||
			session.GetModel(newModelName, ModelType.MDL_PART) != null ||
			session.GetModel(newModelName, ModelType.MDL_DRAWING) != null) {
			template.Erase();
			return;
		}
		Model newModel = template.CopyAndRetrieve(newModelName, null);
		Window newWindow = session.CreateModelWindow(newModel); 
	    newModel.Display(); 
	    session.SetCurrentWindow(newWindow); 
	    newWindow.Activate();
	    template.Erase();
	}
	
	public void createModel() throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		Model template = retrieveTemp();
		if (session.GetModel(newModelName, ModelType.MDL_ASSEMBLY) != null ||
			session.GetModel(newModelName, ModelType.MDL_PART) != null ||
			session.GetModel(newModelName, ModelType.MDL_DRAWING) != null) {
			template.Erase();
			return;
		}
		template.CopyAndRetrieve(newModelName, null);
	    template.Erase();
	}
	
	public Model retrieveTemp() throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		ModelDescriptor desc = pfcModel.ModelDescriptor_CreateFromFileName(tempName);
		desc.SetPath (tempPath);
		return session.RetrieveModelWithOpts(desc, pfcSession.RetrieveModelOptions_Create());
	}
}