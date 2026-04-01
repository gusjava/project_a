package a.entity.gus06.sys.countdown.duration.format;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201216";}
	
	public static final String UNIT_MIN = "min";
	public static final String UNIT_S = "s";
	public static final String UNIT_MS = "ms";


	private Service formatToMs;
	private Service formatToSec;
	private Service formatToMin;


	public EntityImpl() throws Exception
	{
		formatToMs = Outside.service(this,"gus06.string.transform.format.duration.ms.en");
		formatToSec = Outside.service(this,"gus06.string.transform.format.duration.s.en");
		formatToMin = Outside.service(this,"gus06.string.transform.format.duration.min.en");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Long data = (Long) o[0];
		String unit = (String) o[1];
		
		if(data==null) return " ";
		if(data<=0) return " ";
		
		if(unit.equals(UNIT_MS)) return (String) formatToMs.t(data);
		if(unit.equals(UNIT_S)) return (String) formatToSec.t(msToSec(data));
		if(unit.equals(UNIT_MIN)) return (String) formatToMin.t(msToMin(data));
		
		throw new Exception("Unsupported time unit: "+unit);
	}
	
	
	private long msToSec(long data)
	{
		return (data/1000)+1;
	}
	
	private long msToMin(long data)
	{
		return (data/60000)+1;
	}
}