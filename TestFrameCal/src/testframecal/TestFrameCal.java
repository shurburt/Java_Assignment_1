/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testframecal;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author cornb
 */
public class TestFrameCal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
       
        JFrame win = new JFrame();
        JLabel head = new JLabel("Header");
        Font fontSet = new Font("Times New Roman",0, 30);
        head.setFont(fontSet);
        JPanel foot = new JPanel();
        
        foot.setLayout(new BoxLayout(foot,BoxLayout.X_AXIS));
        //foot.setAlignmentX(Component.CENTER_ALIGNMENT);
        foot.setFont(fontSet);
        
        GridLayout lay1 = new GridLayout(3,1); 
        GridLayout lay2 = new GridLayout(1,1); 
        lay2.setHgap(50);
        
        JPanel mainPane = new JPanel(lay1);
        JPanel calPane = new JPanel(lay2);
        
        Dimension r1 = new Dimension(560,620);
        Rectangle rec = new Rectangle(560,620);
        Dimension r2 = new Dimension(1300,900);
        
        
        //pan.setBounds(r1);
        SwingCalendar cal = new SwingCalendar();
        JTextArea text = new JTextArea();
        text.setSize(r1);
        text.setBounds(rec);
        JButton update = new JButton("Update"); 
        JButton save = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        update.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.out.println(cal.getText());
                text.setText(cal.getEntry());
            }
        });
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try {
                    cal.saveEntry(text.getText());
                } catch (IOException ex) {
                    Logger.getLogger(TestFrameCal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TestFrameCal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                text.setText(cal.getEntry());
            }
        });
        
       
        //cal.setLayout(null);
        cal.setLocation(20, 20);
        cal.setSize(r1);
        
        calPane.add(cal);
        calPane.add(text);
        foot.add(save);
        foot.add(update);
        foot.add(cancel);
        mainPane.add(head);
        mainPane.add(calPane);
        mainPane.add(foot);
        
        win.setSize(r2);
        win.add(mainPane);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }
    
}
