package ru.ruselprom.lib.assembly;

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
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.wfc.wfcAssembly.WAssembly;
import com.ptc.wfc.wfcComponentFeat.AssemblyItem;
import com.ptc.wfc.wfcComponentFeat.AssemblyItemInstructions;
import com.ptc.wfc.wfcComponentFeat.AssemblyItems;
import com.ptc.wfc.wfcComponentFeat.WComponentFeat;
import com.ptc.wfc.wfcComponentFeat.wfcComponentFeat;

import ru.ruselprom.lib.assembly.argument.ComponentOfAsm;
import ru.ruselprom.lib.assembly.argument.FlexDimensions;
import ru.ruselprom.lib.assembly.argument.RefCoordSystems;

public class AssemblyBuilder {
	
	private Model currAsm;
	private ComponentFeat componentFeat;
	
	public AssemblyBuilder(Model currAsm) {
		this.currAsm = currAsm;
	}

    public void addToAsmByCsys (ComponentOfAsm componentOfAsm, RefCoordSystems refCoordSystems) throws jxthrowable {
		Matrix3D identityMatrix = createIdentityMatrix();
		Transform3D transf = pfcBase.Transform3D_Create (identityMatrix);
		
		checkCurrAsmAndCurrCompModel(componentOfAsm.getCurrCompModel());
		Assembly assembly = (Assembly) currAsm;
		Solid currCompSolid = (Solid)componentOfAsm.getCurrCompModel();
		/*-----------------------------------------------------------------*\
		Package the component initially
		\*-----------------------------------------------------------------*/
		componentFeat = (ComponentFeat) assembly.AssembleComponent (currCompSolid, transf);
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
		ModelItem compItem = currCompSolid.GetItemByName (ModelItemType.ITEM_COORD_SYS, refCoordSystems.getCompCsysName());
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
		componentFeat.SetName(componentOfAsm.getInternalName());
		componentFeat.SetConstraints (constrs, null);
	}
    
    public void addToAsmByCsysWithFlexDims (FlexDimensions flexDims, ComponentOfAsm componentOfAsm, RefCoordSystems refCoordSystems) throws jxthrowable {
    	addToAsmByCsys(componentOfAsm, refCoordSystems);
    	makeCompFlex(flexDims.getDimensions(), componentOfAsm.getCurrCompModel());
    }
    
    private void makeCompFlex(String[] dimensionNames, Model compModel) throws jxthrowable {
		WAssembly wAsm = (WAssembly)(currAsm);
		WComponentFeat wComponentFeat = (WComponentFeat)(componentFeat);
		AssemblyItems flexDims = AssemblyItems.create();
		for (String dimensionName : dimensionNames) {
			AssemblyItemInstructions flexDimInstr = wfcComponentFeat.AssemblyItemInstructions_Create(compModel, ModelItemType.ITEM_DIMENSION, 0);
			flexDimInstr.SetItemName(dimensionName);
			AssemblyItem flexDim = wAsm.CreateAssemblyItem(flexDimInstr);
			flexDims.append(flexDim);
		}
		wComponentFeat.SetAsFlexible(flexDims);
	}

	private void checkCurrAsmAndCurrCompModel(Model currCompModel) throws jxthrowable {
		if (currAsm == null || currAsm.GetType() != ModelType.MDL_ASSEMBLY) {
			throw new RuntimeException ("Current model is not an assembly.");
		}
		if (currCompModel == null) {
			throw new RuntimeException ("Current component model is null.");
		}
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