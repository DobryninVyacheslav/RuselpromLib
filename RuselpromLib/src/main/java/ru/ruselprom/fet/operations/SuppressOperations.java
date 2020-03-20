package ru.ruselprom.fet.operations;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcComponentFeat.ComponentFeat;
import com.ptc.pfc.pfcExceptions.XToolkitNotFound;
import com.ptc.pfc.pfcFeature.FeatureOperations;
import com.ptc.pfc.pfcFeature.SuppressOperation;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSolid.RegenInstructions;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.pfc.pfcSolid.pfcSolid;

public class SuppressOperations extends FetOperations {

	public SuppressOperations(Solid currSolid, Session session) {
		super(currSolid, session);
	}
	
	public void suppressComponent(String...compNames) {
        try {
            FeatureOperations featOperations = FeatureOperations.create();
            session.SetConfigOption(OPTION_NAME, OPTION_VALUE);
            int index = 0;
            for (String compName : compNames) {
                ComponentFeat component = (ComponentFeat)currSolid.GetFeatureByName(compName);                
                SuppressOperation featDeleteOp = component.CreateSuppressOp();
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
    public void suppressFeature(String...featNames) {
        try {
            FeatureOperations featOperations = FeatureOperations.create();
            session.SetConfigOption(OPTION_NAME, OPTION_VALUE);
            int index = 0;
            for (String featName : featNames) {
                SuppressOperation featDeleteOp = currSolid.GetFeatureByName(featName).CreateSuppressOp();
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
