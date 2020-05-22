package ru.ruselprom.fet.patterns;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcArgument.pfcArgument;
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

public class RefPattern {
	public void patternBuild(String newFeatName, String refFeatName, Solid currSolid) throws jxthrowable {
		Elements elements = Elements.create();
		
		//PRO_E_PATTERN_ROOT
		Element elem_0_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_PATTERN_ROOT,null,0);
	    elements.append(elem_0_0);
	    
	    //PRO_E_GENPAT_TYPE
	    Element elem_1_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_TYPE,pfcArgument.CreateIntArgValue(3),1);					// PRO_GENPAT_REF_DRIVEN = 3
	    elements.append(elem_1_0);
	    
	    //PRO_E_GENPAT_REGEN_METHOD
	    Element elem_1_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_REGEN_METHOD,pfcArgument.CreateIntArgValue(4),1);			//PRO_PAT_GENERAL = 4
	    elements.append(elem_1_1);
	    
	    //PRO_E_STD_FEATURE_NAME
	    Element elem_1_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_STD_FEATURE_NAME,pfcArgument.CreateStringArgValue(newFeatName),1);//Feature Name PRO_VALUE_TYPE_WSTRING
	    elements.append(elem_1_2);

	    //PRO_E_GENPAT_REF
	    Element elem_1_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_REF,null,1);												//Compound
	    elements.append(elem_1_3);

	    //PRO_E_GENPAT_REF_TYPE
	    Element elem_2_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_GENPAT_REF_TYPE,null,2);
	    elements.append(elem_2_0);
	    
	    Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
	    ElementTree	elemTree = ((WSession)session).CreateElementTree(elements);
	    WFeature patternFeat = (WFeature)currSolid.GetFeatureByName(refFeatName);
	    patternFeat.CreatePattern(elemTree, PatternType.FEAT_PATTERN);
	}
}
