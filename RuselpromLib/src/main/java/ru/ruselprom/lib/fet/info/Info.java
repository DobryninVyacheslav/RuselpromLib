package ru.ruselprom.lib.fet.info;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcFeature.Feature;
import com.ptc.pfc.pfcFeature.FeatureType;
import com.ptc.pfc.pfcFeature.Features;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcModelItem.ModelItems;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.wfc.wfcComponentFeat.WComponentFeat;

public class Info {

	private Info() {
	    throw new IllegalStateException("Utility class");
	  }

    public static void getFeatInfoIn(Model currModel) throws jxthrowable {
    	Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		ModelItems items = currModel.ListItems(ModelItemType.ITEM_FEATURE);
		int quan;
		String name;
		StringBuilder allname = new StringBuilder();
					
		for (int j = 0; j < items.getarraysize(); j++) {
			name = ((Feature)items.get(j)).GetName();
			quan = ((Feature)items.get(j)).ListSubItems(ModelItemType.ITEM_FEATURE).getarraysize();
			allname.append(j + ") " + name + ": " + quan + "\n");
		}
					
		session.UIShowMessageDialog(allname.toString(), null);
	}
	
	public static void getFlexFeatInfoIn(Model currModel) throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		ModelItems items = currModel.ListItems(ModelItemType.ITEM_FEATURE);
		StringBuilder allname = new StringBuilder();
		for (int i = 0;i < items.getarraysize(); i++) {
			try {
				allname.append(i + ": " + Boolean.toString(((WComponentFeat)items.get(i)).IsFlexible()) + "\n");
			} catch (Exception e) {
				allname.append(i + ": " + "false" + "\n");
			}
		}
		session.UIShowMessageDialog(allname.toString(), null);
	}
	
	public static void getCompSizeInfoIn(Model currModel) throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		Solid currSolid = (Solid)currModel;
		Features feats = currSolid.ListFeaturesByType(Boolean.TRUE, FeatureType.FEATTYPE_COMPONENT);
		session.UIShowMessageDialog(Integer.toString(feats.getarraysize()),null);
	}
	
	public static void getParamsInfoIn(Model currModel) throws jxthrowable{
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		StringBuilder paramNames = new StringBuilder();
		for (int i = 0;i < currModel.ListParams().getarraysize();i++) {
			paramNames.append(currModel.ListParams().get(i).GetName() + "\n");
		}
		session.UIShowMessageDialog(paramNames.toString(), null);
	}
	
	public static void getDimensionsInfoIn(String nameFeat, Solid currSolid) throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		String name = "";
		double value;
		StringBuilder allname = new StringBuilder();
		ModelItems dimensions = currSolid.GetFeatureByName(nameFeat).ListSubItems(ModelItemType.ITEM_DIMENSION);
		for (int i = 0; i < dimensions.getarraysize(); i++) {
			name = dimensions.get(i).GetName();
			value = ((Dimension)dimensions.get(i)).GetDimValue();
			allname.append(i + ") " + name + ": " + value + "\n");
		}
		session.UIShowMessageDialog(allname.toString(), null);
	}
}

