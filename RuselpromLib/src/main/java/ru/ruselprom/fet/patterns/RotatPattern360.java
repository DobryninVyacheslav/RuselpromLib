package ru.ruselprom.fet.patterns;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcArgument.pfcArgument;
import com.ptc.pfc.pfcGeometry.Axis;
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

import ru.ruselprom.base.Direction;

public class RotatPattern360 extends AbstractRotatPattern {
    
    public RotatPattern360(String refAxisName) {
        super(refAxisName);
    }

    public void patternBuild(int numItems, Direction dirOfRotat, String newFeatName, String refFeatName, Solid currSolid) throws jxthrowable {
    	Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
	    Axis axis = (Axis)currSolid.GetFeatureByName(refAxisName).ListSubItems(ModelItemType.ITEM_AXIS).get(0);
	    Selection refAxis =  pfcSelect.CreateModelItemSelection(axis, null);
	    
		Elements elements = Elements.create();
		
		//PRO_E_PATTERN_ROOT
		Element elem_0_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_PATTERN_ROOT,null,0);
	    elements.append(elem_0_0);
	    
	    //PRO_E_GENPAT_TYPE
	    Element elem_1_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_TYPE,pfcArgument.CreateIntArgValue(6),1);					//PRO_GENPAT_AXIS_DRIVEN = 6
	    elements.append(elem_1_0);
	    
	    //PRO_E_GENPAT_AXIS
	    Element elem_1_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_AXIS,null,1);												//Compound
	    elements.append(elem_1_1);
	    
	    //PRO_E_GENPAT_AXIS_REF
	    Element elem_2_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_AXIS_REF,pfcArgument.CreateSelectionArgValue(refAxis),2);	//Axis Reference (PRO_VALUE_TYPE_SELECTION)
	    elements.append(elem_2_0);
	    
	    //PRO_E_GENPAT_AXIS1_INC
	    Element elem_2_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_AXIS1_INC,pfcArgument.CreateDoubleArgValue(360.0/numItems),2);		//Axis 1st Increment (PRO_VALUE_TYPE_DOUBLE)
	    elements.append(elem_2_1);																											//-360 <= value <= 360 	//
	    
	    //PRO_E_AXIS_PAT_DIR1_FLIP
	    Element elem_2_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_AXIS_PAT_DIR1_FLIP,pfcArgument.CreateIntArgValue(dirOfRotat.getValue()),2);	//Axis 1st Increment (PRO_VALUE_TYPE_DOUBLE)
	    elements.append(elem_2_2);
	    
	    //PRO_E_GENPAT_DIM_FIRST_DIR
	    Element elem_2_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_DIM_FIRST_DIR,null,2);										//1st Dir Dimensions (Array)
	    elements.append(elem_2_3);
	    
	    //PRO_E_GENPAT_DIM_FIRST_DIR_NUM_INST
	    Element elem_2_4 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_DIM_FIRST_DIR_NUM_INST,pfcArgument.CreateIntArgValue(numItems),2);//1st Dir Instances (PRO_VALUE_TYPE_INT)
	    elements.append(elem_2_4);																											//>= 2					//
	    
	    //PRO_E_GENPAT_REGEN_METHOD
	    Element elem_1_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_REGEN_METHOD,pfcArgument.CreateIntArgValue(4),1);			//PRO_PAT_GENERAL = 4
	    elements.append(elem_1_2);
	    
	    //PRO_E_STD_FEATURE_NAME
	    Element elem_1_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_STD_FEATURE_NAME,pfcArgument.CreateStringArgValue(newFeatName),1);	//Feature Name PRO_VALUE_TYPE_WSTRING
	    elements.append(elem_1_3);
	    
	    ElementTree	elemTree = ((WSession)session).CreateElementTree(elements);
	    
	    WFeature patternFeat = (WFeature)currSolid.GetFeatureByName(refFeatName);
	    patternFeat.CreatePattern(elemTree, PatternType.FEAT_PATTERN);
	}
}
