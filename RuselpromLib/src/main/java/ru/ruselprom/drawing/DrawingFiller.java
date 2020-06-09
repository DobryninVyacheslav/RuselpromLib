package ru.ruselprom.drawing;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcBase.Matrix3D;
import com.ptc.pfc.pfcBase.Point3D;
import com.ptc.pfc.pfcBase.Transform3D;
import com.ptc.pfc.pfcDrawing.Drawing;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcView2D.GeneralViewCreateInstructions;
import com.ptc.pfc.pfcView2D.pfcView2D;

import ru.ruselprom.base.OrientViews;

public class DrawingFiller {
    private Drawing drawing;
    private double x = 0;
    private double y = 0;
    private double z = 0;
    
    public DrawingFiller(Drawing drawing) {
        this.drawing = drawing;
    }

    public Drawing getDrawing() {
        return drawing;
    }

    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }
    
    public void createView (double scale, OrientViews view, Model currModel) throws jxthrowable {
    	currModel.Display();
        Transform3D transf = currModel.RetrieveView(view.toString()).GetTransform();
        Matrix3D matrix = transf.GetMatrix();
        normalizeMatrix(matrix);
        transf.SetMatrix(matrix);
        GeneralViewCreateInstructions instrs = pfcView2D.GeneralViewCreateInstructions_Create (currModel, drawing.GetCurrentSheetNumber(), viewLocation(x,y,z), transf);
        instrs.SetScale(scale);
        drawing.Display();
        drawing.SetSheetScale(drawing.GetCurrentSheetNumber(), 2.0, null);
        drawing.CreateView (instrs);
    }
    
    public void setViewLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    private Point3D viewLocation(double x, double y, double z) throws jxthrowable {
        Point3D location = Point3D.create();
        location.set(0, x);
        location.set(1, y);
        location.set(2, z);
        return location;
    }
    
    private void normalizeMatrix(Matrix3D matrix) throws jxthrowable {
        // Remove the shift
        for (int i = 0; i < 3; ++i) {
            matrix.set(3, i, 0.0);
        }
        // Get the scaling factor
        double scale = Math.sqrt(matrix.get(0, 0) * matrix.get(0, 0) + matrix.get(0, 1) * matrix.get(0, 1) + matrix.get(0, 2) * matrix.get(0, 2));
        // Remove the scaling
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                matrix.set(row, col, matrix.get(row, col) / scale);
            }
        }
    }
}