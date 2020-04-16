package com.sarawanak.todobe.helper;

public class StatusHelper {
    public static Integer getCodeForStatus(String status) {
        if (status == null) {
            return null;
        }

        status = status.toLowerCase();

        switch (status) {
            case "pending":
                return 0;
            case "completed":
                return 1;
            default:
                return -1;
        }
    }
}
