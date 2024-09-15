package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecordTest {
    private Record record;
    
    @BeforeEach
    void runBefore() {
        record = new Record("spent", 10.0, "food", "June 10");
    }

    @Test
    void testRecordConstructor() {
        assertEquals("spent", record.getStatus());
        assertEquals(10.0, record.getMoney());
        assertEquals("food", record.getDescription());
        assertEquals("June 10", record.getDate());
        assertEquals(0, record.getIndex());
    
    }

    @Test
    void testToString() {
        assertEquals("(0: You spent $10.0 on June 10 for food.)", record.toString());
    }

    @Test
    void testSetStatus() {
        record.setStatus("earned");
        assertEquals("earned", record.getStatus());
    }

    @Test
    void testSetStatusMultiple() {
        record.setStatus("earned");
        assertEquals("earned", record.getStatus());
        record.setStatus("spent");
        assertEquals("spent", record.getStatus());
    }

    @Test
    void testSetMoney() {
        record.setMoney(11.0);
        assertEquals(11.0, record.getMoney());
    }

    @Test
    void testSetMoneyMultiple() {
        record.setMoney(11.0);
        assertEquals(11.0, record.getMoney());
        record.setMoney(110.0);
        assertEquals(110.0, record.getMoney());
    }

    @Test
    void testSetDescription() {
        record.setDescription("car");
        assertEquals("car", record.getDescription());
    }

    @Test
    void testSetDescriptionMultiple() {
        record.setDescription("car");
        assertEquals("car", record.getDescription());
        record.setDescription("house");
        assertEquals("house", record.getDescription());
    }

    @Test
    void testSetDate() {
        record.setDate("June 11");
        assertEquals("June 11", record.getDate());
    }

    @Test
    void testSetDateMultiple() {
        record.setDate("June 11");
        assertEquals("June 11", record.getDate());
        record.setDate("June 13");
        assertEquals("June 13", record.getDate());
    }

    @Test
    void testSetIndex() {
        record.setIndex(1);
        assertEquals(1, record.getIndex());
    }
    
    @Test
    void testSetIndexMultiple() {
        record.setIndex(3);
        assertEquals(3, record.getIndex());
        record.setIndex(4);
        assertEquals(4, record.getIndex());
    }

    

}
