package a.entity.gus06.sys.parser3.resolver1;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151027";}
	
	
	private Service resolveTag;
	private Service resolveList;
	
	public EntityImpl() throws Exception
	{
		resolveTag = Outside.service(this,"gus06.sys.parser3.resolver1.tag");
		resolveList = Outside.service(this,"gus06.sys.parser3.resolver1.list");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		T external = (T) obj;
		return new T1(external);
	}
	
	
	private class T1 implements T, G
	{
		private T external;
		public T1(T external) {this.external = external;}
		
		public Object g() throws Exception
		{return external;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof List)
				return resolveList((List) obj,this);
			if(obj instanceof Map)
				return resolveTag.t(new Object[]{obj,this});
			
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
	
	
	
	private Object resolveList(List l, T t) throws Exception
	{
		if(l.size()==0)		throw new Exception("Invalid empty list");
		if(l.size()==1)		return resolveTag.t(new Object[]{l.get(0),t});
		
		return resolveList.t(new Object[]{l,t});
	}
}
