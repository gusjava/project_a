package a.entity.gus06.sys.expression1.apply.op._infos;

import a.framework.*;
import java.util.Date;
import java.util.TimeZone;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170413";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof TimeZone) return infos((TimeZone) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Map infos(TimeZone tz)
	{
		Map map = new HashMap();
		
		map.put("display_name",			tz.getDisplayName());
		map.put("id",				tz.getID());
		map.put("dst_savings",			Integer.valueOf(tz.getDSTSavings()));
		map.put("raw_offset",			Integer.valueOf(tz.getRawOffset()));
		map.put("observes_daylighttime",	Boolean.valueOf(tz.observesDaylightTime()));
		map.put("use_daylighttime",		Boolean.valueOf(tz.useDaylightTime()));
		
		return map;
	}
}
