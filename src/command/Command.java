package command;


import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Command + Memento --> Redo, Undo
 * https://www.developer.com/design/article.php/3720566/Working-With-Design-Patterns-Memento.htm
 */
public interface Command {

    int UNSELECT = 0;
    int ARROW = 1;
    int OVAL = 2;
    int RECTANGLE = 3;
    int TEXT = 4;
    int CROP = 5;


    void execute(BufferedImage image);


}
