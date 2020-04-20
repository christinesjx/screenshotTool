package model;

import command.Command;
import command.Memento;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class Model {

    private Command currentCommand = null;
    private Memento resultQueue = new Memento();
    private ArrayList<MouseAdapter> mouseEvents = new ArrayList<>();
    private int currentAction = Command.ARROW;

    private boolean isMouseMoveFinished = true;

    public Command getCurrentCommand() {
        return currentCommand;
    }

    public void setCurrentCommand(Command currentCommand) {
        this.currentCommand = currentCommand;
    }

    public Memento getResultQueue() {
        return resultQueue;
    }

    public ArrayList<MouseAdapter> getMouseEvents() {
        return mouseEvents;
    }


    public int getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
    }

    public boolean isMouseMoveFinished() {
        return isMouseMoveFinished;
    }

    public void setMouseMoveFinished(boolean mouseMoveFinished) {
        isMouseMoveFinished = mouseMoveFinished;
    }
}
