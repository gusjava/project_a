package a.entity.gus06.sys.mailclient1.tool.folder.isout;

import a.framework.*;
import javax.mail.Folder;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201117";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Folder folder = (Folder) obj;
		if(folder==null) return false;
		String name = folder.getName().toLowerCase();
		
		return name.equals("sent") || name.equals("outbox");
	}
}