package com.sarawanak.todobe.helper;

import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaBuilder;

import static org.junit.jupiter.api.Assertions.*;

class PriorityHelperTest {

    @Test
    void shouldGetCodeForPriority() {
        Integer highCode = PriorityHelper.getCodeForPriority("High");
        Integer medCode = PriorityHelper.getCodeForPriority("Medium");
        Integer lowCode = PriorityHelper.getCodeForPriority("Low");
        Integer invalidCode = PriorityHelper.getCodeForPriority("Randome");
        Integer nullCode = PriorityHelper.getCodeForPriority(null);

        assertEquals(highCode, 1);
        assertEquals(medCode, 5);
        assertEquals(lowCode, 10);
        assertEquals(invalidCode, -1);
        assertNull(nullCode);
    }

    @Test
    void shouldGetPriorityForCode() {
        String hiPriority = PriorityHelper.getPriorityForCode(1);
        String medPriority = PriorityHelper.getPriorityForCode(5);
        String lowPriority = PriorityHelper.getPriorityForCode(10);
        String nullPriority = PriorityHelper.getPriorityForCode(-1);

        assertEquals(hiPriority, "High");
        assertEquals(medPriority, "Medium");
        assertEquals(lowPriority, "Low");
        assertEquals(nullPriority, "None");
    }
}
