package model;

import command.Command;
import command.Memento;

public class Model {

    private Command currentCommand = null;
    private Memento memento = new Memento();
    private int currentAction = Command.ARROW;
    private boolean isMouseMoveFinished = true;
    private String currentText = "";

    public Command getCurrentCommand() {
        return currentCommand;
    }

    public void setCurrentCommand(Command currentCommand) {
        this.currentCommand = currentCommand;
    }

    public Memento getMemento() {
        return memento;
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

    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }
}
