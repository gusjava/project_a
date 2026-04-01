package a.entity.gus06.file.name.mimetype;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180410";}

	private Service extToMime;

	public EntityImpl() throws Exception
	{extToMime = Outside.service(this,"gus06.file.ext.mimetype");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String ext = getExtension((String) obj);
		return extToMime.t(ext);
	}
	
	
	private String getExtension(String name)
	{
		if(!name.contains(".")) return null;
		
		String[] n = name.split("\\."); 
		return n[n.length-1];
	}
}
