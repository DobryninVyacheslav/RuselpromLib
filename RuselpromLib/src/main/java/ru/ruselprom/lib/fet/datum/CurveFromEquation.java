package ru.ruselprom.lib.fet.datum;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcArgument.pfcArgument;
import com.ptc.pfc.pfcGeometry.CoordSystem;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModelItem.ModelItemType;
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

import ru.ruselprom.lib.base.CreoModel;

public class CurveFromEquation extends CreoModel {
	
	public CurveFromEquation(Model currModel) {
		super(currModel);
	}

	public void build(String newCurveName, String originCsysName, CsysTypes csysType, double from, double to, String equation) throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		WSolid currSolid = (WSolid)currModel;
		
		CoordSystem csys = (CoordSystem)currSolid.GetFeatureByName(originCsysName).ListSubItems(ModelItemType.ITEM_COORD_SYS).get(0);
		Selection refCsys =  pfcSelect.CreateModelItemSelection(csys, null);
		
		Elements elements = Elements.create();
		
		//PRO_E_FEATURE_TREE
		Element elem_0_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_FEATURE_TREE,null,0);
		elements.append(elem_0_0);
		
		//PRO_E_FEATURE_TYPE
		Element elem_1_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_FEATURE_TYPE, pfcArgument.CreateIntArgValue(949),1);				//Feature Type (PRO_FEAT_CURVE 949)
		elements.append(elem_1_0);
		
		//PRO_E_CURVE_TYPE
		Element elem_1_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CURVE_TYPE, pfcArgument.CreateIntArgValue(27),1);					//PRO_CURVE_TYPE_FROM_EQUATION = 27
		elements.append(elem_1_1);
		
		//PRO_E_STD_FEATURE_NAME
		Element elem_1_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_STD_FEATURE_NAME, pfcArgument.CreateStringArgValue(newCurveName),1); //Feature Name 
		elements.append(elem_1_2);
		
		//PRO_E_CRV_FR_EQ_REF_CSYS
		Element elem_1_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CRV_FR_EQ_REF_CSYS, pfcArgument.CreateSelectionArgValue(refCsys),1); //PRO_VALUE_TYPE_SELECTION (PRO_CSYS)
		elements.append(elem_1_3);
		
		//PRO_E_CRV_FR_EQ_CSYS_TYPE
		Element elem_1_4 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CRV_FR_EQ_CSYS_TYPE, pfcArgument.CreateIntArgValue(csysType.getType()),1);			//PRO_CRV_FR_EQ_CSYS_TYPE_CARTESIAN = 0
		elements.append(elem_1_4);
		
		//PRO_E_CRV_FR_EQ_PARAM_MIN
		Element elem_1_5 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CRV_FR_EQ_PARAM_MIN, pfcArgument.CreateDoubleArgValue(from),1);		//PRO_VALUE_TYPE_DOUBLE
		elements.append(elem_1_5);
		
		//PRO_E_CRV_FR_EQ_PARAM_MAX
		Element elem_1_6 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CRV_FR_EQ_PARAM_MAX, pfcArgument.CreateDoubleArgValue(to),1);		//PRO_VALUE_TYPE_DOUBLE
		elements.append(elem_1_6);
		
		//PRO_E_CRV_ENTER_EQUATION
		Element elem_1_7 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_CRV_ENTER_EQUATION, pfcArgument.CreateStringArgValue(equation),1);		//PRO_VALUE_TYPE_WSTRING
		elements.append(elem_1_7);
		
		WSession wSession = (WSession)session;
		ElementTree	elemTree = wSession.CreateElementTree(elements);
		
		FeatCreateOptions featOpts = FeatCreateOptions.create();
		featOpts.append(FeatCreateOption.FEAT_CR_NO_OPTS);
		WRegenInstructions regenInstr = wfcSolidInstructions.WRegenInstructions_Create();
		
		currSolid.WCreateFeature(elemTree,featOpts,regenInstr);
	}
	
	public enum CsysTypes {
		CARTESIAN(0),
		CYLINDRICAL(1),
		SPHERICAL(2);
		private int type;

		private CsysTypes(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}
	}
}
