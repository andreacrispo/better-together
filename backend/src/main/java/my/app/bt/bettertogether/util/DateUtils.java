package my.app.bt.bettertogether.util;


import my.app.bt.bettertogether.domain.ParticipantDto;

import java.time.LocalDate;

public class DateUtils {


    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }


    public static LocalDate createPreviousDate(int month, int year) {
        int monthPrev = month;
        int yearPrev = year;
        if (month - 1 <= 0) {
            monthPrev = 12;
            yearPrev -= 1;
        } else {
            monthPrev -= 1;
        }
        return LocalDate.of(yearPrev, monthPrev, 1);
    }


    public static LocalDate obtainPaymentDate(ParticipantDto participant) {
        return participant.getMonthPaid() == null || participant.getYearPaid() == null
                ? LocalDate.now()
                : LocalDate.of(participant.getYearPaid(), participant.getMonthPaid(), 1);
    }


}
