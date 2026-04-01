package a.entity.gus06.file.name.mimetype.hierarchy;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180410";}

	private Service extToMimeHier;

	public EntityImpl() throws Exception
	{extToMimeHier = Outside.service(this,"gus06.file.ext.mimetype.hierarchy");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String ext = getExtension((String) obj);
		return extToMimeHier.t(ext);
	}
	
	
	private String getExtension(String name)
	{
		if(!name.contains(".")) return null;
		
		String[] n = name.split("\\."); 
		return n[n.length-1];
	}
}
