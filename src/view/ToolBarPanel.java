package view;

import model.Model;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;


public class ToolBarPanel extends JPanel {

    private JButton fullScreenButton;
    private JButton saveButton;
    private JButton screenshotButton;

    public ToolBarPanel() {
        setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        FlowLayout flowLayout = (FlowLayout) getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        screenshotButton = new JButton("Screenshot");
        add(screenshotButton);


        fullScreenButton = new JButton("Full Screenshot");
        add(fullScreenButton);

        saveButton = new JButton("Save");
        add(saveButton);
        saveButton.setVisible(false);

    }

    public JButton getFullScreenButton() {
        return fullScreenButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getScreenshotButton() {
        return screenshotButton;
    }

}
