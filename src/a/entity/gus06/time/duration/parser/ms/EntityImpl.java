package a.entity.gus06.time.duration.parser.ms;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201216";}
	
	
	public static final long DAY = 24*60*60*1000;
	public static final long HOUR = 60*60*1000;
	public static final long MIN = 60*1000;
	public static final long SEC = 1000;
	

	
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
		if(mm.length==2 && !mm[1].startsWith("s")) return parseMinutes(mm);
		
		String[] ss = s.split("s",2);
		if(ss.length==2 && !mm[0].endsWith("m")) return parseSeconds(ss);
		
		String[] zz = s.split("ms",2);
		if(zz.length==2) return parseMilliseconds(zz);
		
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
		if(isLong(tt[1])) return d + long_(tt[1])*MIN;
		return d + parsePart(tt[1]);
	}
	
	private long parseMinutes(String[] tt)
	{
		long d = long_(tt[0])*MIN;
		if(isLong(tt[1])) return d + long_(tt[1])*SEC;
		return d + parsePart(tt[1]);
	}
	
	private long parseSeconds(String[] tt)
	{
		long d = long_(tt[0])*SEC;
		if(isLong(tt[1])) return d + long_(tt[1]);
		return d + parsePart(tt[1]);
	}
	
	private long parseMilliseconds(String[] tt)
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