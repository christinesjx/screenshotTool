package view;

import model.Model;

import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    Model model;

    public ImagePanel(Model model) {
        this.model = model;

        setPreferredSize(new Dimension(0, 0));
        setBackground(Color.WHITE);
        setLayout(null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }


    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage bfImage = model.getMemento().getImage();
        BufferedImage image = new BufferedImage(bfImage.getWidth(), bfImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics gi = image.getGraphics();

        gi.setColor(Color.WHITE);
        gi.fillRect(0, 0, getWidth(), getHeight());


        if (model.getMemento().getImage() != null) {
            BufferedImage lastImage = model.getMemento().getImage();
            gi.drawImage(lastImage, 0, 0, this);
        }

        if (model.getCurrentCommand() != null) {
            model.getCurrentCommand().execute(image);
        }

        if (model.isMouseMoveFinished()) {
            model.getMemento().setAndStoreState(image);
            model.setMouseMoveFinished(false);
            model.setCurrentCommand(null);
        }

        g.drawImage(image, 0, 0, this);
    }


}
