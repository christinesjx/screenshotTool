package command;

import shape.Oval;
import shape.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class TextCommand implements Command {


    private Text text;

    public TextCommand(Text text) {
        this.text = text;
    }

    @Override
    public void execute(BufferedImage image) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(Color.BLACK);
        g2d.drawString(text.getString(), text.getX(), text.getY());
    }


    public void setPoint(int x, int y) {
        text.setX(x);
        text.setY(y);
    }

    public void setString(String string) {
        text.setString(string);
    }
}

/*
public class TextCommand implements Command {


    private Text text;

    public TextCommand(Text text) {
        this.text = text;
    }

    @Override
    public void execute(BufferedImage image) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(Color.BLACK);
        g2d.drawString(text.getString(), text.getX(), text.getY());
    }


    public void setPoint(int x, int y) {
        text.setX(x);
        text.setY(y);
    }

    public void setString(String string) {
        text.setString(string);
    }
}*/
