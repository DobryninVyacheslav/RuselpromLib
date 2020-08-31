package ru.ruselprom.lib.assembly.adding;

import com.ptc.cipjava.intseq;
import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcAssembly.Assembly;
import com.ptc.pfc.pfcAssembly.ComponentPath;
import com.ptc.pfc.pfcAssembly.pfcAssembly;
import com.ptc.pfc.pfcBase.Matrix3D;
import com.ptc.pfc.pfcBase.Transform3D;
import com.ptc.pfc.pfcBase.pfcBase;
import com.ptc.pfc.pfcComponentFeat.ComponentConstraint;
import com.ptc.pfc.pfcComponentFeat.ComponentConstraintType;
import com.ptc.pfc.pfcComponentFeat.ComponentConstraints;
import com.ptc.pfc.pfcComponentFeat.ComponentFeat;
import com.ptc.pfc.pfcComponentFeat.pfcComponentFeat;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModel.ModelType;
import com.ptc.pfc.pfcModelItem.ModelItem;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSelect.Selection;
import com.ptc.pfc.pfcSelect.pfcSelect;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.Solid;

import ru.ruselprom.lib.argument.assembly.RefCoordSystems;

public class ComponentOfAsm extends AbstractComponentOfAsm {

	public ComponentOfAsm(Model currModel) {
        super(currModel);
    }

    public void addToAsmByCsys (Model currCompModel, RefCoordSystems refCoordSystems) throws jxthrowable {
    	Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		Matrix3D identityMatrix = createIdentityMatrix();
		Transform3D transf = pfcBase.Transform3D_Create (identityMatrix);
		/*-----------------------------------------------------------------*\
		Check the current assembly
		\*-----------------------------------------------------------------*/
		
		if (currModel == null || currModel.GetType() != ModelType.MDL_ASSEMBLY) {
			throw new RuntimeException ("Current model is not an assembly.");
		}
		Assembly assembly = (Assembly) currModel;
		Solid compModel = (Solid)currCompModel;
		
		if (compModel == null) {
			session.UIShowMessageDialog("compModel is null!", null);
			return;
		}
		/*-----------------------------------------------------------------*\
		Package the component initially
		\*-----------------------------------------------------------------*/
		ComponentFeat asmComp = (ComponentFeat) assembly.AssembleComponent (compModel, transf);
		/*-----------------------------------------------------------------*\
		Prepare the constraints array
		\*-----------------------------------------------------------------*/
		ComponentConstraints constrs = ComponentConstraints.create ();
		/*-----------------------------------------------------------------*\
		Find the assembly datum 
		\*-----------------------------------------------------------------*/
		ModelItem asmItem = assembly.GetItemByName (ModelItemType.ITEM_COORD_SYS, refCoordSystems.getAsmCsysName());
		/*-----------------------------------------------------------------*\
		Find the component datum
		\*-----------------------------------------------------------------*/
		ModelItem compItem = compModel.GetItemByName (ModelItemType.ITEM_COORD_SYS, refCoordSystems.getCompCsysName());
		/*-----------------------------------------------------------------*\
		For the assembly reference, initialize a component path.
		This is necessary even if the reference geometry is in the assembly.
		\*-----------------------------------------------------------------*/
		intseq ids = intseq.create ();
		ComponentPath path = pfcAssembly.CreateComponentPath (assembly, ids);
		/*-----------------------------------------------------------------*\
		Allocate the references
		\*-----------------------------------------------------------------*/
		Selection asmSel = pfcSelect.CreateModelItemSelection (asmItem, path);
		Selection compSel = pfcSelect.CreateModelItemSelection (compItem, null);
		/*-----------------------------------------------------------------*\
		Allocate and fill the constraint.
		\*-----------------------------------------------------------------*/
		ComponentConstraint constr = pfcComponentFeat.ComponentConstraint_Create (ComponentConstraintType.ASM_CONSTRAINT_CSYS);
		constr.SetAssemblyReference (asmSel);
		constr.SetComponentReference (compSel);
		constrs.insert (constrs.getarraysize(), constr);
		/*-----------------------------------------------------------------*\
		Set the assembly component constraints and regenerate the assembly.
		\*-----------------------------------------------------------------*/
		asmComp.SetName(compName);
		asmComp.SetConstraints (constrs, null);
	}

	private Matrix3D createIdentityMatrix() throws jxthrowable {
		Matrix3D identityMatrix = Matrix3D.create ();
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (x == y)
					identityMatrix.set (x, y, 1.0);
				else
					identityMatrix.set (x, y, 0.0);
			}			
		}
		return identityMatrix;
	}
}