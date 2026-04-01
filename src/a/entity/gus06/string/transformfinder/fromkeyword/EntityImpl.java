package a.entity.gus06.string.transformfinder.fromkeyword;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150926";}


	private Service lib;
	private Service setToString;



	public EntityImpl() throws Exception
	{
		lib = Outside.service(this,"gus06.string.transformfinder.lib");
		setToString = Outside.service(this,"gus06.tostring.set");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("lib")) return new LibTransform();
		
		Object o = lib.t(s);
		if(o!=null) return o;
		
		throw new Exception("String transform not found for keyword: "+s);
	}
	
	
	
	private class LibTransform implements T
	{
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			if(s.equals("keywords")) return keywords();
			throw new Exception("Invalid command: "+s);
		}
	}
	
	
	
	
	private String keywords() throws Exception
	{
		Map map = (Map) lib.g();
		Set set = map.keySet();
		return (String) setToString.t(set);
	}
}
