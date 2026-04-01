package a.entity.gus06.convert.ttomap;

import a.framework.*;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160301";}


	public Object t(Object obj) throws Exception
	{return new Map1((T) obj);}
	
	
	private class Map1 extends HashMap
	{
		private T t;
		public Map1(T t) {this.t = t;}
		
		public Object get(Object key)
		{return get_(t,key);}
	}
	
	
	private Object get_(T t, Object key)
	{
		try{return t.t(key);}
		catch(Exception e)
		{Outside.err(this,"get_(R,Object)",e);}
		return null;
	}
}
