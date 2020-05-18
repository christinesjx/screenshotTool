package command.memento;

import java.awt.image.BufferedImage;

public class Originator {

    private BufferedImage image;
    private Caretaker caretaker;

    public Originator() {
        this.caretaker = new Caretaker();
    }

    private void setImage(BufferedImage image) {
        this.image = image;
    }


    public BufferedImage getImage() {
        return image;
    }


    private Memento createMemento() {
        return new Memento(image);
    }

    private void restoreMemento(Memento m) {
        if (m != null) {
            this.setImage(m.getState());
        } else {
            System.out.println("no state can be restored");
        }
    }

    public void setAndStoreState(BufferedImage newImage) {
        this.setImage(newImage);
        caretaker.addMemento(this.createMemento());
    }

    public void undo() {
        this.restoreMemento(caretaker.undo());
    }

    public void redo() {
        this.restoreMemento(caretaker.redo());
    }

    public void clear(){
        this.caretaker.clear();
    }
}