package a.entity.gus06.data.perform.infertype;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170129";}


	private Service fromString;
	private Service fromArray;

	public EntityImpl() throws Exception
	{
		fromString = Outside.service(this,"gus06.data.perform.infertype.fromstring");
		fromArray = Outside.service(this,"gus06.data.perform.infertype.fromarray");
	}

	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Number) return inferFromString(""+obj);
		if(obj instanceof String) return inferFromString(""+obj);
		if(obj instanceof Object[]) return inferFromArray((Object[]) obj);
		if(obj instanceof List) return inferFromArray(toArray((List) obj));
		return obj;
	}
	
	private Object inferFromString(String s) throws Exception
	{return fromString.t(s);}
	
	private Object inferFromArray(Object[] t) throws Exception
	{return fromArray.t(t);}
	
	
	private Object[] toArray(List list)
	{
		Object[] array = new Object[list.size()];
		return list.toArray(array);
	}
}
