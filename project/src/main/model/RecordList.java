package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// This class represnets a list of records and the money summary.
public class RecordList implements Writable {
    private double moneySum;
    private ArrayList<Record> listOfRecords;
    private int currentIndex;

    // EFFECTS: create a list of records which is an empty list initially and the
    // total money/currentIndex are 0 as well.
    public RecordList() {
        this.moneySum = 0;
        this.listOfRecords = new ArrayList<>();
        this.currentIndex = 0;

    }

    // MODIFIES: this
    // EFFECTS: add the record to the list of records;
    // add 1 to the current index and set it as the index of the record;
    // add money to the moneySum if the user earned money, substract money if the
    // user spent money.
    public void addRecord(Record r) {
        currentIndex = listOfRecords.size();
        r.setIndex(currentIndex);
        if (r.getStatus().equals("spent")) {
            this.moneySum = moneySum - r.getMoney();
            this.listOfRecords.add(r);
            EventLog.getInstance().logEvent(new Event("Add a new record to the list."));
        } else if (r.getStatus().equals("earned")) {
            this.moneySum = moneySum + r.getMoney();
            this.listOfRecords.add(r);
            EventLog.getInstance().logEvent(new Event("Add a new record to the list."));
        }

    }

    // REQUIRES: r is an element in ListOfRecords
    // MODIFIES: this
    // EFFECTS: delete the record from the list of records;
    // update the index;
    // delete money from the moneySum if the user earned money before,
    // add the money back if the user spent money before.
    public void deleteRecord(int index) {
        Record r = this.getListOfRecords().get(index);
        this.getListOfRecords().remove(index);
        for (Record record : this.getListOfRecords()) {
            if (record.getIndex() > index) {
                record.setIndex(record.getIndex() - 1);
            }
        }
        if (r.getStatus().equals("spent")) {
            this.moneySum = moneySum + r.getMoney();
        } else {
            this.moneySum = moneySum - r.getMoney();
        }
        EventLog.getInstance().logEvent(new Event("Delete a record from the list."));
    }

    // EFFECTS: return whether the records are empty
    public boolean noRecords() {
        return listOfRecords.isEmpty();
    }

    // EFFECTS: get the moneySum of the records
    public double getMoneySum() {
        EventLog.getInstance().logEvent(new Event("Show the money summary of the records: "
                + Double.toString(this.moneySum)));
        return this.moneySum;
    }

    // EFFECTS: get the ListOfRecords of the records
    public ArrayList<Record> getListOfRecords() {
        EventLog.getInstance().logEvent(new Event("Show the list of records."));
        return this.listOfRecords;
    }

    // EFFECTS: get the currentIndex of the records
    public int getCurrentIndex() {
        return this.currentIndex;
    }

    // EFFECTS: convert a RecordList's properties into a JSON object.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listOfRecords", recordsToJson());
        json.put("MoneySummary", moneySum);
        return json;
    }

    // EFFECTS: returns records in this record list as a JSON array
    private JSONArray recordsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Record r : listOfRecords) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }

}
