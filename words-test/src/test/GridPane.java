package test;

import javax.swing.*;
import java.awt.*;

public class GridPane  {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setSize(3000,4000);
        frame.setLayout(new GridLayout(10,1));
        frame.setVisible(true);
        JLabel label=new JLabel("a",SwingConstants.CENTER);
        label.setSize(20,40);
        frame.add(label);
    }
}
