package a.entity.gus06.file.read.mail;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160609";}


	private Service emlToMessage;


	public EntityImpl() throws Exception
	{
		emlToMessage = Outside.service(this,"gus06.mail.message.emlfile.tomessage");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return emlToMessage.t(obj);
	}
}
