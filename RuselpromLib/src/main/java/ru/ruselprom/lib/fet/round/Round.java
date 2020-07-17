package ru.ruselprom.lib.fet.round;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcArgument.pfcArgument;
import com.ptc.pfc.pfcGeometry.Edge;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSelect.Selection;
import com.ptc.pfc.pfcSelect.Selections;
import com.ptc.pfc.pfcSelect.pfcSelect;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.wfc.wfcCollection.Collection;
import com.ptc.wfc.wfcCollection.CurveCollection;
import com.ptc.wfc.wfcCollection.CurveCollectionInstrType;
import com.ptc.wfc.wfcCollection.CurveCollectionInstruction;
import com.ptc.wfc.wfcCollection.CurveCollectionInstructions;
import com.ptc.wfc.wfcCollection.wfcCollection;
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

public class Round {
	public void build(String newFeatName, String featName, Solid currSolid, RadiusAndEdgeIndices...radiusAndEdgeIndicesArray) throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		try {
			Elements elements = Elements.create();
			
			//PRO_E_FEATURE_TREE
			Element elem_0_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_FEATURE_TREE,null,0);
			elements.append(elem_0_0);
			
			//PRO_E_FEATURE_TYPE
			Element elem_1_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_FEATURE_TYPE,pfcArgument.CreateIntArgValue(913),1);//PRO_FEAT_ROUND = 913
			elements.append(elem_1_0);
			
			//PRO_E_STD_FEATURE_NAME
			Element elem_1_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_STD_FEATURE_NAME, pfcArgument.CreateStringArgValue(newFeatName),1);	//Feature Name 
			elements.append(elem_1_1);
			
			//PRO_E_RNDCH_SETS
			Element elem_1_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_RNDCH_SETS, null,1);	//Array
			elements.append(elem_1_2);
			
			for (RadiusAndEdgeIndices radiusAndEdgeIndices : radiusAndEdgeIndicesArray) {
				Collection collection = createCollection(featName, radiusAndEdgeIndices.getEdgeIndices(), currSolid);
				createElementsOfSet(radiusAndEdgeIndices.getRadius(), collection, elements);
			}
			
			//PRO_E_RNDCH_ATTACH_TYPE
			Element elem_1_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_RNDCH_ATTACH_TYPE, pfcArgument.CreateIntArgValue(0),1);	//PRO_ROUND_ATTACHED = 0
			elements.append(elem_1_3);
			
			ElementTree	elemTree = ((WSession)session).CreateElementTree(elements);
			
			FeatCreateOptions featOpts = FeatCreateOptions.create();
			featOpts.append(FeatCreateOption.FEAT_CR_NO_OPTS);
			WRegenInstructions regenInstr = wfcSolidInstructions.WRegenInstructions_Create();
			
			((WSolid)currSolid).WCreateFeature(elemTree,featOpts,regenInstr);
		} catch (Exception e) {
			session.UIShowMessageDialog(e.toString(), null);
		}
	}

	private Collection createCollection(String featName, int[] indexOfEdges, Solid currSolid) throws jxthrowable {
		Selections selections = Selections.create();
		for (int i : indexOfEdges) {
			Edge edge = (Edge)currSolid.GetFeatureByName(featName).ListSubItems(ModelItemType.ITEM_EDGE).get(i);
			Selection refEdge =  pfcSelect.CreateModelItemSelection(edge, null);
			selections.append(refEdge);
		}
		
		CurveCollectionInstruction instruction = wfcCollection.
				CurveCollectionInstruction_Create(CurveCollectionInstrType.CURVCOLL_ADD_ONE_INSTR, selections);
		CurveCollectionInstructions instructions = CurveCollectionInstructions.create();
		instructions.append(instruction);
		CurveCollection curveCollection = wfcCollection.CurveCollection_Create(instructions);
		
		Collection collection = wfcCollection.Collection_Create();
		collection.SetCrvCollection(curveCollection);
		return collection;
	}

	private void createElementsOfSet(double radius1, Collection collection, Elements elements) throws jxthrowable {
		//PRO_E_RNDCH_SET
		Element elem_2_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_RNDCH_SET, null,2);	//Compound
		elements.append(elem_2_0);
		
		//PRO_E_RNDCH_SHAPE_OPTIONS
		Element elem_3_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_RNDCH_SHAPE_OPTIONS, pfcArgument.CreateIntArgValue(0),3);	//PRO_ROUND_TYPE_CONSTANT = 0
		elements.append(elem_3_0);
		
		//PRO_E_RNDCH_COMPOUND_CONIC
		Element elem_3_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_RNDCH_COMPOUND_CONIC, null,3);	//Compound
		elements.append(elem_3_1);
		
		//PRO_E_RNDCH_CONIC_TYPE
		Element elem_4_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_RNDCH_CONIC_TYPE, pfcArgument.CreateIntArgValue(2),4);	//PRO_ROUND_CONIC_DISABLE = 2
		elements.append(elem_4_0);
		
		//PRO_E_RNDCH_REFERENCES
		Element elem_3_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_RNDCH_REFERENCES, null,3);	//Compound
		elements.append(elem_3_2);
		
		//PRO_E_RNDCH_REFERENCE_TYPE
		Element elem_4_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_RNDCH_REFERENCE_TYPE, pfcArgument.CreateIntArgValue(1),4); //PRO_ROUND_REF_EDGE = 1
		elements.append(elem_4_1);
		
		//PRO_E_STD_CURVE_COLLECTION_APPL
		Element elem_4_2 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_STD_CURVE_COLLECTION_APPL, null,4);	//Compound
		elem_4_2.SetElemCollection(collection);
		elements.append(elem_4_2);
		
		//PRO_E_RNDCH_RADII
		Element elem_3_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_RNDCH_RADII, null,3);	//Array
		elements.append(elem_3_3);
		
		//PRO_E_RNDCH_RADIUS
		Element elem_4_3 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_RNDCH_RADIUS, null,4);	//Compound
		elements.append(elem_4_3);
		
		//PRO_E_RNDCH_LEG1
		Element elem_5_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_RNDCH_LEG1, null,5);	//Compound
		elements.append(elem_5_0);
		
		//PRO_E_RNDCH_LEG_TYPE
		Element elem_6_0 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_RNDCH_LEG_TYPE, pfcArgument.CreateIntArgValue(1),6);	//PRO_ROUND_RADIUS_TYPE_VALUE = 1
		elements.append(elem_6_0);
		
		//PRO_E_RNDCH_LEG_VALUE
		Element elem_6_1 = wfcElementTree.Element_Create(wfcElemIds.PRO_E_RNDCH_LEG_VALUE, pfcArgument.CreateDoubleArgValue(radius1),6);	//DOUBLE
		elements.append(elem_6_1);
	}
}
