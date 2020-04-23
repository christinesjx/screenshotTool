package command;

import shape.Arrow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ArrowCommand implements Command {

    Arrow arrow;

    public ArrowCommand(Arrow arrow) {
        this.arrow = arrow;
    }

    @Override
    public void execute(BufferedImage image) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(Color.BLUE);

        int dx = arrow.getX2() - arrow.getX1(), dy = arrow.getY2() - arrow.getY1();
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - arrow.getD(), xn = xm, ym = arrow.getH(), yn = -arrow.getH(), x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + arrow.getX1();
        ym = xm * sin + ym * cos + arrow.getY1();
        xm = x;

        x = xn * cos - yn * sin + arrow.getX1();
        yn = xn * sin + yn * cos + arrow.getY1();
        xn = x;

        int[] xpoints = {arrow.getX2(), (int) xm, (int) xn};
        int[] ypoints = {arrow.getY2(), (int) ym, (int) yn};

        g2d.drawLine(arrow.getX1(), arrow.getY1(), arrow.getX2(), arrow.getY2());
        g2d.fillPolygon(xpoints, ypoints, 3);
    }


    public void setShape(int x1, int y1, int x2, int y2) {
        arrow.setX1(x1);
        arrow.setX2(x2);
        arrow.setY1(y1);
        arrow.setY2(y2);
    }
}
