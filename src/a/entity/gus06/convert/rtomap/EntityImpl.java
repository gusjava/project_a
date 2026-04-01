package a.entity.gus06.convert.rtomap;

import a.framework.*;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160301";}


	public Object t(Object obj) throws Exception
	{return new Map1((R) obj);}
	
	
	private class Map1 extends HashMap
	{
		private R r;
		public Map1(R r) {this.r = r;}
		
		public Object get(Object key)
		{return get_(r,""+key);}
	}
	
	
	private Object get_(R r, String key)
	{
		try{return r.r(key);}
		catch(Exception e)
		{Outside.err(this,"get_(R,String)",e);}
		return null;
	}
}
