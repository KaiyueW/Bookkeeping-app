package persistence;

import org.junit.jupiter.api.Test;

import model.Record;
import model.RecordList;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            RecordList rl = new RecordList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RecordList rl = new RecordList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRecords.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRecords.json");
            rl = reader.read();
            assertEquals(0, rl.getListOfRecords().size());
            assertEquals(0, rl.getMoneySum());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            RecordList rl = new RecordList();
            rl.addRecord(new Record("spent", 10, "a", "a"));
            rl.addRecord(new Record("earned", 15, "b", "b"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRecords.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRecords.json");
            rl = reader.read();
            assertEquals(5, rl.getMoneySum());
            List<Record> records = rl.getListOfRecords();
            assertEquals(2, records.size());
            checkRecord("spent", 10, "a", "a", records.get(0)); 
            checkRecord("earned", 15, "b", "b", records.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}