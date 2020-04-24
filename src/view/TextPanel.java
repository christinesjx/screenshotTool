package view;

import model.Model;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextPanel extends JPanel {


    private JButton addText;
    private JTextArea textField;

    private JButton clear;
    private JButton close;


    public TextPanel(Model model) {

        setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        FlowLayout flowLayout = new FlowLayout();

        setLayout(flowLayout);

        textField = new JTextArea(5, 20);
        textField.setLineWrap(true);
        textField.setPreferredSize(new Dimension(100, 100));
        add(textField);

        addText = new JButton("Add Text");
        add(addText);

        clear = new JButton("Clear");
        add(clear);


        close = new JButton("Close");
        add(close);

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        setVisible(false);
    }


    public JButton getAddText() {
        return addText;
    }

    public JTextArea getTextField() {
        return textField;
    }

}