package controller;

import command.ArrowCommand;
import command.Command;
import command.OvalCommand;
import model.Model;
import shape.Arrow;
import shape.Oval;
import view.View;

import javax.imageio.ImageIO;
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
        this.view.getShapePanel().getOval().addActionListener(new OvalButtonListener());

        model.getMouseEvents().add(drawOvalEvent);
        model.getMouseEvents().add(drawArrowEvent);
    }



    private MouseAdapter drawArrowEvent = new MouseAdapter() {

        private int x1, y1;
        private int x2, y2;
        private int d, h;
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


    public void setCurrentAction(int action) {
        int currentAction = action;
        model.setCurrentAction(action);

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
        }
    }


    public void redo() {
        if (model.getResultQueue().redoable()) {
            model.getResultQueue().redo();
            view.getImagePanel().repaint();
        }
    }

    public void undo() {
        if (model.getResultQueue().undoable()) {
            model.getResultQueue().undo();
            view.getImagePanel().repaint();
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


    class ScreenshotListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event)
        {
            try {
                Thread.sleep(500);
                Robot robot = new Robot();
                java.awt.Rectangle rectangle = new java.awt.Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
  /*              File file = new File("screen-capture.png");
                boolean status = ImageIO.write(bufferedImage, "png", file);
                System.out.println("Screen Captured ? " + status + " File Path:- " + file.getAbsolutePath());
*/
                view.getImagePanel().setPreferredSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
                view.getImagePanel().revalidate();

                clean();
                model.getResultQueue().addResult(bufferedImage);
                view.getImagePanel().repaint();
                view.getImagePanel().setVisible(true);

            } catch (AWTException  | InterruptedException ex) {
                System.err.println(ex);
            }
        }
    }

    class SaveScreenshotListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                File outputfile = new File("image.jpg");
                ImageIO.write(model.getResultQueue().getLastResult(), "jpg", outputfile);


            } catch (IOException ex) {
                System.out.println("An error occurred.");
                ex.printStackTrace();
            }

        }
    }


}