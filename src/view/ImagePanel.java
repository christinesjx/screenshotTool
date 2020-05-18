package view;


import command.memento.ScreenshotType;
import model.Model;

import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;

public class ImagePanel extends JLabel {

    Model model;


    public ImagePanel(Model model) {
        this.model = model;

        setPreferredSize(new Dimension(600, 800));
        setBackground(Color.WHITE);
        setLayout(null);

    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }



    public void paint(Graphics g) {
        super.paint(g);
        Graphics gi;
        BufferedImage image;

        image = new BufferedImage(model.getOriginator().getImage().getWidth(), model.getOriginator().getImage().getHeight(), BufferedImage.TYPE_INT_RGB);
        gi = image.getGraphics();


        if (model.getOriginator().getImage() != null) {
            System.out.println("model.getMemento().getImage() != null");

            if(model.getScreenshotType() == ScreenshotType.FullScreenshot){
                BufferedImage lastImage = model.getOriginator().getImage();
                gi.drawImage(lastImage, 0, 0, this);

            }else{
                BufferedImage lastImage = model.getOriginator().getImage();
                gi.drawImage(lastImage, 0, 0, this);

            }

        }

        if (model.getCurrentCommand() != null) {
            System.out.println("model.getCurrentCommand()");
            model.getCurrentCommand().execute(image);
        }

        if (model.isMouseMoveFinished()) {
            System.out.println("model.isMouseMoveFinished()");
            model.getOriginator().setAndStoreState(image);
            model.setMouseMoveFinished(false);
            model.setCurrentCommand(null);
        }


        if(model.getScreenshotType() == ScreenshotType.FullScreenshot){
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);

        }else{
            g.drawImage(image, 0, 0,  null);
        }


//        g.drawImage(image, 0, 0,  null);



    }


}
