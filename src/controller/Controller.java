package controller;

import command.*;
import model.Model;
import shape.*;
import view.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.Rectangle;
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

        addListeners();
    }

    private void addListeners() {

        this.view.getToolBarPanel().getScreenShotButton().addActionListener(new ScreenshotListener());
        this.view.getToolBarPanel().getSaveButton().addActionListener(new SaveScreenshotListener());
        this.view.getShapePanel().getArrow().addActionListener(new ArrowButtonListener());
        this.view.getShapePanel().getRectangle().addActionListener(new RectangleButtonListener());
        this.view.getShapePanel().getOval().addActionListener(new OvalButtonListener());
        this.view.getTextPanel().getAddText().addActionListener(new AddTextListener());
        this.view.getTextPanel().getTextField().getDocument().addDocumentListener(new MyDocumentListener());
        this.view.getShapePanel().getRedo().addActionListener(new RedoListener());
        this.view.getShapePanel().getUndo().addActionListener(new UndoListener());
        this.view.getShapePanel().getCrop().addActionListener(new CropListener());
        this.view.getShapePanel().getAddText().addActionListener(new TextButtonListener());
    }


    private MouseAdapter arrowEvent = new MouseAdapter() {

        private int x1, y1;
        private int x2, y2;
        private ArrowCommand arrowCommand;

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("mousePressed");
            model.setMouseMoveFinished(false);

            x1 = e.getX();
            y1 = e.getY();
            x2 = e.getX();
            y2 = e.getY();

            ShapeType shapeType = ShapeType.ARROW;
            arrowCommand = new ArrowCommand(new Arrow(x1, y1, x2, y2));
            model.setCurrentCommand(arrowCommand);

            view.getImagePanel().repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("mouseDragged");

            x2 = e.getX();
            y2 = e.getY();

            arrowCommand.setShape(x1, y1, x2, y2);
            view.getImagePanel().repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("mouseReleased");

            mouseDragged(e);
            model.setMouseMoveFinished(true);

            arrowCommand = null;
        }
    };

    private MouseAdapter ovalEvent = new MouseAdapter() {

        private int x1, y1;
        private int x2, y2;

        private OvalCommand ovalCommand;

        @Override
        public void mousePressed(MouseEvent e) {
            model.setMouseMoveFinished(false);

            x1 = e.getX();
            y1 = e.getY();
            x2 = e.getX();
            y2 = e.getY();

            ovalCommand = new OvalCommand(new Oval(x1, y1, 0, 0));
            model.setCurrentCommand(ovalCommand);
            view.getImagePanel().repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

            mouseDragged(e);
            model.setMouseMoveFinished(true);
            ovalCommand = null;
        }

        @Override
        public void mouseDragged(MouseEvent e) {

            x2 = e.getX();
            y2 = e.getY();

            if (x2 >= x1 && y2 >= y1) {
                ovalCommand.setPoint(x1, y1);
            } else if (x2 >= x1) {
                ovalCommand.setPoint(x1, y2);
            } else ovalCommand.setPoint(x2, Math.min(y2, y1));

            ovalCommand.setWidth(Math.abs(x1 - x2));
            ovalCommand.setHeight(Math.abs(y1 - y2));


            view.getImagePanel().repaint();
        }

    };

    private MouseAdapter rectangleEvent = new MouseAdapter() {

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

            } else rectangleCommand.setPoint(x2, Math.min(y2, y1));

            rectangleCommand.setWidth(Math.abs(x1 - x2));
            rectangleCommand.setHeight(Math.abs(y1 - y2));

            view.getImagePanel().repaint();
        }

    };

    private MouseAdapter textEvent = new MouseAdapter() {

        private int x, y;

        private TextCommand textCommand;

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("mousePressed textCommand");
            model.setMouseMoveFinished(false);
            x = e.getX();
            y = e.getY();

            textCommand = new TextCommand(new Text(x, y, model.getCurrentText()));
            model.setCurrentCommand(textCommand);
            view.getImagePanel().repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("mouseReleased textCommand");

            mouseDragged(e);
            model.setMouseMoveFinished(true);
            model.setCurrentText("");
            textCommand = null;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("mouseDragged textCommand");

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
            System.out.println("cropEvent mousePressed");

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
            System.out.println("cropEvent mouseReleased");

            model.setMouseMoveFinished(true);

            setPoints(e);

            BufferedImage bufferedImage = cropCommand.getImage();
            System.out.println(bufferedImage.getWidth());
            System.out.println(bufferedImage.getHeight());

            model.getMemento().addImage(bufferedImage);

            cropCommand = null;

            setCurrentAction(Command.ARROW);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            System.out.println("cropEvent mouseDragged");

            model.setMouseMoveFinished(false);

            setPoints(e);


            view.getImagePanel().repaint();
        }

        private void setPoints(MouseEvent e) {
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

        // remove all mouse listener
        view.getImagePanel().removeMouseListener(rectangleEvent);
        view.getImagePanel().removeMouseMotionListener(rectangleEvent);

        view.getImagePanel().removeMouseListener(arrowEvent);
        view.getImagePanel().removeMouseMotionListener(arrowEvent);

        view.getImagePanel().removeMouseListener(cropEvent);
        view.getImagePanel().removeMouseMotionListener(cropEvent);

        view.getImagePanel().removeMouseListener(textEvent);
        view.getImagePanel().removeMouseMotionListener(textEvent);

        view.getImagePanel().removeMouseListener(ovalEvent);
        view.getImagePanel().removeMouseMotionListener(ovalEvent);

        view.getImagePanel().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        switch (currentAction) {
            case Command.ARROW:
                view.getImagePanel().addMouseListener(arrowEvent);
                view.getImagePanel().addMouseMotionListener(arrowEvent);
                break;
            case Command.OVAL:
                view.getImagePanel().addMouseListener(ovalEvent);
                view.getImagePanel().addMouseMotionListener(ovalEvent);
                break;
            case Command.RECTANGLE:
                view.getImagePanel().addMouseListener(rectangleEvent);
                view.getImagePanel().addMouseMotionListener(rectangleEvent);
                break;
            case Command.TEXT:
                view.getImagePanel().addMouseListener(textEvent);
                view.getImagePanel().addMouseMotionListener(textEvent);
                break;
            case Command.CROP:
                view.getImagePanel().addMouseListener(cropEvent);
                view.getImagePanel().addMouseMotionListener(cropEvent);
                break;
        }
    }


    class ArrowButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("command_Arrow");
            setCurrentAction(Command.ARROW);
        }
    }

    class OvalButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("command_Oval");
            setCurrentAction(Command.OVAL);
        }
    }

    class RectangleButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("command_Rectangle");
            setCurrentAction(Command.RECTANGLE);
        }
    }

    class AddTextListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("command_Text");
            setCurrentAction(Command.TEXT);
        }
    }

    class CropListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println("command_Crop");
            setCurrentAction(Command.CROP);
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


    class TextButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            AbstractButton abstractButton = (AbstractButton) event.getSource();
            boolean selected = abstractButton.getModel().isSelected();
            if (selected) {
                view.getTextPanel().setVisible(true);
            }
        }
    }


    class UndoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            model.getMemento().undo();
            view.getImagePanel().repaint();

        }
    }

    class RedoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            model.getMemento().redo();
            view.getImagePanel().repaint();

        }
    }


    class ScreenshotListener implements ActionListener {

        /**
         * excluding own app. from screenshot
         * reference: https://stackoverflow.com/questions/5898910/screenshot-issue-excluding-own-app-from-screenshot
         *
         * @param event
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            view.getFrame().setVisible(false);
            Timer timer = new Timer(400, al);
            timer.setRepeats(false);
            timer.start();
        }


        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    Robot robot = new Robot();

                    // Get the current screen dimensions.
                    Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
                    int width = (int) d.getWidth();
                    int height = (int) d.getHeight();

                    /*
                     * Create a screen capture of the active window and then create a buffered image
                     * to be saved to disk.
                     */
                    BufferedImage bufferedImage = robot.createScreenCapture(new Rectangle(0, 0, width, height));
                    view.getImagePanel().setPreferredSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
                    view.getImagePanel().revalidate();

                    // clean memento
                    model.getMemento().clean();
                    view.getImagePanel().repaint();

                    // add image to memento
                    model.getMemento().addImage(bufferedImage);
                    view.getImagePanel().repaint();
                    view.getFrame().setVisible(true);

                } catch (AWTException ex) {
                    System.err.println(ex);
                }
            }
        };

    }

    class SaveScreenshotListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                String path = "";
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int r = j.showSaveDialog(null);

                if (r == JFileChooser.APPROVE_OPTION) {
                    path = j.getSelectedFile().getAbsolutePath();
                    File outputfile = new File(path);
                    ImageIO.write(model.getMemento().getLastResult(), "jpg", outputfile);
                }
            } catch (IOException ex) {
                System.out.println("An error occurred.");
                ex.printStackTrace();
            }

        }
    }


}