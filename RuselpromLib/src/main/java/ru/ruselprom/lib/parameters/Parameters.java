package ru.ruselprom.lib.parameters;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcBase.LengthUnitType;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModelItem.pfcModelItem;
import com.ptc.wfc.wfcSolid.WSolid;

public class Parameters {
	
	private Parameters() {
	    throw new IllegalStateException("Utility class");
	}

	public static void createDoubleParamInDeg(String paramName, double doubleParamValue, Model currModel) throws jxthrowable {
		if (((WSolid)currModel).UnitsUsed()) {
			currModel.CreateParamWithUnits(paramName, pfcModelItem.CreateDoubleParamValue(doubleParamValue), ((WSolid)currModel).GetUnit("deg", false));
		}
	}
	
	public static void createDoubleLengthParam(String paramName, double doubleParamValue, LengthUnitType lengthUnitType, Model currModel) throws jxthrowable {
		if (((WSolid)currModel).UnitsUsed()) {
			switch (lengthUnitType.getValue()) {
			case LengthUnitType._LENGTHUNIT_MM:
				currModel.CreateParamWithUnits(paramName, pfcModelItem.CreateDoubleParamValue(doubleParamValue), ((WSolid)currModel).GetUnit("mm", false));
				break;
			case LengthUnitType._LENGTHUNIT_CM:
				currModel.CreateParamWithUnits(paramName, pfcModelItem.CreateDoubleParamValue(doubleParamValue), ((WSolid)currModel).GetUnit("cm", false));
				break;
			case LengthUnitType._LENGTHUNIT_FOOT:
				currModel.CreateParamWithUnits(paramName, pfcModelItem.CreateDoubleParamValue(doubleParamValue), ((WSolid)currModel).GetUnit("ft", false));
				break;
			case LengthUnitType._LENGTHUNIT_INCH:
				currModel.CreateParamWithUnits(paramName, pfcModelItem.CreateDoubleParamValue(doubleParamValue), ((WSolid)currModel).GetUnit("in", false));
				break;
			case LengthUnitType._LENGTHUNIT_KM:
				currModel.CreateParamWithUnits(paramName, pfcModelItem.CreateDoubleParamValue(doubleParamValue), ((WSolid)currModel).GetUnit("km", false));
				break;
			case LengthUnitType._LENGTHUNIT_M:
				currModel.CreateParamWithUnits(paramName, pfcModelItem.CreateDoubleParamValue(doubleParamValue), ((WSolid)currModel).GetUnit("m", false));
				break;
			case LengthUnitType._LENGTHUNIT_MCM:
				currModel.CreateParamWithUnits(paramName, pfcModelItem.CreateDoubleParamValue(doubleParamValue), ((WSolid)currModel).GetUnit("mil", false));
				break;
			case LengthUnitType._LENGTHUNIT_MILE:
				currModel.CreateParamWithUnits(paramName, pfcModelItem.CreateDoubleParamValue(doubleParamValue), ((WSolid)currModel).GetUnit("mile", false));
				break;
			case LengthUnitType._LENGTHUNIT_NM:
				currModel.CreateParamWithUnits(paramName, pfcModelItem.CreateDoubleParamValue(doubleParamValue), ((WSolid)currModel).GetUnit("nm", false));
				break;
			default:
				break;
			}
		}
	}
	
	public static void createStringParam(String paramName, String stringParamValue, Model currModel) throws jxthrowable {
		currModel.CreateParam(paramName, pfcModelItem.CreateStringParamValue(stringParamValue));
	}

	public static void createDoubleParam(String paramName, double doubleParamValue, Model currModel) throws jxthrowable {
		currModel.CreateParam(paramName, pfcModelItem.CreateDoubleParamValue(doubleParamValue));
	}

	public static void createIntParam(String paramName, int intParamValue, Model currModel) throws jxthrowable {
		currModel.CreateParam(paramName, pfcModelItem.CreateIntParamValue(intParamValue));
	}

	public static void createBoolParam(String paramName, Boolean boolParamValue, Model currModel) throws jxthrowable {
		currModel.CreateParam(paramName, pfcModelItem.CreateBoolParamValue(boolParamValue));
	}

	public static void setStringParamValue(String paramName, String stringParamValue, Model currModel) throws jxthrowable {
		currModel.GetParam(paramName).SetValue(pfcModelItem.CreateStringParamValue(stringParamValue));
	}
	
	public static void setDoubleParamValue(String paramName, double doubleParamValue, Model currModel) throws jxthrowable {
		currModel.GetParam(paramName).SetValue(pfcModelItem.CreateDoubleParamValue(doubleParamValue));
	}
	
	public static void setIntParamValue(String paramName, int intParamValue, Model currModel) throws jxthrowable {
		currModel.GetParam(paramName).SetValue(pfcModelItem.CreateIntParamValue(intParamValue));
	}

	public static void setBoolParamValue(String paramName, Boolean boolParamValue, Model currModel) throws jxthrowable {
		currModel.GetParam(paramName).SetValue(pfcModelItem.CreateBoolParamValue(boolParamValue));
	}
}