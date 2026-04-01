package a.entity.gus06.sys.countdown.duration.parse;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201216";}
	
	public static final String UNIT_MIN = "min";
	public static final String UNIT_S = "s";
	public static final String UNIT_MS = "ms";


	private Service parseToMin;
	private Service parseToSec;
	private Service parseToMs;
	
	public EntityImpl() throws Exception
	{
		parseToMin = Outside.service(this,"gus06.time.duration.parser.min");
		parseToSec = Outside.service(this,"gus06.time.duration.parser.s");
		parseToMs = Outside.service(this,"gus06.time.duration.parser.ms");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		String unit = (String) o[1];
		
		if(data==null) return null;
		if(data instanceof Integer) return intToDuration((Integer) data,unit);
		if(data instanceof Long) return longToDuration((Long) data,unit);
		if(data instanceof String) return stringToDuration((String) data,unit);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	
	private long intToDuration(Integer data, String unit) throws Exception
	{
		if(unit.equals(UNIT_MIN)) return data.longValue()*60*1000;
		if(unit.equals(UNIT_S)) return data.longValue()*1000;
		if(unit.equals(UNIT_MS)) return data.longValue();
		
		throw new Exception("Unsupported time unit: "+unit);
	}
	
	
	private long longToDuration(Long data, String unit) throws Exception
	{
		if(unit.equals(UNIT_MIN)) return data.longValue()*60*1000;
		if(unit.equals(UNIT_S)) return data.longValue()*1000;
		if(unit.equals(UNIT_MS)) return data.longValue();
		
		throw new Exception("Unsupported time unit: "+unit);
	}
	
	
	private long stringToDuration(String data, String unit) throws Exception
	{
		if(unit.equals(UNIT_MIN))
		{
			Long d = (Long) parseToMin.t(data);
			return d!=null ? d*60*1000 : -1;
		}
		if(unit.equals(UNIT_S))
		{
			Long d = (Long) parseToSec.t(data);
			return d!=null ? d*1000 : -1;
		}
		if(unit.equals(UNIT_MS))
		{
			Long d = (Long) parseToMs.t(data);
			return d!=null ? d : -1;
		}
		
		throw new Exception("Unsupported time unit: "+unit);
	}
}