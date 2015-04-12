package com.daria.utils;

import org.apache.log4j.Logger;

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

    private static final Logger log = Logger.getLogger(DateTime.class.getName());

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
        ParseDateTime parseDate2 = new ParseDateTime(date2);
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
            if (isSpecialYear(year)){
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

    public Date AddTimeOnly(Date date1, Date date2){
        ParseDateTime parseDate1 = new ParseDateTime(date1);
        ParseDateTime parseDate2 = new ParseDateTime(date2);
        int year = parseDate1.year;
        int month = parseDate1.month;
        int day = parseDate1.day;
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
            log.info("Month is febriory");
            if (isSpecialYear(year)){
                log.info("Year is special " + year);
                if(day > 29){
                    month = 3;
                    day = day - 29;
                    log.info("Current day: " + day + "in March ");
                } else {
                    log.info("Current day: " + day + "in Febriory ");
                }
            } else {
                log.info("Year is not special " + year );
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


    public boolean equelsByDate(Date date1, Date date2){
        ParseDateTime parseDate1 = new ParseDateTime(date1);
        ParseDateTime parseDate2 = new ParseDateTime(date2);
        if(parseDate1.year == parseDate2.year &&
           parseDate1.month == parseDate2.month &&
           parseDate1.day == parseDate2.day ){
            return true;
        }
        return false;

    }

    public Date getTimeOnly(Date time) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy

        ParseDateTime parse = new ParseDateTime(time);
        Date newDate = null;
        try {
            newDate = sdfDate.parse(String.format("0000-00-00 %s:%s:00", parse.hour, parse.min));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }


    /*Return date format for number of hours.
   We assume that input number of ours cant be more than numbers of hours in one month. */
    public Date createDateFromDouble(double hours){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date newDate = null;
        double forHours = hours % 24;
        int day = (int) (hours / 24);
        double forMin =  hours % 1;
        int hour =(int) (forHours / 1);
        int min = (int) (forMin * 60);
//        log.info("Input " + hours);
//        log.info("Day " + day);
//        log.info("Hour " + hour);
//        log.info("Min " + min);
        try {
            newDate = sdfDate.parse(String.format("0000-00-%s %s:%s:00", day, hour, min));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public String createTimeStringFromDouble(double hours){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date newDate = null;
        double forHours = hours % 24;
        int day = (int) (hours / 24);
        double forMin =  hours % 1;
        int hour =(int) (forHours / 1);
        int min = (int) (forMin * 60);
        if (min < 10 && min > 0){
            return String.format(" %s:0%s", hour, min);
        }
        return String.format(" %s:%s", hour, min);
    }

    public String getTimeAsString(Date date) {
        ParseDateTime parse = new ParseDateTime(date);
        return String.format("%s:%s", parse.hour, parse.min);
    }

    public Date getDateOnly(Date date) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy

        ParseDateTime parse = new ParseDateTime(date);
        Date newDate = null;
        try {
            newDate = sdfDate.parse(String.format("%s-%s-%s 00:00:00", parse.year, parse.month, parse.day));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public Date addTenMin(Date currentDate) {
        ParseDateTime parseDate = new ParseDateTime(currentDate);
        int year = parseDate.year;
        int month = parseDate.month;
        int day = parseDate.day;
        int hour = parseDate.hour;
        int min = parseDate.min + 10;

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
            log.info("Month is febriory");
            if (isSpecialYear(year)){
                log.info("Year is special " + year);
                if(day > 29){
                    month = 3;
                    day = day - 29;
                    log.info("Current day: " + day + "in March ");
                } else {
                    log.info("Current day: " + day + "in Febriory ");
                }
            } else {
                log.info("Year is not special " + year );
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
            try {
                String[] timeParse = stTime.split(":");
                this.hour = Integer.parseInt(timeParse[0]);
                this.min = Integer.parseInt(timeParse[1]);
                this.sec = Integer.parseInt(timeParse[2]);

                String[] dateParse = stDate.split("-");
                this.year = Integer.parseInt(dateParse[0]);
                this.month = Integer.parseInt(dateParse[1]);
                this.day = Integer.parseInt(dateParse[2]);
            } catch (NumberFormatException e) {
                log.error("Can't parse: " + date + " NumberFormatException: " + e);
            }
        }
    }

    private boolean isSpecialYear(int year){
        if(year % 4 != 0){
            return false;
        }
        if(year % 400 == 0){
            return true;
        }
        if (year % 100 == 0){
            return false;
        }
        return true;
    }


}
