package com.contguard.track.logic.comparators;

import java.util.Comparator;
import java.util.Date;

public class DateRangeComparator implements Comparator<Date> {

    private final int rangeInMinutes;

    public DateRangeComparator(int rangeInMinutes) {
        this.rangeInMinutes = rangeInMinutes;
    }

    @Override
    public int compare(Date o1, Date o2) {
        int timeDiffMins = (int) ((o1.getTime() - o2.getTime()) / (1000 * 60));
        if(timeDiffMins <= rangeInMinutes && timeDiffMins >= -rangeInMinutes) {
            return 0;
        }
        return timeDiffMins;
    }
}
