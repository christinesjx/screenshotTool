import javax.swing.*;
import java.awt.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

 class TextAreaEnter extends JPanel
{
    private JTextArea message = new JTextArea(5, 20);
    private JTextArea display = new JTextArea(5, 20);

    public TextAreaEnter()
    {
        display.setEditable( false );

        add( new JScrollPane(message) );
        add( new JScrollPane(display) );

        Action enter = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                display.append( message.getText() + "\n" );
                message.setText("");
            }
        };

        message.getActionMap().put("insert-break", enter);
    }

    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("TextAreaEnter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( new TextAreaEnter() );
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }
}