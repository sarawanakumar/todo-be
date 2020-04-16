package com.sarawanak.todobe.helper;

public class PriorityHelper {
    public static Integer getCodeForPriority(String priority) {
        if (priority == null) {
            return null;
        }

        priority = priority.toLowerCase();

        switch (priority) {
            case "high":
                return 1;
            case "medium":
                return 5;
            case "low":
                return 10;
            default:
                return -1;
        }
    }

    public static String getPriorityForCode(Integer code) {
        String priority = "";
        if (code == null) {
            priority = "None";
        }
        switch (code) {
            case 1:
                priority = "High";
                break;
            case 5:
                priority = "Medium";
                break;
            case 10:
                priority = "Low";
                break;
            default:
                priority = "None";
        }
        return priority;
    }
}
