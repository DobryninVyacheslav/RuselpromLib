package ru.ruselprom.fet.operations;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcComponentFeat.ComponentFeat;
import com.ptc.pfc.pfcExceptions.XToolkitNotFound;
import com.ptc.pfc.pfcFeature.DeleteOperation;
import com.ptc.pfc.pfcFeature.FeatureOperations;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSolid.RegenInstructions;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.pfc.pfcSolid.pfcSolid;

public class DeleteOperations extends FetOperations {

	public DeleteOperations(Solid currSolid, Session session) {
		super(currSolid, session);
	}
	public void deleteComponent(String...compNames) {
        try {
            FeatureOperations featOperations = FeatureOperations.create();
            session.SetConfigOption(OPTION_NAME, OPTION_VALUE);
            int index = 0;
            for (String compName : compNames) {
                ComponentFeat component = (ComponentFeat)currSolid.GetFeatureByName(compName);                
                DeleteOperation featDeleteOp = component.CreateDeleteOp();
                featOperations.insert(index, featDeleteOp);
                index++;
            }
            RegenInstructions instrForReg = pfcSolid.RegenInstructions_Create(Boolean.FALSE, null, null);
            currSolid.ExecuteFeatureOps(featOperations, instrForReg);
        } catch (XToolkitNotFound e) {
            e.printStackTrace();
        } catch (jxthrowable e) {
            e.printStackTrace();
        }
    }
    public void deleteFeature(String...featNames) {
        try {
            FeatureOperations featOperations = FeatureOperations.create();
            session.SetConfigOption(OPTION_NAME, OPTION_VALUE);
            int index = 0;
            for (String featName : featNames) {
                DeleteOperation featDeleteOp = currSolid.GetFeatureByName(featName).CreateDeleteOp();
                featOperations.insert(index, featDeleteOp);
                index++;
            }
            RegenInstructions instrForReg = pfcSolid.RegenInstructions_Create(Boolean.FALSE, null, null);
            currSolid.ExecuteFeatureOps(featOperations, instrForReg);
        } catch (XToolkitNotFound e) {
            e.printStackTrace();
        } catch (jxthrowable e) {
            e.printStackTrace();
        }
    }
}
