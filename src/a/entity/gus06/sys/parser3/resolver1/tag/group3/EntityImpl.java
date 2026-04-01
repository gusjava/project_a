package a.entity.gus06.sys.parser3.resolver1.tag.group3;

import a.framework.*;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}
	
	public static final String TYPE = "type";
	public static final String VALUE = "value";
	public static final String TYPE_SYMBOL = "symbol";


	private Service cutMethod;
	private Service handleSet;
	private Service handleMap;
	private Service buildSet;
	private Service buildMap;

	public EntityImpl() throws Exception
	{
		cutMethod = Outside.service(this,"gus06.sys.parser3.cut.symbol.a1");
		handleSet = Outside.service(this,"gus06.sys.parser3.resolver1.tag.group3.set");
		handleMap = Outside.service(this,"gus06.sys.parser3.resolver1.tag.group3.map");
		buildSet = Outside.service(this,"gus06.set.factory.silentset");
		buildMap = Outside.service(this,"gus06.map.factory.silentmap");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List l = (List) o[0];
		T t = (T) o[1];
		
		if(l.isEmpty()) return buildSet.g();
		
		if(l.size()==1)
		{
			Map m = (Map) l.get(0);
			if(type(m).equals(TYPE_SYMBOL) && value(m).equals(":"))
			return buildMap.g();
		}
		
		List cut = (List) cutMethod.t(new Object[]{l,":"});
		
		if(cut==null)
		{
			return handleSet.t(new Object[]{l,t});
		}
		else
		{
			return handleMap.t(new Object[]{l,t});
		}
	}
	
	
	
	
	private Object value(Map m)
	{return m.get(VALUE);}
	
	private String type(Map m)
	{return (String) m.get(TYPE);}
}
