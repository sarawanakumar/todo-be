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
}
