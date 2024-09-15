package model;

import org.json.JSONObject;

import persistence.Writable;

// This class represents one record with the money spent/got,
// the description of the money, the recording date and its index.
public class Record implements Writable {
    private String status;
    private double money;
    private String description;
    private String date;
    private int index;


    // EFFECTS: construct a record, the initial money and index are both zero, 
    //          the status, description and the date are set to be empty.
    public Record(String status, double money, String description, String date) {
        this.status = status;
        this.money = money;
        this.description = description;
        this.date = date;
        this.index = 0;
        
    }

    // MODIFIES: this
    // EFFECTS: set status whether the money is spent or earned
    public void setStatus(String status) {
        this.status = status;
    }

    // REQUIRES: amount cannot be 0.
    // MODIFIES: this
    // EFFECTS: set the money of the record
    public void setMoney(double amount) {
        this.money = amount;
    }

    // MODIFIES: this
    // EFFECTS: set description of where the moeny spent or the source of the income.
    public void setDescription(String words) {
        this.description = words;
    }

    // MODIFIES: this
    // EFFECTS: set the date of the recording
    public void setDate(String date) {
        this.date = date;
    }

    // MODIFIES: this
    // EFFECTS: set the index of the recording
    public void setIndex(int index) {
        this.index = index;
    }

    // EFFECTS: get the status of the record
    public String getStatus() {
        return this.status;
    }

    // EFFECTS: get the money of the record
    public double getMoney() {
        return this.money;
    }


    // EFFECTS: get the description of the record
    public String getDescription() {
        return this.description;
    }


    // EFFECTS: get the date of the record
    public String getDate() {
        return this.date;
    }

    // EFFECTS: get the index of the record
    public int getIndex() {
        return this.index;
    }


    // cite the code in Teller project.
    // EFFECTS: returns string representation of this record
    @Override
    public String toString() {
        return "(" + index + ": You " + status + " $" + money + " on " + date
                + " for " + description + ".)";
    }



    // EFFECTS: convert a record's properties into a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("money", money);
        json.put("description", description);
        json.put("date", date);
        return json;
    }

}
