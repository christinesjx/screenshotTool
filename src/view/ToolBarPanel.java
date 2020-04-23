package view;

import model.Model;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;


public class ToolBarPanel extends JPanel {

    private JButton screenShotButton;
    private JButton saveButton;

    public ToolBarPanel(Model model) {
        setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        FlowLayout flowLayout = (FlowLayout) getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        screenShotButton = new JButton("ScreenShot");
        add(screenShotButton);

        saveButton = new JButton("Save");
        add(saveButton);
    }

    public JButton getScreenShotButton() {
        return screenShotButton;
    }


    public JButton getSaveButton() {
        return saveButton;
    }

}
