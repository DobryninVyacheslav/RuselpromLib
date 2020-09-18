package ru.ruselprom.lib.base;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import com.ptc.pfc.pfcModel.pfcModel;
import com.ptc.pfc.pfcModelItem.ModelItemType;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcSolid.RegenInstructions;
import com.ptc.pfc.pfcSolid.Solid;
import com.ptc.pfc.pfcSolid.pfcSolid;

import ru.ruselprom.lib.exceptions.CreoSessionException;

public class CreoUtils {

	private CreoUtils() {
	    throw new IllegalStateException("Utility class");
	}
	
	public static void setDimValue(Solid solidModel, String dimName, double value) throws jxthrowable {
		Dimension dimension = (Dimension)solidModel.GetItemByName(ModelItemType.ITEM_DIMENSION, dimName);
		dimension.SetDimValue(value);
	}
	
	public static void regenerateSolid (Solid currSolid) throws jxthrowable {
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
		session.SetConfigOption("regen_failure_handling", "resolve_mode");
		RegenInstructions instrForReg = pfcSolid.RegenInstructions_Create(Boolean.TRUE, null, null);
		currSolid.Regenerate(instrForReg);
	}
	
	public static void printToCreo(String msg) {
		try {
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			session.UIShowMessageDialog(msg, null);
		} catch (jxthrowable e) {
			throw new CreoSessionException("error printing to creo", e);
		}
	}
	
	public static Model retrieveModelByFileName(String fileName) {
		try {
			Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
			ModelDescriptor descriptor = pfcModel.ModelDescriptor_CreateFromFileName(fileName);
			return session.RetrieveModel(descriptor);
		} catch(jxthrowable | NullPointerException e) {
			printToCreo("Error in getModel:" + e.toString());
			throw new NullPointerException("no model");	
		}
	}
}
