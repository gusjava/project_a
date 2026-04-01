package a.entity.gus06.sys.expression1.apply.op._tolist_limit;

import a.framework.*;
import java.util.Set;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collection;
import java.util.List;
import java.io.File;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191022";}
	
	
	private Service read;
	private Service stringToList;
	private Service mapToList;
	private Service itToList;
	private Service gToList;
	private Service find;
	
	
	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.string.list.autodetect");
		stringToList = Outside.service(this,"gus06.list.build.from.chars");
		mapToList = Outside.service(this,"gus06.map.build.list.keys");
		itToList = Outside.service(this,"gus06.list.build.from.iterator.limit");
		gToList = Outside.service(this,"gus06.list.build.from.g.limit");
		find = Outside.service(this,"gus06.find.list");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof List)		return new T0((List) obj);
		
		if(obj instanceof String)	return new T1(stringToList,obj);
		if(obj instanceof Set)		return new T1(find,obj);
		if(obj instanceof Set)		return new T1(find,obj);
		if(obj instanceof Map)		return new T1(mapToList,obj);
		if(obj instanceof File)		return new T1(read,obj);
		
		if(obj instanceof Iterator)	return new T2(itToList,obj);
		if(obj instanceof G)		return new T2(gToList,obj);
		
		if(obj instanceof Object[][])	return new T1(find,obj);
		if(obj instanceof int[][])	return new T1(find,obj);
		if(obj instanceof short[][])	return new T1(find,obj);
		if(obj instanceof long[][])	return new T1(find,obj);
		if(obj instanceof double[][])	return new T1(find,obj);
		if(obj instanceof boolean[][])	return new T1(find,obj);
		if(obj instanceof char[][])	return new T1(find,obj);
		if(obj instanceof byte[][])	return new T1(find,obj);
		
		if(obj instanceof Object[])	return new T1(find,obj);
		if(obj instanceof int[])	return new T1(find,obj);
		if(obj instanceof short[])	return new T1(find,obj);
		if(obj instanceof long[])	return new T1(find,obj);
		if(obj instanceof double[])	return new T1(find,obj);
		if(obj instanceof boolean[])	return new T1(find,obj);
		if(obj instanceof char[])	return new T1(find,obj);
		if(obj instanceof byte[])	return new T1(find,obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	
	
	private class T0 implements T
	{
		private List input;
		
		public T0(List input)
		{this.input = input;}
		
		public Object t(Object obj) throws Exception
		{
			int limit = toInt(obj);
			if(limit<0) throw new Exception("Invalid limit: "+limit);
			truncate(input,limit);
			return input;
		}
	}
	
	
	
	private class T1 implements T
	{
		private T listBuilder;
		private Object input;
		
		public T1(T listBuilder, Object input)
		{
			this.listBuilder = listBuilder;
			this.input = input;
		}
		
		public Object t(Object obj) throws Exception
		{
			int limit = toInt(obj);
			if(limit<0) throw new Exception("Invalid limit: "+limit);
			
			List list = (List) listBuilder.t(input);
			truncate(list,limit);
			return list;
		}
	}
	
	
	
	private class T2 implements T
	{
		private T listBuilder;
		private Object input;
		
		public T2(T listBuilder, Object input)
		{
			this.listBuilder = listBuilder;
			this.input = input;
		}
		
		public Object t(Object obj) throws Exception
		{
			int limit = toInt(obj);
			if(limit<0) throw new Exception("Invalid limit: "+limit);
			return listBuilder.t(new Object[]{input,limit});
		}
	}
	
	
	
	
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	
	
	private void truncate(List list, int limit)
	{
		while(list.size()>limit)
		list.remove(list.size()-1);
	}
}
