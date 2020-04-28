package view;

import javax.swing.*;
import java.awt.*;

public class ShapePanel extends JPanel {

    private JRadioButton arrow;
    private JRadioButton oval;
    private JRadioButton rectangle;
    private JRadioButton addText;
    private JRadioButton crop;

    private JButton redo;
    private JButton undo;




    public ShapePanel() {

        arrow = new JRadioButton("Arrow");
        oval = new JRadioButton("Oval");
        rectangle = new JRadioButton("Rectangle");
        crop = new JRadioButton("Crop");
        redo = new JButton("Redo");
        undo = new JButton("Undo");
        addText = new JRadioButton("Text");

        ButtonGroup buttonGroup = new ButtonGroup();
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        setLayout(flowLayout);

        add(oval);
        add(arrow);
        add(rectangle);
        add(addText);
        add(crop);
        add(redo);
        add(undo);

        buttonGroup.add(oval);
        buttonGroup.add(rectangle);
        buttonGroup.add(arrow);
        buttonGroup.add(oval);
        buttonGroup.add(crop);
        buttonGroup.add(redo);
        buttonGroup.add(undo);
        buttonGroup.add(addText);

    }


    public JRadioButton getArrow() {
        return arrow;
    }

    public void setArrow(JRadioButton arrow) {
        this.arrow = arrow;
    }

    public JRadioButton getOval() {
        return oval;
    }

    public void setOval(JRadioButton oval) {
        this.oval = oval;
    }

    public JRadioButton getRectangle() {
        return rectangle;
    }

    public void setRectangle(JRadioButton rectangle) {
        this.rectangle = rectangle;
    }

    public JRadioButton getAddText() {
        return addText;
    }

    public void setAddText(JRadioButton addText) {
        this.addText = addText;
    }

    public JRadioButton getCrop() {
        return crop;
    }

    public void setCrop(JRadioButton crop) {
        this.crop = crop;
    }

    public JButton getRedo() {
        return redo;
    }

    public void setRedo(JButton redo) {
        this.redo = redo;
    }

    public JButton getUndo() {
        return undo;
    }

    public void setUndo(JButton undo) {
        this.undo = undo;
    }
}
