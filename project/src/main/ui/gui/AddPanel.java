package ui.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Record;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// cite the codes from Edx instructions of phase 3.
// represent a new panel which appears when "addRecord" is called.
public class AddPanel extends JFrame implements ActionListener {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;
    private GUI gui;

    public AddPanel(GUI gui) {
        super("AddRecord");
        this.gui = gui;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new BorderLayout());


        JButton btn = new JButton("Confirm");
        btn.setActionCommand("myButton");
        btn.addActionListener(this); 

        addInput();

        add(btn, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: add the buttons to the panel.
    private void addInput() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 1));
    
        label1 = new JLabel("Please enter the status (spent/earned):");
        field1 = new JTextField(15);
        label2 = new JLabel("Please enter the money amount (in double):");
        field2 = new JTextField(15);
        label3 = new JLabel("Please enter the description:");
        field3 = new JTextField(15);
        label4 = new JLabel("Please enter the date:");
        field4 = new JTextField(15);
        
        inputPanel.add(label1);
        inputPanel.add(field1);
        inputPanel.add(label2);
        inputPanel.add(field2);
        inputPanel.add(label3);
        inputPanel.add(field3);
        inputPanel.add(label4);
        inputPanel.add(field4);

        add(inputPanel, BorderLayout.CENTER);

    }



    // This is the method that is called when the the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            String status = field1.getText();
            String money1 = field2.getText();
            Double money = Double.parseDouble(money1);
            String description = field3.getText();
            String date = field4.getText();

            Record r = new Record(status, money, description, date);

            gui.addOne(r);

            dispose();

        }
    }
}
