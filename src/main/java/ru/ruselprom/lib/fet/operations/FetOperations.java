package ru.ruselprom.lib.fet.operations;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcComponentFeat.ComponentFeat;
import com.ptc.pfc.pfcFeature.FeatureOperations;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.RegenInstructions;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.pfc.pfcSolid.pfcSolid;
import com.ptc.wfc.wfcComponentFeat.WComponentFeat;
import com.ptc.wfc.wfcFeature.WFeature;

import ru.ruselprom.lib.exceptions.CreoSessionException;

public final class FetOperations {
	
	protected static final String OPTION_NAME = "regen_failure_handling";
	protected static final String OPTION_VALUE = "resolve_mode";
	
	private FetOperations() {
	    throw new IllegalStateException("Utility class");
	  }

	static {
		try {
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			session.SetConfigOption(OPTION_NAME, OPTION_VALUE);
		} catch (jxthrowable e) {
			throw new CreoSessionException("failed to set options to session");
		}
	}
    
    public static void deleteComponent(Solid currSolid, String...compNames) throws jxthrowable {
        FeatureOperations featOperations = FeatureOperations.create();
        int index = 0;
        for (String compName : compNames) {
            ComponentFeat component = (ComponentFeat)currSolid.GetFeatureByName(compName);                
            featOperations.insert(index, component.CreateDeleteOp());
            index++;
        }
        RegenInstructions instrForReg = pfcSolid.RegenInstructions_Create(Boolean.FALSE, null, null);
        currSolid.ExecuteFeatureOps(featOperations, instrForReg);
    }
    
    public static void deleteFeature(Solid currSolid, String...featNames) throws jxthrowable {
        FeatureOperations featOperations = FeatureOperations.create();
        int index = 0;
        for (String featName : featNames) {
            featOperations.insert(index, currSolid.GetFeatureByName(featName).CreateDeleteOp());
            index++;
        }
        RegenInstructions instrForReg = pfcSolid.RegenInstructions_Create(Boolean.FALSE, null, null);
        currSolid.ExecuteFeatureOps(featOperations, instrForReg);
    }
    
    public static void suppressComponent(Solid currSolid, String...compNames) throws jxthrowable {
        FeatureOperations featOperations = FeatureOperations.create();
        int index = 0;
        for (String compName : compNames) {
            WComponentFeat component = (WComponentFeat)currSolid.GetFeatureByName(compName);
            if (!component.GetStatusFlag().GetIsSuppressed()) {
            	featOperations.insert(index, component.CreateSuppressOp());
            	index++;
            }
        }
        RegenInstructions instrForReg = pfcSolid.RegenInstructions_Create(Boolean.FALSE, null, null);
        currSolid.ExecuteFeatureOps(featOperations, instrForReg);
    }
    
    public static void suppressFeature(Solid currSolid, String...featNames) throws jxthrowable {
        FeatureOperations featOperations = FeatureOperations.create();
        int index = 0;
        for (String featName : featNames) {
        	WFeature feature = (WFeature)currSolid.GetFeatureByName(featName);
        	if (!feature.GetStatusFlag().GetIsSuppressed()) {
        		featOperations.insert(index, feature.CreateSuppressOp());
        		index++;
        	}
        }
        RegenInstructions instrForReg = pfcSolid.RegenInstructions_Create(Boolean.FALSE, null, null);
        currSolid.ExecuteFeatureOps(featOperations, instrForReg);
    }
    
    public static void resumeComponent(Solid currSolid, String...compNames) throws jxthrowable {
    	FeatureOperations featOperations = FeatureOperations.create();
    	int index = 0;
    	for (String compName : compNames) {
    		WComponentFeat component = (WComponentFeat)currSolid.GetFeatureByName(compName);
    		if (component.GetStatusFlag().GetIsSuppressed()) {
    			featOperations.insert(index, component.CreateResumeOp());
    			index++;
    		}
    	}
    	RegenInstructions instrForReg = pfcSolid.RegenInstructions_Create(Boolean.FALSE, null, null);
    	currSolid.ExecuteFeatureOps(featOperations, instrForReg);
    }
    
    public static void resumeFeature(Solid currSolid, String...featNames) throws jxthrowable {
    	FeatureOperations featOperations = FeatureOperations.create();
    	int index = 0;
    	for (String featName : featNames) {
    		WFeature feature = (WFeature)currSolid.GetFeatureByName(featName);
    		if (feature.GetStatusFlag().GetIsSuppressed()) {
    			featOperations.insert(index, feature.CreateResumeOp());
    			index++;
    		}
    	}
    	RegenInstructions instrForReg = pfcSolid.RegenInstructions_Create(Boolean.FALSE, null, null);
    	currSolid.ExecuteFeatureOps(featOperations, instrForReg);
    }
}
