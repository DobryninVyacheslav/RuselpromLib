package ru.ruselprom.fet.operations;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcComponentFeat.ComponentFeat;
import com.ptc.pfc.pfcExceptions.XToolkitNotFound;
import com.ptc.pfc.pfcFeature.DeleteOperation;
import com.ptc.pfc.pfcFeature.FeatureOperations;
import com.ptc.pfc.pfcFeature.SuppressOperation;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSolid.RegenInstructions;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.pfc.pfcSolid.pfcSolid;

import ru.ruselprom.base.CreoSolid;

public class FetOperations extends CreoSolid {

    public FetOperations(Solid currSolid, Session session) {
        super(currSolid, session);
    }

    public void deleteComponent(String...compNames) {
        try {
            FeatureOperations featOperations = FeatureOperations.create();
            session.SetConfigOption("regen_failure_handling", "resolve_mode");
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
            session.SetConfigOption("regen_failure_handling", "resolve_mode");
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
    public void suppressComponent(String...compNames) {
        try {
            FeatureOperations featOperations = FeatureOperations.create();
            session.SetConfigOption("regen_failure_handling", "resolve_mode");
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
            session.SetConfigOption("regen_failure_handling", "resolve_mode");
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
