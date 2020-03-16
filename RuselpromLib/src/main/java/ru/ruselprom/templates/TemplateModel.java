package ru.ruselprom.templates;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcModel.pfcModel;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcWindow.Window;

import ru.ruselprom.base.CreoObject;

public class TemplateModel extends CreoObject {
    private String modelName;
    private String modelPath;
    private String newModelName;
	
    public TemplateModel(String modelName, String modelPath, String newModelName, Session session) {
        super(session);
        this.modelName = modelName;
        this.modelPath = modelPath;
        this.newModelName = newModelName;
    }
    
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelLocation() {
        return modelPath;
    }

    public void setModelLocation(String modelLocation) {
        this.modelPath = modelLocation;
    }

    public String getNewModelName() {
        return newModelName;
    }

    public void setNewModelName(String newModelName) {
        this.newModelName = newModelName;
    }

    public void createModelWithDisp() throws jxthrowable {
    	try	{
			ModelDescriptor desc = pfcModel.ModelDescriptor_CreateFromFileName(modelName);
			desc.SetPath (modelPath);
			Model template = session.RetrieveModelWithOpts(desc, pfcSession.RetrieveModelOptions_Create());
			
			Model newModel = template.CopyAndRetrieve(newModelName, null);
			Window newWindow = session.CreateModelWindow(newModel); 
		    newModel.Display(); 
		    session.SetCurrentWindow(newWindow); 
		    newWindow.Activate();
		    template.Erase();
		    
		} catch (Exception e) {
			Model template = session.GetModel(modelName, ModelType.MDL_PART);
			template.Erase();
			session.UIShowMessageDialog("Error - " + e, null);
		}
	}
	
	public void createModel() throws jxthrowable {
    	try	{
			ModelDescriptor desc = pfcModel.ModelDescriptor_CreateFromFileName(modelName);
			desc.SetPath (modelPath);
			Model template = session.RetrieveModelWithOpts(desc, pfcSession.RetrieveModelOptions_Create());
			template.CopyAndRetrieve(newModelName, null);
		    template.Erase();
		} catch (Exception e) {
			Model template = session.GetModelFromFileName(modelName);
			template.Erase();
			session.UIShowMessageDialog("Error - " + e, null);
		}
	}
	
	public Model transferPart() throws jxthrowable {
		
		try {
			return session.GetModel(newModelName + ".prt", ModelType.MDL_PART);
		} catch (Exception e) {
			session.UIShowMessageDialog("Error - " + e, null);
			return null;
		}
		
	}
}