package model;

import command.Command;
import command.memento.Originator;
import command.memento.ScreenshotType;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Model {

    private Originator originator;

    private Command currentCommand = null;
    private int currentAction = Command.UNSELECT;
    private boolean isMouseMoveFinished = true;
    private String currentText = "";
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    private ScreenshotType screenshotType = null;

    public Model() {

        originator = new Originator();
        BufferedImage initBFImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        initBFImage.setRGB(0,0,155);
        originator.setAndStoreState(initBFImage);
    }

    public ScreenshotType getScreenshotType() {
        return screenshotType;
    }

    public void setScreenshotType(ScreenshotType screenshotType) {
        this.screenshotType = screenshotType;
    }

    public Command getCurrentCommand() {
        return currentCommand;
    }

    public void setCurrentCommand(Command currentCommand) {
        this.currentCommand = currentCommand;
    }

    public Originator getOriginator() {
        return originator;
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
