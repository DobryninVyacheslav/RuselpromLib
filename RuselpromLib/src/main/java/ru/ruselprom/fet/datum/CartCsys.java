package ru.ruselprom.fet.datum;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcArgument.pfcArgument;
import com.ptc.pfc.pfcFeature.Feature;
import com.ptc.pfc.pfcGeometry.CoordSystem;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcModelItem.ModelItems;
import com.ptc.pfc.pfcSelect.Selection;
import com.ptc.pfc.pfcSelect.pfcSelect;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
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

import ru.ruselprom.base.CreoModel;


public class CartCsys extends CreoModel {
	
	public CartCsys(Model currModel) {
        super(currModel);
    }

    public void csysBuild(double offsetX, double offsetY, double offsetZ, String csysName) throws jxthrowable {
    	Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
	    WSolid currSolid = (WSolid)currModel;
	    
	    ModelItems items = currSolid.ListItems(ModelItemType.ITEM_FEATURE);
	    
	    CoordSystem csys = (CoordSystem)((Feature)items.get(1)).ListSubItems(ModelItemType.ITEM_COORD_SYS).get(0);
	    Selection refCsys =  pfcSelect.CreateModelItemSelection(csys, null);
	    
		Elements elements = Elements.create();
		
		//PRO_E_FEATURE_TREE
		Element elem_0_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_FEATURE_TREE,null,0);												//
	    elements.append(elem_0_0);
	    
