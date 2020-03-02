package ru.ruselprom.fet.info;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcFeature.Feature;
import com.ptc.pfc.pfcFeature.FeatureType;
import com.ptc.pfc.pfcFeature.Features;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcModelItem.ModelItems;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.wfc.wfcComponentFeat.WComponentFeat;

public class Info {
	
	private Session session;
	
	public Info(Session session) {
		this.session = session;
	}
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void getFeatInfoIn(Model currModel) throws jxthrowable {
		try {
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
			
		} catch (jxthrowable e) {
			session.UIShowMessageDialog("Ошибка - " + e, null);
		}
	}
	
	public void getFlexFeatInfoIn(Model currModel) throws jxthrowable {
		try {
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
		} catch (jxthrowable e) {
			session.UIShowMessageDialog("Error in info.getFlexFeatInfoin(Model)!", null);
		}
		
	}
	
	public void getCompSizeInfoIn(Model currModel) throws jxthrowable {
		Solid currSolid = (Solid)currModel;
		Features feats = currSolid.ListFeaturesByType(Boolean.TRUE, FeatureType.FEATTYPE_COMPONENT);
		session.UIShowMessageDialog(Integer.toString(feats.getarraysize()),null);
	}
	
	public void getParamsInfoIn(Model currModel) throws jxthrowable{
		StringBuilder paramNames = new StringBuilder();
		for (int i = 0;i < currModel.ListParams().getarraysize();i++) {
			paramNames.append(currModel.ListParams().get(i).GetName() + "\n");
		}
		session.UIShowMessageDialog(paramNames.toString(), null);
	}
	
	public void getDimensionsInfoIn(String nameFeat, Solid currSolid) throws jxthrowable {
		String name = "";
		double value;
		StringBuilder allname = new StringBuilder();
		ModelItems dimensions = currSolid.GetFeatureByName(nameFeat).ListSubItems(ModelItemType.ITEM_DIMENSION);
		for (int i = 0; i < dimensions.getarraysize(); i++) {
			name = dimensions.get(i).GetName();
			value = ((Dimension)dimensions.get(i)).GetDimValue();
			allname.append((i+1) + ") " + name + ": " + value + "\n");
		}
		session.UIShowMessageDialog(allname.toString(), null);
	}
	
}

