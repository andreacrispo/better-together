package my.app.bt.bettertogether;


import my.app.bt.bettertogether.domain.ParticipantDto;

import java.time.LocalDate;

public class Utils {


    public static LocalDate obtainPaymentDate(ParticipantDto participant) {
        return participant.getMonthPaid() == null || participant.getYearPaid() == null
                ? LocalDate.now()
                : LocalDate.of(participant.getYearPaid(), participant.getMonthPaid(),1);
    }

    public static boolean paymentDateMatches(LocalDate paymentDate, LocalDate dateParm){
        return paymentDate != null && dateParm != null
                && paymentDate.getMonthValue() == dateParm.getMonthValue()
                && paymentDate.getYear() == dateParm.getYear();
    }


}
