package ru.ruselprom.fet.operations;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcComponentFeat.ComponentFeat;
import com.ptc.pfc.pfcFeature.FeatureOperations;
import com.ptc.pfc.pfcFeature.ResumeOperation;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.RegenInstructions;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.pfc.pfcSolid.pfcSolid;

public class ResumeOperations extends FetOperations {

	public ResumeOperations(Solid currSolid) {
		super(currSolid);
	}
	public void resumeComponent(String...compNames) throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
        FeatureOperations featOperations = FeatureOperations.create();
        session.SetConfigOption(OPTION_NAME, OPTION_VALUE);
        int index = 0;
        for (String compName : compNames) {
            ComponentFeat component = (ComponentFeat)currSolid.GetFeatureByName(compName);                
            ResumeOperation featDeleteOp = component.CreateResumeOp();
            featOperations.insert(index, featDeleteOp);
            index++;
        }
        RegenInstructions instrForReg = pfcSolid.RegenInstructions_Create(Boolean.FALSE, null, null);
        currSolid.ExecuteFeatureOps(featOperations, instrForReg);
    }
    public void resumeFeature(String...featNames) throws jxthrowable {
    	Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
        FeatureOperations featOperations = FeatureOperations.create();
        session.SetConfigOption(OPTION_NAME, OPTION_VALUE);
        int index = 0;
        for (String featName : featNames) {
        	ResumeOperation featDeleteOp = currSolid.GetFeatureByName(featName).CreateResumeOp();
            featOperations.insert(index, featDeleteOp);
            index++;
        }
        RegenInstructions instrForReg = pfcSolid.RegenInstructions_Create(Boolean.FALSE, null, null);
        currSolid.ExecuteFeatureOps(featOperations, instrForReg);
    }
}
