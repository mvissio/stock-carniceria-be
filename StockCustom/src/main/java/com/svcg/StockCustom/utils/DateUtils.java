package com.svcg.StockCustom.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * Regresa si las fechas envidas son el mismo dia no considerando la hora
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

    }

    /**
     * Regresa Verdadaro si la fecha enviada esta en el mes actual
     *
     * @param givenDate
     * @return
     */
    public static boolean isInCurrentMonth(Date givenDate) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        //set the given date in one of the instance and current date in another
        cal1.setTime(givenDate);
        cal2.setTime(new Date());

        //now compare the dates using functions
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);

    }

    /**
     * Retorna el mes de la fecha recibida en Integer
     *
     * @param date
     * @return
     */
    public static Integer getMothInt(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue();
    }

}
