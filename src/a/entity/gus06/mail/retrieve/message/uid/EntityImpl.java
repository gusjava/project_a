package a.entity.gus06.mail.retrieve.message.uid;

import a.framework.*;
import javax.mail.Message;
import javax.mail.UIDFolder;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240324";}


	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		Message message = (Message) obj;
		UIDFolder uf = (UIDFolder) message.getFolder();
		return Long.valueOf(uf.getUID(message));
	}
}