package com.sarawanak.todobe.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusHelperTest {

    @Test
    void shouldGetCodeForStatus() {
        Integer penCode = StatusHelper.getCodeForStatus("pending");
        Integer comCode = StatusHelper.getCodeForStatus("completed");
        Integer invalidCode = StatusHelper.getCodeForStatus("None");
        Integer nullCode = StatusHelper.getCodeForStatus(null);

        assertEquals(penCode, 0);
        assertEquals(comCode, 1);
        assertEquals(invalidCode, -1);
        assertNull(nullCode);
    }

    @Test
    void shouldGetStatusForCode() {
        String penStat = StatusHelper.getStatusForCode(0);
        String comStat = StatusHelper.getStatusForCode(1);
        String noneStat = StatusHelper.getStatusForCode(89);

        assertEquals(penStat, "Pending");
        assertEquals(comStat, "Completed");
        assertEquals(noneStat, "Unknown");
    }
}
