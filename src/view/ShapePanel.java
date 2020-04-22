package view;

import model.Model;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ShapePanel extends JPanel {

    private Model model;
    private JButton arrow;
    private JButton oval;
    private JButton rectangle;

    private JButton crop;

    private JButton redo;
    private JButton undo;


    public ShapePanel(Model model) {
        this.model = model;

        ButtonGroup buttonGroup = new ButtonGroup();

        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);


        oval = new JButton("Oval");
        GridBagConstraints gbc_oval = new GridBagConstraints();
        gbc_oval.gridx = 0;
        gbc_oval.gridy = 1;
        add(oval, gbc_oval);
        buttonGroup.add(oval);

        arrow = new JButton("Arrow");
        GridBagConstraints gbc_arrow = new GridBagConstraints();
        gbc_arrow.gridx = 0;
        gbc_arrow.gridy = 2;
        add(arrow, gbc_arrow);

        rectangle = new JButton("Rectangle");
        GridBagConstraints gbc_rectangle = new GridBagConstraints();
        gbc_rectangle.gridx = 0;
        gbc_rectangle.gridy = 3;
        add(rectangle, gbc_rectangle);

        crop = new JButton("Crop");
        GridBagConstraints gbc_crop = new GridBagConstraints();
        gbc_crop.gridx = 0;
        gbc_crop.gridy = 4;
        add(crop, gbc_crop);


        redo = new JButton("Redo");
        GridBagConstraints gbc_redo = new GridBagConstraints();
        gbc_redo.gridx = 0;
        gbc_redo.gridy = 7;
        add(redo, gbc_redo);

        undo = new JButton("Undo");
        GridBagConstraints gbc_undo = new GridBagConstraints();
        gbc_undo.gridx = 0;
        gbc_undo.gridy = 8;
        add(undo, gbc_undo);

        buttonGroup.add(rectangle);
        buttonGroup.add(arrow);
        buttonGroup.add(oval);
    }


    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
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
