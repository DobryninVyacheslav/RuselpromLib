package ru.ruselprom.lib.fet.mirror;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcArgument.pfcArgument;
import com.ptc.pfc.pfcGeometry.Curve;
import com.ptc.pfc.pfcGeometry.Surface;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSelect.Selection;
import com.ptc.pfc.pfcSelect.pfcSelect;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.wfc.wfcElemIds.wfcElemIds;
import com.ptc.wfc.wfcElementTree.Element;
import com.ptc.wfc.wfcElementTree.ElementTree;
import com.ptc.wfc.wfcElementTree.Elements;
import com.ptc.wfc.wfcElementTree.wfcElementTree;
import com.ptc.wfc.wfcFeatureInstructions.FeatCreateOption;
import com.ptc.wfc.wfcFeatureInstructions.FeatCreateOptions;
import com.ptc.wfc.wfcSession.WSession;
import com.ptc.wfc.wfcSolid.WSolid;
import com.ptc.wfc.wfcSolidInstructions.WRegenInstructions;
import com.ptc.wfc.wfcSolidInstructions.wfcSolidInstructions;

public class MirrorCurve {
	private MirrorCurve() {
	    throw new IllegalStateException("Utility class");
	}

	public static void build(String newFeatName, String curveName, String refPlaneName, Solid currSolid) throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		Curve curve = (Curve)currSolid.GetFeatureByName(curveName).ListSubItems(ModelItemType.ITEM_CURVE).get(0);
		Selection refCurve =  pfcSelect.CreateModelItemSelection(curve, null);
		Surface plane = (Surface)currSolid.GetFeatureByName(refPlaneName).ListSubItems(ModelItemType.ITEM_SURFACE).get(0);
		Selection refPlane = pfcSelect.CreateModelItemSelection(plane, null);
		
		Elements elements = Elements.create();
		
		//PRO_E_FEATURE_TREE
		Element elem_0_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_FEATURE_TREE,null,0);
		elements.append(elem_0_0);
		
		//PRO_E_FEATURE_TYPE
		Element elem_1_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_FEATURE_TYPE,pfcArgument.CreateIntArgValue(950),1);//PRO_FEAT_SRF_MDL = 950
		elements.append(elem_1_0);
		
		//PRO_E_SRF_TRANS_TYPE
		Element elem_1_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_SRF_TRANS_TYPE,pfcArgument.CreateIntArgValue(1),1);//PRO_SURF_TRANS_TYPE_MIRROR = 1
		elements.append(elem_1_1);
		
		//PRO_E_STD_FEATURE_NAME
		Element elem_1_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_STD_FEATURE_NAME,pfcArgument.CreateStringArgValue(newFeatName),1);	//FEATURE_NAME
		elements.append(elem_1_2);
		
		//PRO_E_MIRROR_REF_ITEMS
		Element elem_1_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_MIRROR_REF_ITEMS,pfcArgument.CreateSelectionArgValue(refCurve),1);	//PRO_CURVE (PRO_VALUE_TYPE_SELECTION)
		elements.append(elem_1_3);
		
		//PRO_E_MIRROR_REF_PLANE
		Element elem_1_4 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_MIRROR_REF_PLANE,pfcArgument.CreateSelectionArgValue(refPlane),1);	//PRO_DATUM_PLANE, PRO_SURFACE
		elements.append(elem_1_4);
		
		//PRO_E_COPY_NO_COPY
		Element elem_1_5 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_COPY_NO_COPY,pfcArgument.CreateIntArgValue(0),1);	//PRO_MIRROR_KEEP_ORIGINAL = 0
		elements.append(elem_1_5);
		
		ElementTree	elemTree = ((WSession)session).CreateElementTree(elements);
		
		FeatCreateOptions featOpts = FeatCreateOptions.create();
		featOpts.append(FeatCreateOption.FEAT_CR_NO_OPTS);
		WRegenInstructions regenInstr = wfcSolidInstructions.WRegenInstructions_Create();
		
		((WSolid)currSolid).WCreateFeature(elemTree,featOpts,regenInstr);
	}
}