	    //PRO_E_FEATURE_TYPE
	    Element elem_1_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_FEATURE_TYPE, pfcArgument.CreateIntArgValue(979),1);				//Feature Type (PRO_FEAT_CSYS 979)
	    elements.append(elem_1_0);
	    
	    //PRO_E_STD_FEATURE_NAME
	    Element elem_1_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_STD_FEATURE_NAME, pfcArgument.CreateStringArgValue(csysName),1);	//Feature Name 
	    elements.append(elem_1_1);
	    
	    //PRO_E_CSYS_ORIGIN_CONSTRS
	    Element elem_1_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIGIN_CONSTRS, null,1);										//Origin Constraints (Array) 
	    elements.append(elem_1_2);
	    
	    //PRO_E_CSYS_ORIGIN_CONSTR
	    Element elem_2_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIGIN_CONSTR, null,2);										//Compound
	    elements.append(elem_2_0);
	    
	    //PRO_E_CSYS_ORIGIN_CONSTR_REF
	    Element elem_3_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIGIN_CONSTR_REF, pfcArgument.CreateSelectionArgValue(refCsys),3);	//Origin Reference (PRO_VALUE_TYPE_SELECTION)
	    elements.append(elem_3_0);
	    
	    //PRO_E_CSYS_OFFSET_TYPE
	    Element elem_1_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_OFFSET_TYPE, pfcArgument.CreateIntArgValue(0),1);			//Origin Offset Type (PRO_CSYS_OFFSET_CARTESIAN)
	    elements.append(elem_1_3);
	    
	    //PRO_E_CSYS_ORIENTMOVES
	    Element elem_1_4 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVES, null,1);										//Orientation Moves (Array)
	    elements.append(elem_1_4);
		
	    //PRO_E_CSYS_ORIENTMOVE
	    Element elem_2_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE, null,2);											//Compound
	    elements.append(elem_2_1);
	    
	    //PRO_E_CSYS_ORIENTMOVE_MOVE_TYPE
	    Element elem_3_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE_MOVE_TYPE, pfcArgument.CreateIntArgValue(3),3);	//Move Type (PRO_CSYS_ORIENTMOVE_MOVE_OPT_ROT_X)
	    elements.append(elem_3_1);
	    
	    //PRO_E_CSYS_ORIENTMOVE_MOVE_VAL
	    Element elem_3_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE_MOVE_VAL, pfcArgument.CreateDoubleArgValue(0),3);	//Move Value (PRO_VALUE_TYPE_DOUBLE)
	    elements.append(elem_3_2);
	    
	    //PRO_E_CSYS_ORIENTMOVE
	    Element elem_2_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE, null,2);											//Compound
	    elements.append(elem_2_2);
	    
	    //PRO_E_CSYS_ORIENTMOVE_MOVE_TYPE
	    Element elem_3_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE_MOVE_TYPE, pfcArgument.CreateIntArgValue(4),3);	//Move Type (PRO_CSYS_ORIENTMOVE_MOVE_OPT_ROT_Y)
	    elements.append(elem_3_3);
	    
	    //PRO_E_CSYS_ORIENTMOVE_MOVE_VAL
	    Element elem_3_4 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE_MOVE_VAL, pfcArgument.CreateDoubleArgValue(0),3);	//Move Value (PRO_VALUE_TYPE_DOUBLE)
	    elements.append(elem_3_4);
	    
	    //PRO_E_CSYS_ORIENTMOVE
	    Element elem_2_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE, null,2);											//Compound
	    elements.append(elem_2_3);
	    
	    //PRO_E_CSYS_ORIENTMOVE_MOVE_TYPE
	    Element elem_3_5 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE_MOVE_TYPE, pfcArgument.CreateIntArgValue(5),3);	//Move Type (PRO_CSYS_ORIENTMOVE_MOVE_OPT_ROT_Z)
	    elements.append(elem_3_5);
	    
	    //PRO_E_CSYS_ORIENTMOVE_MOVE_VAL
	    Element elem_3_6 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE_MOVE_VAL, pfcArgument.CreateDoubleArgValue(0),3);	//Move Value (PRO_VALUE_TYPE_DOUBLE)
	    elements.append(elem_3_6);
	    
	    //PRO_E_CSYS_ORIENTMOVE
	    Element elem_2_4 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE, null,2);											//Compound
	    elements.append(elem_2_4);
	    
	    //PRO_E_CSYS_ORIENTMOVE_MOVE_TYPE
	    Element elem_3_7 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE_MOVE_TYPE, pfcArgument.CreateIntArgValue(0),3);	//Move Type (PRO_CSYS_ORIENTMOVE_MOVE_OPT_TRAN_X)
	    elements.append(elem_3_7);
	    
	    //PRO_E_CSYS_ORIENTMOVE_MOVE_VAL
	    Element elem_3_8 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE_MOVE_VAL, pfcArgument.CreateDoubleArgValue(offsetX),3);	//Move Value (PRO_VALUE_TYPE_DOUBLE)
	    elements.append(elem_3_8);
	    
	    //PRO_E_CSYS_ORIENTMOVE
	    Element elem_2_5 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE, null,2);											//Compound
	    elements.append(elem_2_5);
	    
	    //PRO_E_CSYS_ORIENTMOVE_MOVE_TYPE
	    Element elem_3_9 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE_MOVE_TYPE, pfcArgument.CreateIntArgValue(1),3);	//Move Type (PRO_CSYS_ORIENTMOVE_MOVE_OPT_TRAN_Y)
	    elements.append(elem_3_9);
	    
	    //PRO_E_CSYS_ORIENTMOVE_MOVE_VAL
	    Element elem_3_10 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE_MOVE_VAL, pfcArgument.CreateDoubleArgValue(offsetY),3);	//Move Value (PRO_VALUE_TYPE_DOUBLE)
	    elements.append(elem_3_10);
	    
	    //PRO_E_CSYS_ORIENTMOVE
	    Element elem_2_6 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE, null,2);											//Compound
	    elements.append(elem_2_6);
	    
	    //PRO_E_CSYS_ORIENTMOVE_MOVE_TYPE
	    Element elem_3_11 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE_MOVE_TYPE, pfcArgument.CreateIntArgValue(2),3);	//Move Type (PRO_CSYS_ORIENTMOVE_MOVE_OPT_TRAN_Z)
	    elements.append(elem_3_11);
	    
	    //PRO_E_CSYS_ORIENTMOVE_MOVE_VAL
	    Element elem_3_12 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENTMOVE_MOVE_VAL, pfcArgument.CreateDoubleArgValue(offsetZ),3);//Move Value (PRO_VALUE_TYPE_DOUBLE)
	    elements.append(elem_3_12);
	    
	    //PRO_E_CSYS_ORIENT_BY_METHOD
	    Element elem_1_5 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CSYS_ORIENT_BY_METHOD, pfcArgument.CreateIntArgValue(1),1);		//Orientation Moves (Array)
	    elements.append(elem_1_5);
	    
	    WSession wSession = (WSession)session;
	    ElementTree	elemTree = wSession.CreateElementTree(elements);
	    
	    FeatCreateOptions featOpts = FeatCreateOptions.create();
	    featOpts.append(FeatCreateOption.FEAT_CR_NO_OPTS);
	    WRegenInstructions regenInstr = wfcSolidInstructions.WRegenInstructions_Create();
	    
	    currSolid.WCreateFeature(elemTree,featOpts,regenInstr);
	}
}
