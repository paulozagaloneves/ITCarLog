package pt.itector.itcarman.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Created by me on 31/07/2019
 */
public class DBUtils {
    public static final String CURRENT_BALANCE_TAG = "CURRENT_BALANCE_TAG";
    public static final String CURRENT_MONTH_TAG = "CURRENT_MONTH_TAG";
    public static final String CURRENT_YEAR_TAG = "CURRENT YEAR_TAG";

    private static SharedPreferences sSharedPreferencesInstance;

    public static SharedPreferences getSharedPreferencesInstance(Context context){
        if(sSharedPreferencesInstance == null)
            sSharedPreferencesInstance = PreferenceManager.getDefaultSharedPreferences(context);

        return sSharedPreferencesInstance;
    }

    /* CURRENT BALANCE */
    public static void setCurrentBalanceInSharedPreferences(Context context, double currentBalance){
        SharedPreferences.Editor editor = getSharedPreferencesInstance(context).edit();

        editor.putLong(CURRENT_BALANCE_TAG, Double.doubleToRawLongBits(currentBalance));

        editor.apply();
    }

    public static double getCurrentBalanceFromSharedPreferences(Context context){
        return Double.longBitsToDouble(getSharedPreferencesInstance(context).getLong(CURRENT_BALANCE_TAG, PreferenceUtils.getCardMonthlyBudget(context)));
    }


    /* CURRENT MONTH */
    public static void setCurrentMonthInSharedPreferences(Context context, int currentMonth){
        SharedPreferences.Editor editor = getSharedPreferencesInstance(context).edit();

        editor.putInt(CURRENT_MONTH_TAG, currentMonth);

        editor.apply();
    }

    public static int getCurrentMonthFromSharedPreferences(Context context){
        return getSharedPreferencesInstance(context).getInt(CURRENT_MONTH_TAG, -1);
    }

    public static double getMonthlyBudgetFromSharedPreferences(Context context){
        return Double.longBitsToDouble(PreferenceUtils.getCardMonthlyBudget(context));
    }

    /* CURRENT YEAR */
    public static void setCurrentYearInSharedPreferences(Context context, int currentYear){
        SharedPreferences.Editor editor = getSharedPreferencesInstance(context).edit();

        editor.putInt(CURRENT_YEAR_TAG, currentYear);
        editor.apply();
    }

    public static int getCurrentYearFromSharedPreferences(Context context){
        return getSharedPreferencesInstance(context).getInt(CURRENT_YEAR_TAG, -1);
    }

}
