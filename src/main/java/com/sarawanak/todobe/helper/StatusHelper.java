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

    public static String getStatusForCode(Integer code) {
        String status = "";
        if (code == null) {
            status = "Unknown";
        }
        switch (code) {
            case 1:
                status = "Completed";
                break;
            case 0:
                status = "Pending";
                break;
            default:
                status = "Unknown";
        }
        return status;
    }
}
