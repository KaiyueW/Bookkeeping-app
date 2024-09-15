package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e0;
    private Date d0;
    private Event e1;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e0 = new Event("Sensor open at door"); // (1)
        d0 = Calendar.getInstance().getTime(); // (2)
        e1 = new Event("Sensor open at door");
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", e0.getDescription());
        assertEquals(d0, e0.getDate());

    }

    @Test
    public void testEqual() {
        assertFalse(e0.equals(null));
        assertFalse(e0.equals(d0));
        assertFalse(e0.equals(e1));
    }

    @Test
    public void testHashCode() {
        assertEquals(e1.hashCode(), e1.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals(d0.toString() + "\n" + "Sensor open at door", e0.toString());
    }
}
