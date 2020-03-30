package ru.ruselprom.drawing.create;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDrawing.Drawing;
import com.ptc.pfc.pfcDrawing.DrawingCreateOption;
import com.ptc.pfc.pfcDrawing.DrawingCreateOptions;
import com.ptc.pfc.pfcExceptions.XStringTooLong;
import com.ptc.pfc.pfcExceptions.XToolkitDrawingCreateErrors;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcSession.Session;

import ru.ruselprom.base.CreoModel;

public class DrawingCreator extends CreoModel {

	public DrawingCreator(Model currModel, Session session) {
		super(currModel, session);
	}
	
	public Drawing createDrawing (String drwName, String drwTemp) throws jxthrowable {
		Drawing drawing = null;
        try {
            DrawingCreateOptions options = DrawingCreateOptions.create();
            options.insert (0, DrawingCreateOption.DRAWINGCREATE_SHOW_ERROR_DIALOG);
            drawing = session.CreateDrawingFromTemplate  (drwName, drwTemp, currModel.GetDescr(), options);
        } catch (XStringTooLong e) {
            session.UIShowMessageDialog("Название пути превыет 32 символа!!", null);
        } catch (XToolkitDrawingCreateErrors e) {
            session.UIShowMessageDialog(e.getMessage(), null);
        }
        return drawing;
	}
}
