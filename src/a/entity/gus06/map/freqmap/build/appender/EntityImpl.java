package a.entity.gus06.map.freqmap.build.appender;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151014";}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Handler((Map) obj);
	}
	
	
	private class Handler implements P
	{
		private Map map;
		
		public Handler(Map map)
		{this.map = map;}
		
		public void p(Object obj) throws Exception
		{
			if(!map.containsKey(obj))
			{map.put(obj,Integer.valueOf(1));return;}
			
			Integer n = (Integer) map.get(obj);
			map.put(obj,Integer.valueOf(n.intValue()+1));
		}
	}
}
