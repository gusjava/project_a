package a.entity.gus06.sys.scheduling1.checkcurrent;

import a.framework.*;
import java.util.Map;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180118";}

	public static final String KEY_MONTHS = "months";
	public static final String KEY_MONTH_DAYS = "month_days";
	public static final String KEY_WEEK_DAYS = "week_days";
	public static final String KEY_HOURS = "hours";
	public static final String KEY_MINUTES = "minutes";
	public static final String KEY_SECONDES = "secondes";
	public static final String KEY_DATES = "dates";
	public static final String KEY_TIMES = "times";
	public static final String KEY_DURATION_MAX = "duration_max";
	public static final String KEY_DURATION_MIN = "duration_min";
	public static final String KEY_DATE_START = "date_start";
	public static final String KEY_DATE_END = "date_end";
	public static final String KEY_EACH = "each";
	public static final String KEY_LAST_DATE = "last_date";
	public static final String KEY_DEFAULT = "default";
	public static final String KEY_CURRENT_DATE = "current_date";
	public static final String KEY_PREVIOUS_DATE = "previous_date";
	public static final String KEY_DISABLED = "disabled";

	private SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat HHmmss = new SimpleDateFormat("HHmmss");
	

	private Service toDate;
	private Service toBoolean;
	private Service toDuration;
	private Service getMonth;
	private Service getDayOfMonth;
	private Service getDayOfWeek;
	private Service getHour;
	private Service getMinute;
	private Service getSecond;
	
	private Service each;
	
	
	public EntityImpl() throws Exception
	{
		toDate = Outside.service(this,"gus06.find.date");
		toBoolean = Outside.service(this,"gus06.find.boolean1");
		toDuration = Outside.service(this,"gus06.find.long1.duration");
		getMonth = Outside.service(this,"gus06.time.date.month");
		getDayOfMonth = Outside.service(this,"gus06.time.date.dayofmonth");
		getDayOfWeek = Outside.service(this,"gus06.time.date.dayofweek");
		getHour = Outside.service(this,"gus06.time.date.get.hour");
		getMinute = Outside.service(this,"gus06.time.date.get.minute");
		getSecond = Outside.service(this,"gus06.time.date.get.second");
		
		each = Outside.service(this,"gus06.sys.scheduling1.checkcurrent.each");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		Date d = (Date) o[1];
		Date d0 = (Date) o[2];
		
		boolean disabled = boolDF(map,KEY_DISABLED);
		if(disabled) return false;
		
		Date lastDate = toDate(get(map,KEY_LAST_DATE));
		
		//v�rification de date_start : si date_start>d : stop
		
		if(has(map,KEY_DATE_START))
		{
			Date dateStart = toDate(get(map,KEY_DATE_START));
			if(dateStart.after(d)) return false;
		}
		
		//v�rification de date_end : si date_end<d : stop
		
		if(has(map,KEY_DATE_END))
		{
			Date dateEnd = toDate(get(map,KEY_DATE_END));
			if(dateEnd.before(d)) return false;
		}
		
		//v�rification duration_max : si d-last_date>duration_max : go
		
		
		if(has(map,KEY_DURATION_MAX))
		{
			long durationMax = toDuration(get(map,KEY_DURATION_MAX));
			if(lastDate==null || d.getTime()-lastDate.getTime() > durationMax) return true;
		}
		
		//v�rification duration_min : si d-last_date<duration_min : stop
		
		if(has(map,KEY_DURATION_MIN))
		{
			long durationMin = toDuration(get(map,KEY_DURATION_MIN));
			if(lastDate==null || d.getTime()-lastDate.getTime() < durationMin) return false;
		}
		
		//verification each second : si changement de seconde entre d et d0 : go
		//verification each minute : si changement de minute entre d et d0 : go
		//verification each hour : si changement d'heure entre d et d0 : go
		//verification each day : si changement de jour entre d et d0 : go
		
		if(has(map,KEY_EACH)) return each.f(new Object[]{map,d,d0});
		
		//verification months : si d n'est pas dans un mois retenu : stop
		
		if(has(map,KEY_MONTHS))
		{
			String months = (String) get(map,KEY_MONTHS);
			if(!isTargetMonth(d,months)) return false;
		}
		
		//verification month_days : si d n'est pas dans un jour retenu : stop
		
		if(has(map,KEY_MONTH_DAYS))
		{
			String days = (String) get(map,KEY_MONTH_DAYS);
			if(!isTargetMonthDay(d,days)) return false;
		}
		
		//verification week_days : si d n'est pas dans un jour retenu : stop
		
		if(has(map,KEY_WEEK_DAYS))
		{
			String days = (String) get(map,KEY_WEEK_DAYS);
			if(!isTargetWeekDay(d,days)) return false;
		}
		
		//verification hours : si d n'est pas dans une heure retenue : stop
		
		if(has(map,KEY_HOURS))
		{
			String hours = (String) get(map,KEY_HOURS);
			if(!isTargetHour(d,hours)) return false;
		}
		
		//verification minutes : si d n'est pas dans une minute retenue : stop
		
		if(has(map,KEY_MINUTES))
		{
			String minutes = (String) get(map,KEY_MINUTES);
			if(!isTargetMinute(d,minutes)) return false;
		}
		
		//verification secondes : si d n'est pas dans une seconde retenue : stop
		
		if(has(map,KEY_SECONDES))
		{
			String secondes = (String) get(map,KEY_SECONDES);
			if(!isTargetSecond(d,secondes)) return false;
		}
		
		//verification dates : si d n'est pas dans une date retenue : stop
		
		if(has(map,KEY_DATES))
		{
			String dates = (String) get(map,KEY_DATES);
			if(!isTargetDate(d,dates)) return false;
		}
		
		//verification times : si d n'est pas dans un instant retenu : stop
		
		if(has(map,KEY_TIMES))
		{
			String times = (String) get(map,KEY_TIMES);
			if(!isTargetTime(d,times)) return false;
		}
		
		//default :
		
		if(has(map,KEY_DEFAULT)) return toBoolean(get(map,KEY_DEFAULT));
		return false;
	}
	
	
	private boolean has(Map map, String key)
	{return map.containsKey(key);}
	
	private Object get(Map map, String key)
	{return has(map,key) ? map.get(key) : null;}
	
	private Date toDate(Object obj) throws Exception
	{return (Date) toDate.t(obj);}
	
	private boolean toBoolean(Object obj) throws Exception
	{return toBoolean.f(obj);}
	
	private long toDuration(Object obj) throws Exception
	{return ((Long) toDuration.t(obj)).longValue();}
	
	
	
	private boolean isTargetMonth(Date d, String value) throws Exception
	{
		if(value.trim().equals("")) return false;
		if(value.equals("*")) return true;
		
		int n1 = toInt(getMonth.t(d));
		int[] nn = toIntArray(value);
		for(int n : nn) if(n1==n) return true;
		
		return false;
	}
	
	private boolean isTargetMonthDay(Date d, String value) throws Exception
	{
		if(value.trim().equals("")) return false;
		if(value.equals("*")) return true;
		
		int n1 = toInt(getDayOfMonth.t(d));
		int[] nn = toIntArray(value);
		for(int n : nn) if(n1==n) return true;
		
		return false;
	}
	
	private boolean isTargetWeekDay(Date d, String value) throws Exception
	{
		if(value.trim().equals("")) return false;
		if(value.equals("*")) return true;
		
		int n1 = toInt(getDayOfWeek.t(d));
		int[] nn = toIntArray(value);
		for(int n : nn) if(n1==n) return true;
		
		return false;
	}
	
	private boolean isTargetHour(Date d, String value) throws Exception
	{
		if(value.trim().equals("")) return false;
		if(value.equals("*")) return true;
		
		int n1 = toInt(getHour.t(d));
		int[] nn = toIntArray(value);
		for(int n : nn) if(n1==n) return true;
		
		return false;
	}
	
	private boolean isTargetMinute(Date d, String value) throws Exception
	{
		if(value.trim().equals("")) return false;
		if(value.equals("*")) return true;
		
		int n1 = toInt(getMinute.t(d));
		int[] nn = toIntArray(value);
		for(int n : nn) if(n1==n) return true;
		
		return false;
	}
	
	private boolean isTargetSecond(Date d, String value) throws Exception
	{
		if(value.trim().equals("")) return false;
		if(value.equals("*")) return true;
		
		int n1 = toInt(getSecond.t(d));
		int[] nn = toIntArray(value);
		for(int n : nn) if(n1==n) return true;
		
		return false;
	}
	
	private boolean isTargetDate(Date d, String value) throws Exception
	{
		if(value.trim().equals("")) return false;
		if(value.equals("*")) return true;
		
		String s1 = yyyyMMdd.format(d);
		String s2 = s1.substring(4,8);
		
		String[] nn = value.split(" ");
		for(String n : nn) if(n.equals(s1) || n.equals(s2)) return true;
		
		return false;
	}
	
	private boolean isTargetTime(Date d, String value) throws Exception
	{
		if(value.trim().equals("")) return false;
		if(value.equals("*")) return true;
		
		String s1 = HHmmss.format(d);
		String s2 = s1.substring(0,4);
		
		String[] nn = value.split(" ");
		for(String n : nn) if(n.equals(s1) || n.equals(s2)) return true;
		
		return false;
	}
	
	
	
	private int toInt(Object value)
	{return ((Integer) value).intValue();}
	
	
	
	private int[] toIntArray(String value)
	{
		String[] nn = value.split(" ");
		int len = nn.length;
		int[] rr = new int[len];
		for(int i=0;i<len;i++) rr[i] = Integer.parseInt(nn[i]);
		return rr;
	}
	
	
	private boolean boolDF(Map map, String key)
	{
		if(!map.containsKey(key)) return false;
		String v = (String) map.get(key);
		return Boolean.parseBoolean(v);
	}
}
