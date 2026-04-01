package a.entity.gus06.app.entity.entrytoname;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140903";}

	public Object t(Object obj) throws Exception
	{
		String entry = (String) obj;
		if(!entry.startsWith("gus06/entity/") || entry.equals("gus06/entity/"))
			throw new Exception("Invalid entity entry name: "+entry);
		
		String[] n = entry.split("/");
		String fileName = n[n.length-1];
		
		if(!fileName.endsWith(".java") && !fileName.endsWith(".class"))
			throw new Exception("Invalid entity entry name: "+entry);
		
		entry = entry.substring(13,entry.length()-fileName.length()-1);
		return entry.replace("/",".");
	}
}