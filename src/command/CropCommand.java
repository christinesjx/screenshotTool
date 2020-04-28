package command;

import shape.Rectangle;

import java.awt.*;
import java.awt.image.BufferedImage;


public class CropCommand implements Command {


    private Rectangle rectangle;
    private BufferedImage subImage;

    public CropCommand(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void execute(BufferedImage image) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();

        System.out.println(rectangle.getX() + " " + rectangle.getY() + " " +rectangle.getWidth() + " " +rectangle.getHeight());
        System.out.println(image.getWidth() + " " + image.getHeight());

        if(rectangle.getWidth() > 0 && rectangle.getHeight() >0) {
            if (rectangle.getWidth() < image.getWidth() && rectangle.getWidth() < image.getHeight()) {
                g2d.setColor(Color.RED);

                g2d.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
                this.subImage = image.getSubimage(rectangle.getX()+1, rectangle.getY()+1, rectangle.getWidth()-1, rectangle.getHeight()-1);

            }
        }
    }


    public void setPoint(int x, int y) {
        rectangle.setX(x);
        rectangle.setY(y);
    }

    public void setWidth(int width) {
        rectangle.setWidth(width);
    }

    public void setHeight(int height) {
        rectangle.setHeight(height);
    }

    public BufferedImage getImage() {
        return subImage;
    }
}
