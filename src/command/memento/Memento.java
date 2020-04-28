package command.memento;


import java.awt.image.BufferedImage;

public class Memento {
    private BufferedImage state;

    public Memento(BufferedImage state) {

        this.state = state;
    }

    public BufferedImage getState() {
        return state;
    }

}