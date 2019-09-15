package pt.itector.itcarman.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by me on 31/07/2019
 */
public class DateUtils {

    /**
     * Get current month's number (1-12)
     *
     * @return the number
     */
    public static int getCurrentMonth() {
        Calendar c = Calendar.getInstance();

        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * Get current year
     *
     * @return the year
     */
    public static int getCurrentYear() {
        Calendar c = Calendar.getInstance();

        return c.get(Calendar.YEAR);
    }

    public static String getCurrentDateInFormat(String s) {
        String pattern = "MM/dd/yyyy HH:mm:ss";

        DateFormat df = new SimpleDateFormat(pattern);

        Date today = Calendar.getInstance().getTime();

        return df.format(today);
    }
}
