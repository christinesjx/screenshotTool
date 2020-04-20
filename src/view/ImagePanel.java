package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    Model model;

    public ImagePanel(Model model) {

        this.model = model;
        setPreferredSize(new Dimension(400, 300));
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

        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics gi = image.getGraphics();
        gi.setColor(Color.WHITE);
        gi.fillRect(0, 0, getWidth(), getHeight());


        if (!model.getResultQueue().isEmpty()) {
            System.out.println("!!!!!");
            BufferedImage imagePrev = model.getResultQueue().getLastResult();
            gi.drawImage(imagePrev, 0, 0, this);
        }

		if (model.getCurrentCommand() != null) {
            System.out.println("======");

            model.getCurrentCommand().execute(image);
		}

		if (model.isMouseMoveFinished()) {
		    model.getResultQueue().addResult(image);
			model.setMouseMoveFinished(false);
			model.setCurrentCommand(null);
		}

        g.drawImage(image, 0, 0, this);
    }



}
