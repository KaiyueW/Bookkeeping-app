package persistence;


import model.Record;
import model.RecordList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Cite code from the persistence package in the JsonSerializationDemo
// Represents a reader that reads RecordList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads RecordList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RecordList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecordList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it 直接copy
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses RecordList from JSON object and returns it
    private RecordList parseRecordList(JSONObject jsonObject) {
        RecordList rl = new RecordList();
        addRecords(rl, jsonObject);
        return rl;
    }

    // MODIFIES: rl
    // EFFECTS: parses thingies from JSON object and adds them to RecordList
    private void addRecords(RecordList rl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfRecords");
        for (Object json : jsonArray) {
            JSONObject nextRecord = (JSONObject) json;
            addRecord(rl, nextRecord);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to RecordList
    private void addRecord(RecordList rl, JSONObject jsonObject) {
        String status = jsonObject.getString("status");
        double money = jsonObject.getDouble("money");
        String description = jsonObject.getString("description");
        String date = jsonObject.getString("date");
        Record r = new Record(status, money, description, date);

        rl.addRecord(r);
    }
}
