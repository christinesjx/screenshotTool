package view;

import model.Model;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

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

    public void addScreenshotListener(ActionListener listenForScreenshotButton) {

        screenShotButton.addActionListener(listenForScreenshotButton);
    }


    public void addSaveScreenshotListener(ActionListener listenForSaveScreenshotButton) {

        saveButton.addActionListener(listenForSaveScreenshotButton);
    }


}
