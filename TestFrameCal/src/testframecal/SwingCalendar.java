package testframecal;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
 
public class SwingCalendar extends JTable {
 
  DefaultTableModel model;
  Calendar cal = new GregorianCalendar();
  JLabel label;
  int selectedCode = 0;
  int currentYear = Calendar.YEAR+2017;
  int currentMonth = Calendar.MONTH+2;
  int currentDay = 0;
  String infoStr = "";
  
  FileInputStream in = new FileInputStream("data.dat");
  ObjectInputStream ins = new ObjectInputStream(in);
  Hashtable hash = (Hashtable)ins.readObject();
  
  
  
  SwingCalendar() throws FileNotFoundException, IOException, ClassNotFoundException {
 
    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //this.setTitle("Swing Calandar");
    
    
    this.ins.close();
    //os.writeObject(table);
    this.setSize(620,560);
    
    this.setLayout(new BorderLayout());
    this.setVisible(true);
 
    label = new JLabel();
    label.setHorizontalAlignment(SwingConstants.CENTER);
    
    Rectangle rec = new Rectangle(560,620);
    JTextArea text = new JTextArea();
    text.setSize(620,560);
    text.setBounds(rec);
    
    JButton b1 = new JButton("<-");
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        cal.add(Calendar.MONTH, -1);
        if(currentMonth == 1){
            currentMonth = 12;
            currentYear--;
        }
        else{
            currentMonth--;
        }
        updateMonth();
      }
    });
 
    JButton b2 = new JButton("->");
    b2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        cal.add(Calendar.MONTH, +1);
        if(currentMonth == 12){
            currentMonth = 1;
            currentYear++;
        }
        else{
            currentMonth++;
        }
        updateMonth();
      }
    });
 
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(b1,BorderLayout.WEST);
    panel.add(label,BorderLayout.CENTER);
    panel.add(b2,BorderLayout.EAST);
 
    
    String [] columns = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
    model = new DefaultTableModel(null,columns);
    JTable table = new JTable(model);
    table.setCellSelectionEnabled(true);
    table.setRowSelectionAllowed(true);
    table.setColumnSelectionAllowed(true);
    
    ListSelectionListener listen = new ListSelectionListener() { public void valueChanged(ListSelectionEvent e) {
        
        String selectedData ="";
        
        int[] selectedRow = table.getSelectedRows();
        int[] selectedColumns = table.getSelectedColumns();
        for (int i = 0; i < selectedRow.length; i++) {
          for (int j = 0; j < selectedColumns.length; j++) {
            System.out.println("Row: " + selectedRow[i] + " | " + "Columns: " + selectedColumns[j]);
            selectedData = table.getValueAt(selectedRow[i], selectedColumns[j]).toString();
            currentDay = Integer.parseInt(selectedData);
            
          }
          
        }
        
          
        
        selectedCode = currentYear * currentMonth * currentDay;
        System.out.println("Year: " + currentYear);
        System.out.println("Month: " + currentMonth);
        System.out.println("Day: " + selectedData);
        
          
      }};
    
    table.getColumnModel().getSelectionModel().addListSelectionListener(listen);
    table.getSelectionModel().addListSelectionListener(listen);
    
    
//    cellColumnModel.addListSelectionListener(new ListSelectionListener() {
//      public void valueChanged(ListSelectionEvent e) {
//        
//        String selectedData = null;
//        
//        int[] selectedRow = table.getSelectedRows();
//        int[] selectedColumns = table.getSelectedColumns();
//        for (int i = 0; i < selectedRow.length; i++) {
//          for (int j = 0; j < selectedColumns.length; j++) {
//            System.out.println("Row: " + selectedRow[i] + " | " + "Columns: " + selectedColumns[j]);
//            selectedData = table.getValueAt(selectedRow[i], selectedColumns[j]).toString();
//            int temp = Integer.parseInt(selectedData);
//            selectedCode = currentYear * currentMonth * temp;
//            
//          }
//        }
//        
//          
//        
//        
//        System.out.println("Year: " + currentYear);
//        System.out.println("Month: " + currentMonth);
//        System.out.println("Day: " + selectedData);
//        
//          
//      }
//      
//    });
    
    
    JScrollPane pane = new JScrollPane(table);
 
    this.add(panel,BorderLayout.NORTH);
    this.add(pane,BorderLayout.CENTER);
    
    this.updateMonth();
 
  }
  
  void updateMonth() {
    cal.set(Calendar.DAY_OF_MONTH, 1);
 
    String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
    int year = cal.get(Calendar.YEAR);
    label.setText(month + " " + year);
 
    int startDay = cal.get(Calendar.DAY_OF_WEEK);
    int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);
 
    model.setRowCount(0);
    model.setRowCount(weeks);
 
    int i = startDay-1;
    for(int day=1;day<=numberOfDays;day++){
      model.setValueAt(day, i/7 , i%7 );    
      i = i + 1;
    }
    
  }
  
  public int getCode(){
      return selectedCode;
  }
  public String getText(){
      return infoStr;
  }
  public void saveEntry(String entry) throws FileNotFoundException, IOException, ClassNotFoundException{
    
    FileOutputStream out = new FileOutputStream("data.dat");
    ObjectOutputStream os = new ObjectOutputStream(out);
    this.hash.put(selectedCode, entry);
    
    System.out.println(hash.get(selectedCode));
    System.out.println(hash.containsKey(selectedCode));
    os.writeObject(this.hash);
    os.close();
    
  }
  public String getEntry(){
      System.out.println(this.hash.contains(selectedCode));
      if(this.hash.containsKey(selectedCode)){
      System.out.println(this.hash.get(selectedCode).toString());
      
      return this.hash.get(selectedCode).toString();
      }
      else
          return "";
  }
  
  public static void main(String[] arguments) throws IOException {
    JFrame.setDefaultLookAndFeelDecorated(true);
    
  }
 
}