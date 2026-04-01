package a.entity.gus06.sys.mailclient1.tool.message.isout;

import a.framework.*;
import javax.mail.Message;
import javax.mail.Folder;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201117";}

	private Service isFolderOut;

	public EntityImpl() throws Exception
	{
		isFolderOut = Outside.service(this,"gus06.sys.mailclient1.tool.folder.isout");
	}
	
	public boolean f(Object obj) throws Exception
	{
		Message message = (Message) obj;
		return isFolderOut.f(message.getFolder());
	}
}
