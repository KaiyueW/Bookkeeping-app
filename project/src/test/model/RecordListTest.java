package model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecordListTest {
    private RecordList records;
    private Record r1;
    private Record r2;
    private Record r3;
    private Record r4;
    private Record r5;

    
    @BeforeEach
    void runBefore() {
        records = new RecordList();
        r1 = new Record("spent", 10.0, "food", "June 10");
        r2 = new Record("spent", 20.0, "car", "June 12");
        r3 = new Record("earned", 30.0, "work", "June 13");
        r4 = new Record("earned", 40.0, "part-time", "June 14");
        r5 = new Record("earn", 40.0, "part-time", "June 14");
    }

    @Test
    void testRecordsConstructor() {
        assertEquals(0, records.getMoneySum());
        assertEquals(0, records.getListOfRecords().size());
        assertEquals(0, records.getCurrentIndex());
    }

    @Test
    void testAddRecord() {
        records.addRecord(r1);
        assertEquals(-10.0, records.getMoneySum());
        assertEquals(0, r1.getIndex());
        assertEquals(r1, records.getListOfRecords().get(0));
        assertEquals(1, records.getListOfRecords().size());

    }

    @Test
    void testAddRecordMultiple() {
        records.addRecord(r1);
        assertEquals(-10.0, records.getMoneySum());
        assertEquals(0, r1.getIndex());
        assertEquals(r1, records.getListOfRecords().get(0));
        assertEquals(1, records.getListOfRecords().size());
        records.addRecord(r2);
        assertEquals(-30.0, records.getMoneySum());
        assertEquals(1, r2.getIndex());
        assertEquals(r1, records.getListOfRecords().get(0));
        assertEquals(r2, records.getListOfRecords().get(1));
        assertEquals(2, records.getListOfRecords().size());
    }

    @Test
    void testAddRecordEarned() {
        records.addRecord(r3);
        assertEquals(0, r3.getIndex());
        assertEquals(30.0, records.getMoneySum());
        assertEquals(r3, records.getListOfRecords().get(0));
        assertEquals(1, records.getListOfRecords().size());

    }

    @Test
    void testAddRecordEarnedMultiple() {
        records.addRecord(r3);
        assertEquals(30.0, records.getMoneySum());
        assertEquals(0, r3.getIndex());
        assertEquals(r3, records.getListOfRecords().get(0));
        assertEquals(1, records.getListOfRecords().size());
        records.addRecord(r4);
        assertEquals(70.0, records.getMoneySum());
        assertEquals(1, r4.getIndex());
        assertEquals(r3, records.getListOfRecords().get(0));
        assertEquals(r4, records.getListOfRecords().get(1));
        assertEquals(2, records.getListOfRecords().size());
    }

    @Test
    void testAddRecordMixMultiple() {
        records.addRecord(r1);
        assertEquals(-10.0, records.getMoneySum());
        assertEquals(0, r1.getIndex());
        assertEquals(r1, records.getListOfRecords().get(0));
        assertEquals(1, records.getListOfRecords().size());
        records.addRecord(r4);
        assertEquals(30.0, records.getMoneySum());
        assertEquals(1, r4.getIndex());
        assertEquals(r1, records.getListOfRecords().get(0));
        assertEquals(r4, records.getListOfRecords().get(1));
        assertEquals(2, records.getListOfRecords().size());
        records.addRecord(r3);
        assertEquals(2, r3.getIndex());
        assertEquals(60.0, records.getMoneySum());
        assertEquals(r1, records.getListOfRecords().get(0));
        assertEquals(r4, records.getListOfRecords().get(1));
        assertEquals(r3, records.getListOfRecords().get(2));
        assertEquals(3, records.getListOfRecords().size());
    }

    @Test
    void testAddRecordEarnedNone() {
        records.addRecord(r1);
        assertEquals(1, records.getListOfRecords().size());
        records.addRecord(r5);
        assertEquals(1, records.getListOfRecords().size());
       
    }


    @Test
    void testDeleteRecord() {
        records.addRecord(r3);
        assertEquals(30.0, records.getMoneySum());
        assertEquals(r3, records.getListOfRecords().get(0));
        assertEquals(1, records.getListOfRecords().size());
        records.deleteRecord(0);
        assertEquals(0.0, records.getMoneySum());
        assertEquals(0, records.getListOfRecords().size());
    }

    @Test
    void testDeleteRecordEarnedNone() {
        records.addRecord(r1);
        assertEquals(1, records.getListOfRecords().size());
        records.addRecord(r3);
        assertEquals(2, records.getListOfRecords().size());
        records.deleteRecord(0);
        assertEquals(1, records.getListOfRecords().size());
       
    }

    @Test
    void testDeleteRecordEarnedUnchanged() {
        records.addRecord(r1);
        assertEquals(1, records.getListOfRecords().size());
        records.addRecord(r3);
        assertEquals(2, records.getListOfRecords().size());
        assertEquals(0, r1.getIndex());
        assertEquals(1, r3.getIndex());
        records.addRecord(r2);
        assertEquals(3, records.getListOfRecords().size());
        records.deleteRecord(2);
        assertEquals(2, records.getListOfRecords().size());
        assertEquals(0, r1.getIndex());
        assertEquals(1, r3.getIndex());
       
    }

    @Test
    void testDeleteRecordSpent() {
        records.addRecord(r1);
        assertEquals(-10.0, records.getMoneySum());
        assertEquals(r1, records.getListOfRecords().get(0));
        assertEquals(1, records.getListOfRecords().size());
        records.deleteRecord(0);
        assertEquals(0.0, records.getMoneySum());
        assertEquals(0, records.getListOfRecords().size());
    }

    @Test
    void testDeleteRecordSpentMultiple() {
        records.addRecord(r1);
        assertEquals(-10.0, records.getMoneySum());
        assertEquals(r1, records.getListOfRecords().get(0));
        assertEquals(1, records.getListOfRecords().size());
        records.addRecord(r4);
        assertEquals(30.0, records.getMoneySum());
        assertEquals(r1, records.getListOfRecords().get(0));
        assertEquals(r4, records.getListOfRecords().get(1));
        assertEquals(2, records.getListOfRecords().size());
        records.addRecord(r1);
        records.deleteRecord(0);
        assertEquals(30.0, records.getMoneySum());
        assertEquals(2, records.getListOfRecords().size());
        assertEquals(0, r4.getIndex());
        assertEquals(1, r1.getIndex());
        assertEquals(r4, records.getListOfRecords().get(0));
        records.deleteRecord(0);
        assertEquals(0, r1.getIndex());
        records.deleteRecord(0);
        assertEquals(0.0, records.getMoneySum());
        assertEquals(0, records.getListOfRecords().size());

    }

    @Test
    void testNoRecords() {
        assertTrue(records.noRecords());
        records.addRecord(r1);
        assertFalse(records.noRecords());
    }

    @Test
    void testNoRecordsMultiple() {
        assertTrue(records.noRecords());
        records.addRecord(r1);
        assertFalse(records.noRecords());
        records.deleteRecord(0);
        assertTrue(records.noRecords());
    }


}
