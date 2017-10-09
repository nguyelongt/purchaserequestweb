package com.prs.Utilities;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;


public class StringUtility {
    public static String getCurrencyFormat(double d) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(d);
    }

    public static String getFormattedDouble(double input)
    {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(input);
    }

    public static Date convertTodaysDateToSQLDate()
    {
        java.util.Date today = new java.util.Date();
        return new Date(today.getTime());
    }

    public static Date convertStringToSQLDate(String dateString)
    {
        java.util.Date tempDate;

        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tempDate = sdf.parse(dateString);
            return new Date(tempDate.getTime());
        }
        catch (ParseException e)
        {
            System.out.println(e);
            return null;
        }
    }

    public static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

}
