package view;

import command.Command;
import controller.Controller;
import model.Model;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;

public class View {

    private Model model;

    private ImagePanel imagePanel;
    private ShapePanel shapePanel;
    private ToolBarPanel toolBarPanel;

    private int currentAction = Command.ARROW;

    private JFrame frame;
    private JPanel canvasWrapper;


    public View(Model model) throws HeadlessException {

        this.model = model;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 865, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        toolBarPanel = new ToolBarPanel(model);
        frame.getContentPane().add(toolBarPanel, BorderLayout.NORTH);

        shapePanel = new ShapePanel(model);
        frame.getContentPane().add(shapePanel, BorderLayout.WEST);

        canvasWrapper = new JPanel();
        canvasWrapper.setAutoscrolls(true);
        canvasWrapper.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        frame.getContentPane().add(canvasWrapper, BorderLayout.CENTER);
        canvasWrapper.setLayout(null);


        JPanel InnerWrapper = new JPanel();
        FlowLayout fl_InnerWrapper = (FlowLayout) InnerWrapper.getLayout();
        fl_InnerWrapper.setVgap(0);
        fl_InnerWrapper.setHgap(0);
        fl_InnerWrapper.setAlignment(FlowLayout.LEFT);
        InnerWrapper.setBounds(0, 0, 10, 10);

        imagePanel = new ImagePanel(model);
        InnerWrapper.add(imagePanel);
        imagePanel.setBounds(2, 2, 400, 300);

        JScrollPane scrollPane = new JScrollPane(InnerWrapper);
        scrollPane.setBounds(2, 2, 402, 302);
        canvasWrapper.add(scrollPane);

        canvasWrapper.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {

                super.componentResized(e);
                scrollPane.setBounds(0,0,canvasWrapper.getWidth(),canvasWrapper.getHeight());
                canvasWrapper.revalidate();
            }

        });


        imagePanel.setLayout(null);
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


    public void registerListener(MouseAdapter listener){

        imagePanel.addMouseListener(listener);
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
