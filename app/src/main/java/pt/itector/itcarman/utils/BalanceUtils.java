package pt.itector.itcarman.utils;

import android.content.Context;

/**
 * Created by me on 31/07/2019
 */
public class BalanceUtils {

    public static void setCurrentBalance(Context context, double balance, int currentMonth, int currentYear) {

        if (context != null && currentMonth > 0 && currentYear > 2010) {
            DBUtils.setCurrentBalanceInSharedPreferences(context, balance);
            DBUtils.setCurrentMonthInSharedPreferences(context, currentMonth);
            DBUtils.setCurrentYearInSharedPreferences(context, currentYear);
        }

    }

    public static void updateBalance(Context context, double variation, int currentMonth, int currentYear) {
        double newBalance = getCurrentBalance(context) + variation;

        setCurrentBalance(context, newBalance, currentMonth, currentYear);
    }

    public static double getCurrentBalance(Context context) {
        int currentMonth = DateUtils.getCurrentMonth();
        int currentYear = DateUtils.getCurrentYear();

        if (currentMonth != DBUtils.getCurrentMonthFromSharedPreferences(context) || currentYear != DBUtils.getCurrentYearFromSharedPreferences(context))
            // we don't have a balance from the current month -> reset it
            setCurrentBalance(context, DBUtils.getMonthlyBudgetFromSharedPreferences(context), currentMonth, currentYear);

        return DBUtils.getCurrentBalanceFromSharedPreferences(context);
    }
}
