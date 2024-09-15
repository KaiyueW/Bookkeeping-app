package persistence;

import org.junit.jupiter.api.Test;

import model.Record;
import model.RecordList;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RecordList rl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRecords() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRecords.json");
        try {
            RecordList rl = reader.read();
            assertEquals(0, rl.getListOfRecords().size());
            assertEquals(0, rl.getMoneySum());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRecords.json");
        try {
            RecordList rl = reader.read();
            assertEquals(5, rl.getMoneySum());
            List<Record> records = rl.getListOfRecords();
            assertEquals(2, records.size());
            checkRecord("spent", 10, "a", "a", records.get(0)); 
            checkRecord("earned", 15, "b", "b", records.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
