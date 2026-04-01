package a.entity.gus06.sys.parser3.resolver1.tag.group2;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}
	
	public static final String TYPE = "type";
	public static final String VALUE = "value";
	public static final String TYPE_SYMBOL = "symbol";


	private Service cutMethod;
	private Service handleList;
	private Service handleArray;
	private Service buildList;

	public EntityImpl() throws Exception
	{
		cutMethod = Outside.service(this,"gus06.sys.parser3.cut.symbol.b1");
		handleList = Outside.service(this,"gus06.sys.parser3.resolver1.tag.group2.list");
		handleArray = Outside.service(this,"gus06.sys.parser3.resolver1.tag.group2.array");
		buildList = Outside.service(this,"gus06.list.factory.silentlist");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List l = (List) o[0];
		T t = (T) o[1];
		
		if(l.isEmpty()) return buildList.g();
		
		if(l.size()==2)
		{
			Map m = (Map) l.get(1);
			if(type(m).equals(TYPE_SYMBOL) && value(m).equals(":"))
			return handleArray.t(new Object[]{l.subList(0,1),new ArrayList(),t});
		}
		
		List cut = (List) cutMethod.t(new Object[]{l,":"});
		
		if(cut==null)
		{
			return handleList.t(new Object[]{l,t});
		}
		else
		{
			return handleArray.t(new Object[]{cut.get(0),cut.get(1),t});
		}
	}
	
	
	
	
	private Object value(Map m)
	{return m.get(VALUE);}
	
	private String type(Map m)
	{return (String) m.get(TYPE);}
}
