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
        setVisible(false);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }



    public void paint(Graphics g) {
        super.paint(g);

        BufferedImage image = new BufferedImage(model.getResultQueue().getLastResult().getWidth(), model.getResultQueue().getLastResult().getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics gi = image.getGraphics();

        gi.setColor(Color.WHITE);
        gi.fillRect(0, 0, getWidth(), getHeight());



        if (!model.getResultQueue().isEmpty()) {

            BufferedImage imagePrev = model.getResultQueue().getLastResult();
            System.out.println("+" + imagePrev.getWidth());
            System.out.println("+" + imagePrev.getHeight());


            gi.drawImage(imagePrev, 0, 0, this);
        }

		if (model.getCurrentCommand() != null) {
            model.getCurrentCommand().execute(image);
        }

		if (model.isMouseMoveFinished()) {
		    model.getResultQueue().addResult(image);
            System.out.println("-" + image.getWidth());
            System.out.println("-" + image.getHeight());
			model.setMouseMoveFinished(false);
			model.setCurrentCommand(null);
        }

        g.drawImage(image, 0, 0, this);
    }


}
