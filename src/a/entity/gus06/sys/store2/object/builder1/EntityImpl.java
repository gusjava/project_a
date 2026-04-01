package a.entity.gus06.sys.store2.object.builder1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191125";}


	private Service builder2;

	public EntityImpl() throws Exception
	{
		builder2 = Outside.service(this,"gus06.sys.store2.object.builder2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mapAccess = (R) o[0];
		R factory = (R) o[1];
		
		return new Builder(mapAccess,factory);
	}
	
	
	
	private class Builder implements R, T, V
	{
		private R mapAccess; 
		private R factory; 
		
		public Builder(R mapAccess, R factory)
		{
			this.mapAccess = mapAccess;
			this.factory = factory;
		}
		
		public Object r(String key) throws Exception
		{
			Map map = (Map) mapAccess.r(key);
			return builder2.t(new Object[]{map,factory});
		}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof Map) return builder2.t(new Object[]{obj,factory});
			if(obj instanceof String) return r((String) obj);
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("mapAccess")) {mapAccess = (R) obj;return;}
			if(key.equals("factory")) {factory = (R) obj;return;}
			
			throw new Exception("Unknown key: "+key);
		}
	}
}
