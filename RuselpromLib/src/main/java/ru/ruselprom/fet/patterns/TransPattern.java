package ru.ruselprom.fet.patterns;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcArgument.pfcArgument;
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
import com.ptc.wfc.wfcFeature.PatternType;
import com.ptc.wfc.wfcFeature.WFeature;
import com.ptc.wfc.wfcSession.WSession;

public class TransPattern {
    
    private String refPlaneName;

    public TransPattern(String refPlaneName) {
        this.refPlaneName = refPlaneName;
    }

    public String getRefPlaneName() {
        return refPlaneName;
    }

    public void setRefPlaneName(String refPlaneName) {
        this.refPlaneName = refPlaneName;
    }

    public void patternBuild(int numItems, double offset, String newFeatName, String refFeatName, Solid currSolid) throws jxthrowable {
    	Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
    	Surface plane = (Surface)currSolid.GetFeatureByName(refPlaneName).ListSubItems(ModelItemType.ITEM_SURFACE).get(0);
	    Selection refPlane =  pfcSelect.CreateModelItemSelection(plane, null);
		Elements elements = Elements.create();
		
		//PRO_E_PATTERN_ROOT
		Element elem_0_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_PATTERN_ROOT,null,0);													//
	    elements.append(elem_0_0);
	    
	    //PRO_E_GENPAT_TYPE
	    Element elem_1_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_TYPE,pfcArgument.CreateIntArgValue(5),1);						//PRO_GENPAT_DIR_DRIVEN = 5
	    elements.append(elem_1_0);
	    
	    //PRO_E_GENPAT_REGEN_METHOD
	    Element elem_1_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_REGEN_METHOD,pfcArgument.CreateIntArgValue(4),1);				//PRO_PAT_GENERAL = 4
	    elements.append(elem_1_1);
	    
	    //PRO_E_STD_FEATURE_NAME
	    Element elem_1_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_STD_FEATURE_NAME,pfcArgument.CreateStringArgValue(newFeatName),1);		//Feature Name PRO_VALUE_TYPE_WSTRING
	    elements.append(elem_1_2);
	    
	    //PRO_E_GENPAT_DIR
	    Element elem_1_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_DIR,null,1);													//Compound
	    elements.append(elem_1_3);
	    
	    //PRO_E_GENPAT_DIR1
	    Element elem_2_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_DIR1,null,2);													//1st Direction Compound
	    elements.append(elem_2_0);
	    
	    //PRO_E_DIRECTION_COMPOUND
	    Element elem_3_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_DIRECTION_COMPOUND,null,3);											//Direction Compound
	    elements.append(elem_3_0);
	    
	    //PRO_E_DIRECTION_REFERENCE
	    Element elem_4_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_DIRECTION_REFERENCE,pfcArgument.CreateSelectionArgValue(refPlane),4);	//Direction Ref (PRO_VALUE_TYPE_SELECTION)
	    elements.append(elem_4_0);
	    
	    //PRO_E_DIRECTION_FLIP
	    Element elem_4_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_DIRECTION_FLIP,pfcArgument.CreateIntArgValue(0),4);					//Direction Flip PRO_VALUE_TYPE_INT Value Ignored
	    elements.append(elem_4_1);
	    
	    //PRO_E_GENPAT_DIR1_INC
	    Element elem_2_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_DIR1_INC,pfcArgument.CreateDoubleArgValue(offset),2);			//1st dir inc PRO_VALUE_TYPE_DOUBLE
	    elements.append(elem_2_1);
	    
	    //PRO_E_DIR_PAT_DIR1_FLIP
	    Element elem_2_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_DIR_PAT_DIR1_FLIP,pfcArgument.CreateIntArgValue(1),2);				//1st dir flip PRO_VALUE_TYPE_INT 0 or 1
	    elements.append(elem_2_2);
	    
	    //PRO_E_DIR_PAT_DIR1_OPT
	    Element elem_2_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_DIR_PAT_DIR1_OPT,pfcArgument.CreateIntArgValue(-1),2);				//PRO_GENPAT_TRANSLATIONAL = -1
	    elements.append(elem_2_3);
	    
	    //PRO_E_GENPAT_DIM_FIRST_DIR
	    Element elem_2_4 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_DIM_FIRST_DIR,null,2);											//1st Dir Dimensions Array
	    elements.append(elem_2_4);
	    
	    //PRO_E_GENPAT_DIM_FIRST_DIR_NUM_INST
	    Element elem_2_5 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_DIM_FIRST_DIR_NUM_INST,pfcArgument.CreateIntArgValue(numItems),2);	//1st Dir Instances PRO_VALUE_TYPE_INT >= 2
	    elements.append(elem_2_5);
	    
	    ElementTree	elemTree = ((WSession)session).CreateElementTree(elements);
	    WFeature patternFeat = (WFeature)currSolid.GetFeatureByName(refFeatName);
	    patternFeat.CreatePattern(elemTree, PatternType.FEAT_PATTERN);
	}
}