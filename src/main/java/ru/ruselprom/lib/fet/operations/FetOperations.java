package ru.ruselprom.lib.fet.operations;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcComponentFeat.ComponentFeat;
import com.ptc.pfc.pfcFeature.DeleteOperation;
import com.ptc.pfc.pfcFeature.FeatureOperations;
import com.ptc.pfc.pfcFeature.ResumeOperation;
import com.ptc.pfc.pfcFeature.SuppressOperation;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.RegenInstructions;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.pfc.pfcSolid.pfcSolid;

public final class FetOperations {
	
	protected static final String OPTION_NAME = "regen_failure_handling";
	protected static final String OPTION_VALUE = "resolve_mode";

	private FetOperations() {
	    throw new IllegalStateException("Utility class");
	  }
    
    public static void deleteComponent(Solid currSolid, String...compNames) throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
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
    }
    
    public static void deleteFeature(Solid currSolid, String...featNames) throws jxthrowable {
    	Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
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
    }
    
    public static void suppressComponent(Solid currSolid, String...compNames) throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
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
    }
    
    public static void suppressFeature(Solid currSolid, String...featNames) throws jxthrowable {
    	Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
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
    }
    
    public static void resumeComponent(Solid currSolid, String...compNames) throws jxthrowable {
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
    
    public static void resumeFeature(Solid currSolid, String...featNames) throws jxthrowable {
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
