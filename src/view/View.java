package view;


import controller.Controller;
import model.Model;
import javax.swing.*;
import java.awt.*;


public class View {


    private ImagePanel imagePanel;
    private ShapePanel shapePanel;
    private ToolBarPanel toolBarPanel;
    private TextPanel textPanel;
    private JPanel topPanel;
    private JFrame frame;
    private Model model;


    private JPanel mainPanel;


    public View(Model model) throws HeadlessException {
        this.model = model;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 300, 70);


        toolBarPanel = new ToolBarPanel();
        shapePanel = new ShapePanel();

        GridLayout gridLayout = new GridLayout(2, 1);
        topPanel = new JPanel(gridLayout);
        topPanel.add(toolBarPanel);
        topPanel.add(shapePanel);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        shapePanel.setVisible(false);

        textPanel = new TextPanel(model);
        frame.getContentPane().add(textPanel, BorderLayout.SOUTH);

        imagePanel = new ImagePanel(model);
        frame.getContentPane().add(imagePanel, BorderLayout.CENTER);

    }


    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public void setImagePanel(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    public ShapePanel getShapePanel() {
        return shapePanel;
    }

    public void setShapePanel(ShapePanel shapePanel) {
        this.shapePanel = shapePanel;
    }

    public ToolBarPanel getToolBarPanel() {
        return toolBarPanel;
    }

    public void setToolBarPanel(ToolBarPanel toolBarPanel) {
        this.toolBarPanel = toolBarPanel;
    }


    public TextPanel getTextPanel() {
        return textPanel;
    }

    public void setTextPanel(TextPanel textPanel) {
        this.textPanel = textPanel;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    Model model = new Model();
                    View view = new View(model);
                    Controller controller = new Controller(model, view);

                    SwingUtilities.updateComponentTreeUI(view.frame);
                    view.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}