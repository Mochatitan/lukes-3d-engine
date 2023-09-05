import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;

public class Main {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());
       

        // panel to display render results
        RenderPanel renderPanel = new RenderPanel();
        
        pane.add(renderPanel, BorderLayout.CENTER);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
}
}
