package a.entity.gus06.sys.jsonparser1.resolver;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151125";}

	
	private Service resolveTag;
	private Service resolveTag2;
	
	public EntityImpl() throws Exception
	{
		resolveTag = Outside.service(this,"gus06.sys.jsonparser1.resolver.tag");
		resolveTag2 = Outside.service(this,"gus06.sys.jsonparser1.resolver.tag2");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List)
			return resolveList((List) obj);
		if(obj instanceof Map)
			return resolveTag((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Object resolveList(List l) throws Exception
	{
		if(l.size()==0)		return null;
		if(l.size()==1)		return resolveTag((Map) l.get(0));
		if(l.size()==2)		return resolveTag((Map) l.get(0), (Map) l.get(1));
		
		throw new Exception("Failed to resolve list [invalid size="+l.size()+", expected=0,1 or 2]");
	}
	
	
	private Object resolveTag(Map tag) throws Exception
	{return resolveTag.t(new Object[]{tag,this});}
	
	
	private Object resolveTag(Map tag1, Map tag2) throws Exception
	{return resolveTag2.t(new Object[]{tag1,tag2,this});}
}