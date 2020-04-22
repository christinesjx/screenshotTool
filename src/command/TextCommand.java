package command;

import shape.Text;
import java.awt.*;
import java.awt.image.BufferedImage;


public class TextCommand implements Command {

    private Text text;
    private String string;

    public TextCommand(Text text) {
        this.text = text;
    }


    @Override
    public void execute(BufferedImage image) {

        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(Color.BLACK);

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        g2d.setFont(new Font("Dialog", Font.PLAIN, 20));

        int y = text.getY();
        for (String line : text.getString().split("\n")){
            g2d.drawString(line, text.getX(), y += g2d.getFontMetrics().getHeight());
        }
    }


    public void setPoint(int x, int y) {
        text.setX(x);
        text.setY(y);
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
