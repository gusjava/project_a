package a.entity.gus06.find.filelist;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150530";}
	
	
	public Object t(Object obj) throws Exception
	{return toList(obj);}
	
	
	
	
	private List toList(Object obj) throws Exception
	{
		if(obj instanceof List) return (List) obj;
		if(obj instanceof Set) return new ArrayList((Set) obj);
		if(obj instanceof Object[]) return toList((Object[]) obj);
		if(obj instanceof File[]) return toList((File[]) obj);
		if(obj instanceof File) return toList((File) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private List toList(Object[] ff)
	{
		List list = new ArrayList();
		for(Object f:ff) list.add(f);
		return list;
	}
	
	private List toList(File[] ff)
	{
		List list = new ArrayList();
		for(File f:ff) list.add(f);
		return list;
	}
	
	private List toList(File f)
	{
		List list = new ArrayList();
		list.add(f);
		return list;
	}
}