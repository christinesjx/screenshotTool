package command;

import shape.Oval;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OvalCommand implements Command {

    private Oval oval;

    public OvalCommand(Oval oval) {
        this.oval = oval;
    }

    @Override
    public void execute(BufferedImage image) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(Color.BLACK);
        g2d.drawOval(oval.getX(), oval.getY(), oval.getWidth(), oval.getHeight());
    }

    public void setPoint(int x, int y) {
        oval.setX(x);
        oval.setY(y);
    }

    public void setWidth(int width) {
        oval.setWidth(width);
    }

    public void setHeight(int height) {
        oval.setHeight(height);
    }
}
