package a.entity.gus06.data.perform.valueset;

import a.framework.*;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170327";}


	private Service performFile;
	
	public EntityImpl() throws Exception
	{
		performFile = Outside.service(this,"gus06.file.read.properties.valueset");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof Map) return perform((Map) obj);
		if(obj instanceof Map[]) return perform((Map[]) obj);
		
		if(obj instanceof File) return perform((File) obj);
		if(obj instanceof File[]) return perform((File[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private Set perform(Map m)
	{
		return new HashSet(m.values());
	}
	
	private Set perform(Map[] mm)
	{
		Set set = new HashSet();
		for(Map m:mm) set.addAll(m.values());
		return set;
	}
	
	private Set perform(File f) throws Exception
	{
		return (Set) performFile.t(f);
	}
	
	private Set perform(File[] ff) throws Exception
	{
		Set set = new HashSet();
		for(File f:ff) set.addAll(perform(f));
		return set;
	}
}
