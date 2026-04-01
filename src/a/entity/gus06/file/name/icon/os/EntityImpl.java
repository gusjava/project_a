package a.entity.gus06.file.name.icon.os;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140807";}

	private Service extToIcon;

	public EntityImpl() throws Exception
	{extToIcon = Outside.service(this,"gus06.file.ext.icon.os");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String ext = getExtension((String) obj);
		return extToIcon.t(ext);
	}
	
	
	private String getExtension(String name)
	{
		if(name.endsWith("\\") || name.endsWith("/")) return "#";
		if(!name.contains(".")) return null;
		
		String[] n = name.split("\\.");
		if(n.length==0) return null;
		return n[n.length-1];
	}
}