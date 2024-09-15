package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import model.Record;
import model.RecordList;
import persistence.JsonReader;
import persistence.JsonWriter;

// Cite codes in the ui package of Lab3.
// a bookkeeping app that can allow users add/delete record, view all the records and view the summary.
public class BookkeepingApp {
    private static final String JSON_STORE = "./data/recordlist.json";
    private Scanner scanner;
    private boolean keepGoing;
    private RecordList list;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates an instance of the Bookkeeping app console ui application
    public BookkeepingApp() {
        this.scanner = new Scanner(System.in);
        this.keepGoing = true;
        this.list = new RecordList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        printDivider();
        while (this.keepGoing) {
            handleMenu();
        }
    }

    // EFFECTS: displays and processes inputs for the main menu
    public void handleMenu() {
        displayMenu();
        String input = this.scanner.nextLine();
        processCommands(input);
    }

    // EFFECTS: displays a list of commands that can be used in the main menu
    public void displayMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("a: Add a new record");
        System.out.println("v: View all records");
        System.out.println("d: Delete a record");
        System.out.println("m: View the money summary");
        System.out.println("l: Load records from file");
        System.out.println("q: Quit the application");
        printDivider();
    }

    // EFFECTS: processes the user's input in the main menu
    public void processCommands(String input) {
        printDivider();
        switch (input) {
            case "a": addNewRecord();
                break;
            case "v": viewRecords();
                break;
            case "d": deleteRecord();
                break;
            case "m": viewSummary();
                break;
            case "l": loadRecords();
                break;
            case "q": quitApplication();
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
        }
        printDivider();
    }

    // MODIFIES: this
    // EFFECTS: add new record to the records and change the total money.
    public void addNewRecord() {
        System.out.println("Please enter the status (spent/earned):");
        String status = this.scanner.nextLine();

        System.out.println("\nPlease enter the money amount (in double):");
        String money1 = this.scanner.nextLine();
        Double money = Double.parseDouble(money1);

        System.out.println("\nPlease enter the description:");
        String description = this.scanner.nextLine();

        System.out.println("\nPlease enter the date:");
        String date = this.scanner.nextLine();

        Record r = new Record(status, money, description, date);

        list.addRecord(r);
        System.out.println("\nNew record successfully created!");

    }

    // EFFECTS: view all the records.
    public void viewRecords() {
        if (list.noRecords()) {
            System.out.println("Error: No records to review. Try adding a record first!");
            return;
        }
        System.out.println("\nThe records you made are: " + list.getListOfRecords());
    }

    // EFFECTS: view the money summary
    public void viewSummary() {
        double money = list.getMoneySum();
        System.out.println("\nThe total money is: " + money);
    }

    // MODIFIES: this
    // EFFECTS: delete one record and change the total money.
    public void deleteRecord() {
        if (list.noRecords()) {
            System.out.println("Error: No records to be deleted. Try adding a record first!");
            return;
        }
        System.out.println("\nThe records you made are: " + list.getListOfRecords());
        System.out.println("\nPlease enter the index of the record you want to delete:");
        String d1 = this.scanner.nextLine();
        int d = Integer.parseInt(d1);
        list.deleteRecord(d);
        System.out.println("\nThis record has been successfully deleted!");
    }

    // MODIFIES: this
    // EFFECTS: quit the app
    public void quitApplication() {
        System.out.println("\nDo you want to save the records? (True/False)");
        String b1 = this.scanner.nextLine();
        Boolean b = Boolean.parseBoolean(b1);

        if (b) {
            saveRecords();
        }

        System.out.println("Thanks for using the bookkeeping app!");
        System.out.println("GoodBye!");
        // 写。copy for each
        this.keepGoing = false;
    }

    // EFFECTS: prints out a line of dashes to act as a divider
    private void printDivider() {
        System.out.println("-------------------------------------");
    }

    // EFFECTS: saves the records to file
    private void saveRecords() {
        try {
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
            System.out.println("Saved " + "to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads records from file
    private void loadRecords() {
        try {
            list = jsonReader.read();
            System.out.println("Loaded " + "from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
    

}
