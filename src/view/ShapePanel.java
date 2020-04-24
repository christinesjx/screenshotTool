package view;

import model.Model;

import javax.swing.*;
import java.awt.*;

public class ShapePanel extends JPanel {

    private JButton arrow;
    private JButton oval;
    private JButton rectangle;
    private JToggleButton addText;
    private JButton crop;

    private JButton redo;
    private JButton undo;



    public ShapePanel(Model model) {

        ButtonGroup buttonGroup = new ButtonGroup();
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        setLayout(flowLayout);


        oval = new JButton("Oval");
        arrow = new JButton("Arrow");
        rectangle = new JButton("Rectangle");
        crop = new JButton("Crop");
        redo = new JButton("Redo");
        undo = new JButton("Undo");
        addText = new JToggleButton("Text");

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
        buttonGroup.add(redo);
        buttonGroup.add(undo);
        buttonGroup.add(addText);

    }


    public JToggleButton getAddText() {
        return addText;
    }

    public void setAddText(JToggleButton addText) {
        this.addText = addText;
    }

    public JButton getArrow() {
        return arrow;
    }

    public void setArrow(JButton arrow) {
        this.arrow = arrow;
    }

    public JButton getOval() {
        return oval;
    }

    public void setOval(JButton oval) {
        this.oval = oval;
    }

    public JButton getRectangle() {
        return rectangle;
    }

    public void setRectangle(JButton rectangle) {
        this.rectangle = rectangle;
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

    public JButton getCrop() {
        return crop;
    }

    public void setCrop(JButton crop) {
        this.crop = crop;
    }
}
