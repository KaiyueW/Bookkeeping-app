package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Record;

public class JsonTest {
    protected void checkRecord(String status, double money, String description, String date, Record r) {
        assertEquals(status, r.getStatus());
        assertEquals(money, r.getMoney());
        assertEquals(description, r.getDescription());
        assertEquals(date, r.getDate());
    }
}