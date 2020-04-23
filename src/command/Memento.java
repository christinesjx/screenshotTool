package command;

import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class Memento {

    private int maxImageNum = 10;

    private LinkedList<BufferedImage> commandStack = new LinkedList<>();

    private LinkedList<BufferedImage> redoStack = new LinkedList<>();


    public void addImage(BufferedImage image) {

        commandStack.addFirst(image);
        if (commandStack.size() > maxImageNum) {
            commandStack.removeLast();
        }

        redoStack.clear();
    }

    public void undo() {
        if (commandStack.isEmpty()) {
            return;
        }
        BufferedImage image = commandStack.removeFirst();
        redoStack.addFirst(image);
    }


    public void redo() {
        if (redoStack.isEmpty()) {
            return;
        }
        BufferedImage image = redoStack.removeFirst();
        commandStack.addFirst(image);
    }

    public void clean() {
        commandStack.clear();
        redoStack.clear();
    }

    public BufferedImage getLastResult() {
        return commandStack.getFirst();
    }

    public boolean isEmpty() {
        return commandStack.isEmpty();
    }

}
