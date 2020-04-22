package view;

import model.Model;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextPanel extends JPanel {

    private Model model;

    private JButton addText;
    private JTextArea textField;

    private JButton clear;


    public TextPanel(Model model) {
        this.model = model;

        setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        FlowLayout gridBagLayout = new FlowLayout();

        setLayout(gridBagLayout);

        textField = new JTextArea(5, 20);
        textField.setLineWrap(true);
        textField.setPreferredSize(new Dimension(100, 100));
        add(textField);

        addText = new JButton("Text");
        add(addText);

        clear = new JButton("Clear");
        add(clear);

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });
    }


    public JButton getAddText() {
        return addText;
    }

    public void setAddText(JButton addText) {
        this.addText = addText;
    }

    public JTextArea getTextField() {
        return textField;
    }

    public void setTextField(JTextArea textField) {
        this.textField = textField;
    }
}