package ru.ruselprom.drawing.create;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDrawing.Drawing;
import com.ptc.pfc.pfcDrawing.DrawingCreateOption;
import com.ptc.pfc.pfcDrawing.DrawingCreateOptions;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;

import ru.ruselprom.base.CreoModel;

public class DrawingCreator extends CreoModel {

	public DrawingCreator(Model currModel) {
		super(currModel);
	}
	
	public Drawing createDrawing (String drwName, String drwTemp) throws jxthrowable {
		Drawing drawing = null;
		Session session = pfcSession.GetCurrentSessionWithCompatibility(CreoCompatibility.C4Compatible);
        DrawingCreateOptions options = DrawingCreateOptions.create();
        options.insert (0, DrawingCreateOption.DRAWINGCREATE_SHOW_ERROR_DIALOG);
        if (drwTemp.length() > 32) {
        	 session.UIShowMessageDialog("Название пути превыет 32 символа!!", null);
        }
        drawing = session.CreateDrawingFromTemplate  (drwName, drwTemp, currModel.GetDescr(), options);
        return drawing;
	}
}
