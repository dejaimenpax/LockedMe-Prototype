package LockedMeApp;

import java.util.Comparator;

public class IgnoreCaseComparator implements Comparator<String> {
    @Override
    public int compare(String str1, String str2) {
        return str1.compareToIgnoreCase(str2);
    }
}
