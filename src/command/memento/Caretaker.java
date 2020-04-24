package command.memento;

import java.util.LinkedList;

public class Caretaker {

    private LinkedList<Memento> mementoStack;
    private LinkedList<Memento> redoStack;

    private int capacity = 50;

    public Caretaker() {

        this.mementoStack = new LinkedList<>();
        this.redoStack = new LinkedList<>();
    }

    public void addMemento(Memento memento) {

        mementoStack.addFirst(memento);
        if (mementoStack.size() > capacity) {
            mementoStack.removeLast();
        }

        redoStack.clear();
    }


    public Memento undo() {
        if (mementoStack.isEmpty()) {
            return null;
        }
        Memento memento = mementoStack.removeFirst();
        redoStack.addFirst(memento);
        return mementoStack.getFirst();
    }


    public Memento redo() {
        if (redoStack.isEmpty()) {
            return null;
        }
        Memento memento = redoStack.removeFirst();
        mementoStack.addFirst(memento);
        return mementoStack.getFirst();
    }

    public void clear() {
        mementoStack.clear();
        redoStack.clear();
    }
}