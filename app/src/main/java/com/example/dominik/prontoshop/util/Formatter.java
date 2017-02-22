package com.example.dominik.prontoshop.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {

    public static String formatCurrency(double amount){

        NumberFormat baseFormat = NumberFormat.getCurrencyInstance();
        String moneyString = baseFormat.format(amount);
        return moneyString;
    }

    public static String formatDate(long date){

        String displayDate = new SimpleDateFormat("MMM dd").format(new Date(date));
        return displayDate;
    }
}
