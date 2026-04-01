package a.entity.gus06.data.size.computesize;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170208";}

	
	
	public Object t(Object obj) throws Exception
	{
		return Long.valueOf(handleObj(obj));
	}
	
	
	private long handleObj(Object obj) throws Exception
	{
		if(obj instanceof List) return handleList((List) obj);
		
		if(obj instanceof Object[]) return handleArray((Object[]) obj);
		if(obj instanceof byte[]) return handleByteArray((byte[]) obj);
		
		if(obj instanceof String) return handleString((String) obj);
		if(obj instanceof StringBuffer) return handleString(((StringBuffer) obj).toString());
		if(obj instanceof StringBuilder) return handleString(((StringBuilder) obj).toString());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private long handleList(List list) throws Exception
	{
		long d = 0;
		for(Object obj:list) d += handleObj(obj);
		return d;
	}
	
	private long handleArray(Object[] array) throws Exception
	{
		long d = 0;
		for(Object obj:array) d += handleObj(obj);
		return d;
	}
	
	private long handleString(String s)
	{
		return s.length();
	}
	
	private long handleByteArray(byte[] ba)
	{
		return ba.length;
	}
}
