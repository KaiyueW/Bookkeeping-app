package ui.gui;

import model.EventLog;
import model.Event;
import model.Record;
import model.RecordList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.io.FileNotFoundException;

// Cite codes in phase 3 Edx instructions
// represent a graphical user interface (GUI)
public class GUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/recordlist.json";

    private RecordList recordList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JTextArea textArea;
    private ImageIcon welcome;
    private JLabel imageLabel;
    private JSplitPane mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    // EFFECTS: construct the GUI with a panel splited into 2 parts, add 6 buttoms
    // on the bottom panel and a display area in the top panel
    public GUI() {
        super("Bookkeeping App");
        recordList = new RecordList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 700));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new BorderLayout());

        mainPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        mainPanel.setDividerLocation(350);
        add(mainPanel, BorderLayout.CENTER);

        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout());
        mainPanel.setTopComponent(topPanel);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 3));
        mainPanel.setBottomComponent(bottomPanel);

        addButtons1();
        addButtons2();

        addDisplay();

        loadImages();
    }

    // MODIFIES: this
    // EFFECTS: add 3 buttons to the panel
    private void addButtons1() {

        JButton addButton = new JButton("Add Record");
        addButton.setActionCommand("Add");
        addButton.addActionListener(this);
        bottomPanel.add(addButton);

        JButton viewButton = new JButton("View Records");
        viewButton.setActionCommand("View");
        viewButton.addActionListener(this);
        bottomPanel.add(viewButton);

        JButton deleteButton = new JButton("Delete Record");
        deleteButton.setActionCommand("Delete");
        deleteButton.addActionListener(this);
        bottomPanel.add(deleteButton);
    }

    // MODIFIES: this
    // EFFECTS: add 3 buttons to the panel
    private void addButtons2() {

        JButton summaryButton = new JButton("View Summary");
        summaryButton.setActionCommand("Summary");
        summaryButton.addActionListener(this);
        bottomPanel.add(summaryButton);

        JButton loadButton = new JButton("Load Records");
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(this);
        bottomPanel.add(loadButton);

        JButton quitButton = new JButton("Quit");
        quitButton.setActionCommand("Quit");
        quitButton.addActionListener(this);
        bottomPanel.add(quitButton);
    }

    // MODIFIES: this
    // EFFECTS: add a display area to the panel
    private void addDisplay() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        textArea.setText("Welcome to my project!");

        imageLabel = new JLabel(welcome);
        topPanel.add(imageLabel, BorderLayout.NORTH);
    }

    // EFFECTS: load the image from the folder and display it.
    private void loadImages() {
        URL imageUrl = getClass().getResource("image/welcome.png");
        welcome = new ImageIcon(imageUrl);
        Image image = welcome.getImage();
        Image resizedImage = image.getScaledInstance(450, 350, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        imageLabel.setIcon(resizedIcon);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFCTS: show the method that is called when the the JButton buttoms is
    // clicked.
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add":
                addRecord();
                break;
            case "View":
                viewRecords();
                break;
            case "Delete":
                deleteRecord();
                break;
            case "Summary":
                viewSummary();
                break;
            case "Load":
                loadRecords();
                break;
            case "Quit":
                quitApplication();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: pop up a new panel.
    public void addRecord() {
        new AddPanel(this);
    }

    // MODIFIES: this
    // EFFECTS: add a record to the panel and display the certain text.
    public void addOne(Record r) {
        recordList.addRecord(r);
        if ((r.getStatus().equals("earned")) || (r.getStatus().equals("spent"))) {
            textArea.setText("New record successfully created!");
        } else {
            textArea.setText("Invalid record! Please try again.");
        }

    }

    // EFFECTS: view all the records.
    public void viewRecords() {
        if (recordList.noRecords()) {
            textArea.setText("Error: No records to review. Try adding a record first!");
            return;
        }

        StringBuilder records = new StringBuilder();

        for (Record record : recordList.getListOfRecords()) {
            records.append(record.toString()).append("\n");
        }

        textArea.setText("The records you made are:\n" + records);

    }

    // MODIFIES: this
    // EFFECTS: delete one record and change the total money.
    public void deleteRecord() {
        if (recordList.noRecords()) {
            textArea.setText("Error: No records to be deleted. Try adding a record first!");
            return;
        }

        StringBuilder records = new StringBuilder();
        for (Record record : recordList.getListOfRecords()) {
            records.append(record.toString()).append("\n");
        }
        textArea.setText("The records you made are:\n" + records);

        String d1 = JOptionPane.showInputDialog(this, "Please enter the index of the record you want to delete:");
        int d = Integer.parseInt(d1);
        recordList.deleteRecord(d);
        textArea.setText("This record has been successfully deleted!");
    }

    // EFFECTS: view the money summary
    public void viewSummary() {
        double money = recordList.getMoneySum();
        textArea.setText("The total money is: " + money);
    }

    // EFFECTS: load the records from the file
    private void loadRecords() {
        try {
            recordList = jsonReader.read();
            textArea.setText("Loaded " + "from " + JSON_STORE);
        } catch (IOException e) {
            textArea.setText("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: quit the app
    private void quitApplication() {
        printLog(EventLog.getInstance());
        int confirm = JOptionPane.showConfirmDialog(this, "Do you want to save the records?", "Quit",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            saveRecords();
            System.exit(0);
        } else if (confirm == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    // EFFECTS: saves the records to file
    private void saveRecords() {
        try {
            jsonWriter.open();
            jsonWriter.write(recordList);
            jsonWriter.close();
            textArea.setText("Saved " + "to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            textArea.setText("Unable to write to file: " + JSON_STORE);
        }
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }

        repaint();
    }

}
