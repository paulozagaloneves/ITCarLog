package pt.itector.itcarman.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {

    private static SharedPreferences sSharedPreferencesInstance;

    public static SharedPreferences getSharedPreferencesInstance(Context context){
        if(sSharedPreferencesInstance == null)
            sSharedPreferencesInstance = PreferenceManager.getDefaultSharedPreferences(context);

        return sSharedPreferencesInstance;
    }


    public static String getUsername(Context context) {
        return getSharedPreferencesInstance(context).getString("app_username","");
    }

    public static String getUserEmail(Context context) {
        return getSharedPreferencesInstance(context).getString("app_user_email","");
    }

    public static Long getCardMonthlyBudget(Context context) {
        String mBudget = getSharedPreferencesInstance(context).getString("card_monthly_budget", "-1");

        return Long.parseLong(mBudget);
    }
}
