package a.entity.gus06.time.duration.parser.min;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170814";}

	public static final long DAY = 24*60;
	public static final long HOUR = 60;
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s==null) return null;
		s = s.trim().replace(" ","").toLowerCase();
		if(s.equals("")) return null;
		
		long duration = parsePart(s);
		return Long.valueOf(duration);
	}
	
	
	
	
	private long parsePart(String s)
	{
		if(s.equals("")) return 0L;
		
		String[] jj = s.split("j",2);
		if(jj.length==2) return parseDays(jj);
		
		String[] hh = s.split("h",2);
		if(hh.length==2) return parseHours(hh);
		
		String[] mm = s.split("m",2);
		if(mm.length==2) return parseMinutes(mm);
		
		return Long.valueOf(s);
	}
	
	
	
	private long parseDays(String[] tt)
	{
		long d = long_(tt[0])*DAY;
		if(isLong(tt[1])) return d + long_(tt[1])*HOUR;
		return d + parsePart(tt[1]);
	}
	
	private long parseHours(String[] tt)
	{
		long d = long_(tt[0])*HOUR;
		if(isLong(tt[1])) return d + long_(tt[1]);
		return d + parsePart(tt[1]);
	}
	
	private long parseMinutes(String[] tt)
	{
		return long_(tt[0]);
	}
	
	
	
	private long long_(String s)
	{return Long.parseLong(s);}
	
	
	private boolean isLong(String s)
	{
		try
		{
			Long.parseLong(s);
			return true;
		}
		catch(NumberFormatException e){}
		return false;
	}
}