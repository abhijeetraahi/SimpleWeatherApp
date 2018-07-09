package com.raahi.simpleweatherapp.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Raahi on 09-07-2018.
 */

public class Util {
    public static String ConvertDateToDay(String input_date) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date dt1 = null;
        try {
            dt1 = format1.parse(input_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (isToday(dt1)) {
            return "Today";
        } else if (isTomorrow(dt1)) {
            return "Tomorrow";
        } else {
            DateFormat format2 = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            return format2.format(dt1);
        }
    }

    private static boolean isToday(Date d) {
        return DateUtils.isToday(d.getTime());
    }

    private static boolean isTomorrow(Date d) {
        return DateUtils.isToday(d.getTime() - DateUtils.DAY_IN_MILLIS);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
