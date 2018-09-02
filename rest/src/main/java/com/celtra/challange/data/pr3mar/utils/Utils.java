package com.celtra.challange.data.pr3mar.utils;

import java.util.Date;

public class Utils {

    public static Pair<Date, Date> getDates(Date from, Date to) {
        Date dateFrom;
        Date dateTo;
        if(to == null) {
            dateTo = new Date(); // now
        } else {
            dateTo = new Date(to.getTime());
        }
        if(from == null) {
            dateFrom = new Date(
                    dateTo.getTime() - (7 * 24 * 60 * 60 * 1000) // one (1) week prior to now
            );
        } else {
            dateFrom = new Date(from.getTime());
        }
        if(dateTo.before(dateFrom)) {
            // if the `to` date is before the from date, swap them
            Date buffer = new Date(dateTo.getTime());
            dateTo = new Date(dateFrom.getTime());
            dateFrom = new Date(buffer.getTime());
        }
        long timeDiff = dateTo.getTime() - dateFrom.getTime();
        if (timeDiff < 24 * 60 * 60 * 1000) {
            // if the time frame is shorter than one (1) day,
            // then expand the from date for one (1) day before the current value
            dateFrom = new Date(dateFrom.getTime() - 24 * 60 * 60 * 1000);
        }
        return new Pair<>(dateFrom, dateTo);
    }

}
