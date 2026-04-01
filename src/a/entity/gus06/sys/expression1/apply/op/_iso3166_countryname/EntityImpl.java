package a.entity.gus06.sys.expression1.apply.op._iso3166_countryname;

import a.framework.*;
import java.util.Locale;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160421";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return new T1((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class T1 implements T
	{
		private Locale l;
		
		public T1(String code)
		{this.l = new Locale("",code.toUpperCase());}
		
		public Object t(Object obj) throws Exception
		{
			Locale ling = new Locale((String) obj);
			return l.getDisplayCountry(ling);
		}
	}
}
