package ru.ruselprom.lib.fet.extrusions.add;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcArgument.pfcArgument;
import com.ptc.pfc.pfcFeature.Feature;
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
import com.ptc.wfc.wfcFeature.FeatureElemTreeExtractOptions;
import com.ptc.wfc.wfcFeature.WFeature;
import com.ptc.wfc.wfcFeatureInstructions.FeatCreateOption;
import com.ptc.wfc.wfcFeatureInstructions.FeatCreateOptions;
import com.ptc.wfc.wfcSession.WSession;
import com.ptc.wfc.wfcSolid.WSolid;
import com.ptc.wfc.wfcSolidInstructions.WRegenInstructions;
import com.ptc.wfc.wfcSolidInstructions.wfcSolidInstructions;

public class ExtrusionAddOneWay {

    public void build(double depth, String newFeatName, String refSecName, Solid currSolid)  throws jxthrowable {
    	Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
	    Feature section = currSolid.GetFeatureByName(refSecName);
		Selection refSection =  pfcSelect.CreateModelItemSelection(section, null);
		Elements elements = Elements.create();
		
		//PRO_E_FEATURE_TREE
		Element elem_0_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_FEATURE_TREE,null,0);
	    elements.append(elem_0_0);
	    
	    //PRO_E_STD_FEATURE_NAME
	    Element elem_1_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_STD_FEATURE_NAME, pfcArgument.CreateStringArgValue(newFeatName),1);	//Feature Name 
	    elements.append(elem_1_0); 
	    
	    //PRO_E_FEATURE_FORM
	    Element elem_1_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_FEATURE_FORM, pfcArgument.CreateIntArgValue(1),1);				//PRO_EXTRUDE = 1 (Pro_FeatFormType)
	    elements.append(elem_1_1);
	    
	    //PRO_E_EXT_SURF_CUT_SOLID_TYPE 
	    Element elem_1_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_EXT_SURF_CUT_SOLID_TYPE, pfcArgument.CreateIntArgValue(917),1);	//PRO_EXT_FEAT_TYPE_SOLID = 917
	    elements.append(elem_1_2);
	    
	    //PRO_E_REMOVE_MATERIAL
	    Element elem_1_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_REMOVE_MATERIAL, pfcArgument.CreateIntArgValue(-1),1);			//PRO_EXT_MATERIAL_ADD = -1
	    elements.append(elem_1_3);																											//PRO_EXT_MATERIAL_REMOVE = 916
	    
	    //PRO_E_FEAT_FORM_IS_THIN
	    Element elem_1_4 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_FEAT_FORM_IS_THIN, pfcArgument.CreateIntArgValue(0),1);			//PRO_EXT_FEAT_FORM_NO_THIN = 0
	    elements.append(elem_1_4);
	    
	    //PRO_E_STD_EXT_DEPTH
	    Element elem_1_6 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_STD_EXT_DEPTH, null,1);											//Compound
	    elements.append(elem_1_6);
	    
	    //PRO_E_EXT_DEPTH_FROM
	    Element elem_2_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_EXT_DEPTH_FROM, null,2);											//Compound
	    elements.append(elem_2_0);
	    
	    //PRO_E_EXT_DEPTH_FROM_TYPE
	    Element elem_3_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_EXT_DEPTH_FROM_TYPE, pfcArgument.CreateIntArgValue(1 << 12),3);	//PRO_EXT_DEPTH_FROM_NONE = (1 << 12)	
	    elements.append(elem_3_0);
	    
	    //PRO_E_EXT_DEPTH_TO
	    Element elem_2_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_EXT_DEPTH_TO, null,2);											//Compound
	    elements.append(elem_2_1);
	    
	    //PRO_E_EXT_DEPTH_TO_TYPE
	    Element elem_3_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_EXT_DEPTH_TO_TYPE, pfcArgument.CreateIntArgValue(1 << 18),3);		//PRO_EXT_DEPTH_TO_BLIND = (1 << 18)
	    elements.append(elem_3_1);
	    
	    //PRO_E_EXT_DEPTH_TO_VALUE
	    Element elem_3_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_EXT_DEPTH_TO_VALUE, pfcArgument.CreateDoubleArgValue(depth),3);		//PRO_EXT_DEPTH_TO_BLIND = (1 << 18)
	    elements.append(elem_3_2);
	    
	    //PRO_E_STD_SECTION
	    Element elem_1_7 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_STD_SECTION, null,1);												//Compound
	    elements.append(elem_1_7);
	    
	    //PRO_E_SEC_USE_SKETCH
	    Element elem_2_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_SEC_USE_SKETCH, pfcArgument.CreateSelectionArgValue(refSection),2);//PRO_VALUE_TYPE_SELECTION (PRO_FEATURE)
	    elements.append(elem_2_2);  
	    		   
	    ElementTree	elemTree = ((WSession)session).CreateElementTree(elements);
	    
	    FeatCreateOptions featOpts = FeatCreateOptions.create();
	    featOpts.append(FeatCreateOption.FEAT_CR_INCOMPLETE_FEAT);
	    WRegenInstructions regenInstr = wfcSolidInstructions.WRegenInstructions_Create();
	    WFeature extrudeFeat = ((WSolid)currSolid).WCreateFeature(elemTree,featOpts,regenInstr);
	    elemTree = extrudeFeat.GetElementTree(null,FeatureElemTreeExtractOptions.FEAT_EXTRACT_NO_OPTS);
	    
	    featOpts.insert(0, FeatCreateOption.FEAT_CR_NO_OPTS);
	    extrudeFeat.RedefineFeature(null,elemTree,featOpts,regenInstr);
	}
}