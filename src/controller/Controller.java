package controller;

import command.*;
import model.Model;
import shape.Arrow;
import shape.Oval;
import shape.Text;
import view.View;

import javax.imageio.ImageIO;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Controller {
    private Model model;
    private View view;


    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        this.view.getToolBarPanel().addScreenshotListener(new ScreenshotListener());
        this.view.getToolBarPanel().addSaveScreenshotListener(new SaveScreenshotListener());
        this.view.getShapePanel().getArrow().addActionListener(new ArrowButtonListener());
        this.view.getShapePanel().getRectangle().addActionListener(new RectangleButtonListener());
        this.view.getShapePanel().getOval().addActionListener(new OvalButtonListener());
        this.view.getTextPanel().getAddText().addActionListener(new TextButtonListener());
        this.view.getTextPanel().getTextField().getDocument().addDocumentListener(new MyDocumentListener());
        this.view.getShapePanel().getRedo().addActionListener(new RedoListener());
        this.view.getShapePanel().getUndo().addActionListener(new UndoListener());
        this.view.getShapePanel().getCrop().addActionListener(new CropListener());

        model.getMouseEvents().add(drawOvalEvent);
        model.getMouseEvents().add(drawArrowEvent);
        model.getMouseEvents().add(drawTextEvent);
        model.getMouseEvents().add(drawRectangleEvent);
        model.getMouseEvents().add(cropEvent);

    }



    private MouseAdapter drawArrowEvent = new MouseAdapter() {

        private int x1, y1;
        private int x2, y2;
        private ArrowCommand drawArrow;

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("mousePressed");
            model.setMouseMoveFinished(false);

            x1 = e.getX();
            y1 = e.getY();
            x2 = e.getX();
            y2 = e.getY();

            drawArrow = new ArrowCommand(new Arrow(x1, y1, x2, y2, 3,3));
            model.setCurrentCommand(drawArrow);

            view.getImagePanel().repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("mouseDragged");

            x2 = e.getX();
            y2 = e.getY();

            drawArrow.setShape(x1, y1, x2, y2, 8, 8);
            view.getImagePanel().repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("mouseReleased");

            mouseDragged(e);
            model.setMouseMoveFinished(true);

            drawArrow = null;

        }
    };

    private MouseAdapter drawOvalEvent = new MouseAdapter() {

        private int x1, y1;
        private int x2, y2;

        private  OvalCommand drawOval;

        @Override
        public void mousePressed(MouseEvent e) {
            model.setMouseMoveFinished(false);

            x1 = e.getX();
            y1 = e.getY();
            x2 = e.getX();
            y2 = e.getY();

            drawOval = new OvalCommand(new Oval(x1, y1, 0, 0));

            model.setCurrentCommand(drawOval);

            view.getImagePanel().repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

            mouseDragged(e);
            model.setMouseMoveFinished(true);

            drawOval = null;
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            x2 = e.getX();
            y2 = e.getY();

            if (x2 >= x1 && y2 >= y1) {
                drawOval.setPoint(x1, y1);
            } else if (x2 >= x1) {
                drawOval.setPoint(x1, y2);
            } else if (y2 >= y1) {
                drawOval.setPoint(x2, y1);
            } else {
                drawOval.setPoint(x2, y2);
            }

            drawOval.setWidth(Math.abs(x1 - x2));
            drawOval.setHeight(Math.abs(y1 - y2));


            view.getImagePanel().repaint();
        }

    };

    private MouseAdapter drawRectangleEvent = new MouseAdapter() {

        private int x1, y1;
        private int x2, y2;

        private RectangleCommand rectangleCommand;

        @Override
        public void mousePressed(MouseEvent e) {
            model.setMouseMoveFinished(false);

            x1 = e.getX();
            y1 = e.getY();
            x2 = e.getX();
            y2 = e.getY();

            rectangleCommand = new RectangleCommand(new shape.Rectangle(x1, y1, 0, 0));

            model.setCurrentCommand(rectangleCommand);

            view.getImagePanel().repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            model.setMouseMoveFinished(true);
            mouseDragged(e);

            rectangleCommand = null;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            x2 = e.getX();
            y2 = e.getY();

            if (x2 >= x1 && y2 >= y1) {
                rectangleCommand.setPoint(x1, y1);
            } else if (x2 >= x1) {
                rectangleCommand.setPoint(x1, y2);
            } else if (y2 >= y1) {
                rectangleCommand.setPoint(x2, y1);
            } else {
                rectangleCommand.setPoint(x2, y2);
            }

            rectangleCommand.setWidth(Math.abs(x1 - x2));
            rectangleCommand.setHeight(Math.abs(y1 - y2));

            view.getImagePanel().repaint();
        }

    };

    private MouseAdapter drawTextEvent = new MouseAdapter() {

        private int x, y;

        private TextCommand textCommand;

        @Override
        public void mousePressed(MouseEvent e) {
            model.setMouseMoveFinished(false);
            x = e.getX();
            y = e.getY();

            textCommand = new TextCommand(new Text(x, y, model.getCurrentText()));
            model.setCurrentCommand(textCommand);
            view.getImagePanel().repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

            mouseDragged(e);
            model.setMouseMoveFinished(true);
            model.setCurrentText("");
            textCommand = null;
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            x = e.getX();
            y = e.getY();

            textCommand.setPoint(x, y);
            view.getImagePanel().repaint();
        }
    };



    private MouseAdapter cropEvent = new MouseAdapter() {

        private int x1, y1;
        private int x2, y2;

        private CropCommand cropCommand;

        @Override
        public void mousePressed(MouseEvent e) {
            model.setMouseMoveFinished(false);
            System.out.println("mousePressed");

            x1 = e.getX();
            y1 = e.getY();
            x2 = e.getX();
            y2 = e.getY();

            cropCommand = new CropCommand(new shape.Rectangle(x1, y1, 0, 0));
            model.setCurrentCommand(cropCommand);
            view.getImagePanel().repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            model.setMouseMoveFinished(true);

            setPoints(e);

            BufferedImage bufferedImage = cropCommand.getImage();
            System.out.println(bufferedImage.getWidth());
            System.out.println(bufferedImage.getHeight());

            model.getResultQueue().addResult(bufferedImage);

            cropCommand = null;

            setCurrentAction(Command.ARROW);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            model.setMouseMoveFinished(false);

            setPoints(e);


            view.getImagePanel().repaint();
        }

        private void setPoints(MouseEvent e){
            x2 = e.getX();
            y2 = e.getY();

            if (x2 >= x1 && y2 >= y1) {
                cropCommand.setPoint(x1, y1);
            } else if (x2 >= x1) {
                cropCommand.setPoint(x1, y2);
            } else if (y2 >= y1) {
                cropCommand.setPoint(x2, y1);
            } else {
                cropCommand.setPoint(x2, y2);
            }

            cropCommand.setWidth(Math.abs(x1 - x2));
            cropCommand.setHeight(Math.abs(y1 - y2));
        }

    };





    public void setCurrentAction(int action) {
        int currentAction = action;
        model.setCurrentAction(action);

        System.out.println(currentAction);

        for (MouseAdapter mouseAdapter : model.getMouseEvents()) {
            view.getImagePanel().removeMouseListener(mouseAdapter);
            view.getImagePanel().removeMouseMotionListener(mouseAdapter);
        }

        view.getImagePanel().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        switch (currentAction) {
            case Command.ARROW:
                view.getImagePanel().addMouseListener(drawArrowEvent);
                view.getImagePanel().addMouseMotionListener(drawArrowEvent);
                break;
            case Command.OVAL:
                view.getImagePanel().addMouseListener(drawOvalEvent);
                view.getImagePanel().addMouseMotionListener(drawOvalEvent);
                break;
            case Command.RECTANGLE:
                view.getImagePanel().addMouseListener(drawRectangleEvent);
                view.getImagePanel().addMouseMotionListener(drawRectangleEvent);
                break;
            case Command.TEXT:
                view.getImagePanel().addMouseListener(drawTextEvent);
                view.getImagePanel().addMouseMotionListener(drawTextEvent);
                break;
            case Command.CROP:
                view.getImagePanel().addMouseListener(cropEvent);
                view.getImagePanel().addMouseMotionListener(cropEvent);
                break;
        }
    }


    public void clean() {
        model.getResultQueue().clean();
        view.getImagePanel().repaint();
    }

    class ArrowButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event)
        {
            System.out.println("DRAW_Arrow");
            setCurrentAction(Command.ARROW);
        }
    }

    class OvalButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event)
        {
            System.out.println("DRAW_OVAL");

            setCurrentAction(Command.OVAL);
        }
    }

    class RectangleButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event)
        {
            System.out.println("DRAW_OVAL");
            setCurrentAction(Command.RECTANGLE);
        }
    }

    class TextButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event)
        {
            System.out.println("Text");
            setCurrentAction(Command.TEXT);
        }
    }


    class MyDocumentListener implements DocumentListener {
        String newline = "\n";

        @Override
        public void insertUpdate(DocumentEvent e) {
            printIt(e);
        }

        public void removeUpdate(DocumentEvent e) {
            printIt(e);
        }

        public void changedUpdate(DocumentEvent e) {
            printIt(e);
        }

        private void printIt(DocumentEvent documentEvent) {

            System.out.println(view.getTextPanel().getTextField().getText());
            model.setCurrentText(view.getTextPanel().getTextField().getText());
        }

    }

    class CropListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event)
        {
            System.out.println("DRAW_crop");
            setCurrentAction(Command.CROP);
        }
    }

    class UndoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event)
        {
            if (model.getResultQueue().undoable()) {
                model.getResultQueue().undo();
                view.getImagePanel().repaint();
            }
        }
    }

    class RedoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event)
        {
            if (model.getResultQueue().redoable()) {
                model.getResultQueue().redo();
                view.getImagePanel().repaint();
            }
        }
    }

    class ScreenshotListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event)
        {
            try {
                Robot robot = new Robot();

                /**
                 * Get the current screen dimensions.
                 */
                Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
                int width = (int) d.getWidth();
                int height = (int) d.getHeight();

                robot.delay(500);

                /**
                 * Create a screen capture of the active window and then create a buffered image
                 * to be saved to disk.
                 */
                BufferedImage bufferedImage = robot.createScreenCapture(new Rectangle(0, 0, width,
                        height));



                view.getImagePanel().setPreferredSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
                view.getImagePanel().revalidate();

                clean();
                model.getResultQueue().addResult(bufferedImage);
                view.getImagePanel().repaint();
                view.getImagePanel().setVisible(true);

            } catch (AWTException ex) {
                System.err.println(ex);
            }
        }
    }

    class SaveScreenshotListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                File outputfile = new File("image.jpg");
                System.out.println(model.getResultQueue().getLastResult().getWidth());
                System.out.println(model.getResultQueue().getLastResult().getHeight());

                ImageIO.write(model.getResultQueue().getLastResult(), "jpg", outputfile);


            } catch (IOException ex) {
                System.out.println("An error occurred.");
                ex.printStackTrace();
            }

        }
    }


}