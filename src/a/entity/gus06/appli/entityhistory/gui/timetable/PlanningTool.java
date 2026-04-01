package a.entity.gus06.appli.entityhistory.gui.timetable;

import java.util.*;

public class PlanningTool {
    
    private static Calendar getCalendar(int year, int month, int day)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,day+1); 
        return calendar;
    }
    
    private static Calendar getCalendar(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); 
        return calendar;
    }
    
    
    public static boolean isToday(int year, int month, int day)
    {return (currentYear()==year && currentMonth()==month && currentDay()==day);}
    
    public static int currentYear()
    {return Calendar.getInstance().get(Calendar.YEAR);}
    
    public static int currentMonth()
    {return Calendar.getInstance().get(Calendar.MONTH);}
    
    public static int currentDay()
    {return Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1;}
    
    
    public static boolean isToday(Date date)
    {
        int c = date.compareTo(new Date());
        return c==0;
    }
    
    public static boolean isValidDay(int year, int month, int day)
    {
        int d = getCalendar(year,month,day).get(Calendar.DAY_OF_MONTH);
        return day==d-1;
    }
    
    public static int dayOfWeek(int year, int month, int day)
    {return getCalendar(year,month,day).get(Calendar.DAY_OF_WEEK);}
    
    public static boolean isMonday(int year, int month, int day) {return dayOfWeek(year,month,day)==Calendar.MONDAY;}
    public static boolean isTuesday(int year, int month, int day) {return dayOfWeek(year,month,day)==Calendar.TUESDAY;}
    public static boolean isWednesday(int year, int month, int day) {return dayOfWeek(year,month,day)==Calendar.WEDNESDAY;}
    public static boolean isThursday(int year, int month, int day) {return dayOfWeek(year,month,day)==Calendar.THURSDAY;}
    public static boolean isFriday(int year, int month, int day) {return dayOfWeek(year,month,day)==Calendar.FRIDAY;}
    public static boolean isSaturday(int year, int month, int day) {return dayOfWeek(year,month,day)==Calendar.SATURDAY;}
    public static boolean isSunday(int year, int month, int day) {return dayOfWeek(year,month,day)==Calendar.SUNDAY;}
    public static boolean isWeekend(int year, int month, int day) {return isSaturday(year,month,day) || isSunday(year,month,day);}
    
    
    public static int dayOfWeek(Date date)
    {return getCalendar(date).get(Calendar.DAY_OF_WEEK);}
    
    public static boolean isMonday(Date date) {return dayOfWeek(date)==Calendar.MONDAY;}
    public static boolean isTuesday(Date date) {return dayOfWeek(date)==Calendar.TUESDAY;}
    public static boolean isWednesday(Date date) {return dayOfWeek(date)==Calendar.WEDNESDAY;}
    public static boolean isThursday(Date date) {return dayOfWeek(date)==Calendar.THURSDAY;}
    public static boolean isFriday(Date date) {return dayOfWeek(date)==Calendar.FRIDAY;}
    public static boolean isSaturday(Date date) {return dayOfWeek(date)==Calendar.SATURDAY;}
    public static boolean isSunday(Date date) {return dayOfWeek(date)==Calendar.SUNDAY;}
    public static boolean isWeekend(Date date) {return isSaturday(date) || isSunday(date);}
	
	
	
    public static String month(int x)
    {
        switch(x)
        {
        	case 0:return "January";
        	case 1:return "Febuary";
        	case 2:return "March";
        	case 3:return "April";
        	case 4:return "May";
        	case 5:return "June";
        	case 6:return "July";
        	case 7:return "August";
        	case 8:return "September";
        	case 9:return "October";
        	case 10:return "November";
        	case 11:return "December";
        	default:return "";
        }
    }
    
    
    
    private static HashSet frenchHoliday;
    
    private static void initFrenchHoliday()
    {
        frenchHoliday = new HashSet();
        frenchHoliday.add("0_0");   // 1 janvier
        frenchHoliday.add("3_11");  // 12 avril
        frenchHoliday.add("4_0");  // 1 mai
        frenchHoliday.add("4_7");  // 8 mai
        frenchHoliday.add("4_19");  // 20 mai
        frenchHoliday.add("4_30");  // 31 mai
        frenchHoliday.add("6_13");  // 14 juillet
        frenchHoliday.add("7_14");  // 15 aout
        frenchHoliday.add("10_0");  // 1 novembre
        frenchHoliday.add("10_10");  // 11 novembre
        frenchHoliday.add("11_24");  // 25 decembre
    }
    
    
    public static boolean isFrenchHoliday(int month, int day)
    {
        if(frenchHoliday==null)initFrenchHoliday();
        return frenchHoliday.contains(month+"_"+day);
    }
}
