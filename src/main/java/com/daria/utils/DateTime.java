package com.daria.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Дарья on 03.04.2015.
 */
public class DateTime {

    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
    private static final Set<Integer> longMonth = new HashSet<Integer>();
    private static final Set<Integer> shortMonth = new HashSet<Integer>();
    private static final Set<Integer> febriory = new HashSet<Integer>();

    static{
        longMonth.add(1);
        febriory.add(2);
        longMonth.add(3);
        shortMonth.add(4);
        longMonth.add(5);
        shortMonth.add(6);
        longMonth.add(7);
        longMonth.add(8);
        shortMonth.add(9);
        longMonth.add(10);
        shortMonth.add(11);
        longMonth.add(0);
    }

    public Date sum(Date date1, Date date2){
        ParseDateTime parseDate1 = new ParseDateTime(date1);
        ParseDateTime parseDate2 = new ParseDateTime(date1);
        int year = parseDate1.year + parseDate2.year;
        int month = parseDate1.month + parseDate2.month;
        int day = parseDate1.day + parseDate2.day;
        int hour = parseDate1.hour + parseDate2.hour;
        int min = parseDate1.min + parseDate2.min;

        if(min > 59){
            hour = hour + 1;
            min = min - 60;
        }
        if(hour > 23){
            day = day + 1;
            hour = hour - 24;
        }

     /*MONTHS*/
        if( day > 31 && longMonth.contains(month % 12)) {
            day = day - 31;
            month = month + 1;
        } else if( day > 30 && shortMonth.contains(month % 12)) {
            day = day - 30;
            month = month + 1;
        } else if (febriory.contains(month % 12)){
            if (isSpesialYear(year)){
                if(day > 29){
                    month = 3;
                    day = day - 29;
                }
            } else {
                if(day > 28){
                    month = 3;
                    day = day - 28;
                }
            }
        }
     /*Year*/
        if(month > 12){
            year = year + 1;
            month = month - 12;

        }
        Date resultDate = null;
        try {
            resultDate = sdfDate.parse(String.format("%s-%s-%s %s:%s:00", year, month, day, hour, min));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;

    }


    private class ParseDateTime{
        int year;
        int month;
        int day;
        int hour;
        int min;
        int sec;

        public ParseDateTime(Date date){

            String strDate = sdfDate.format(date);
            String[] dateAndTime = strDate.toString().split(" ");
            String stDate = dateAndTime[0];
            String stTime = dateAndTime[1];

            String[] timeParse = stTime.split(":");
            this.hour = Integer.parseInt(timeParse[0]);
            this.min = Integer.parseInt(timeParse[1]);
            this.sec = Integer.parseInt(timeParse[2]);

            String[] dateParse = stTime.split("-");
            this.year = Integer.parseInt(dateParse[0]);
            this.month = Integer.parseInt(dateParse[1]);
            this.day = Integer.parseInt(dateParse[2]);

        }
    }

    private boolean isSpesialYear(int year){
        if(year % 4 != 0){
            return false;
        }
        if(year % 400 == 0){
            return true;
        }
        if (year % 100 == 0){
            return false;
        }
        return false;
    }
}
