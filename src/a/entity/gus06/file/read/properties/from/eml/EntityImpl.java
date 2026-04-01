package a.entity.gus06.file.read.properties.from.eml;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151015";}


	private Service emlToMessage;
	private Service messageToProp;


	public EntityImpl() throws Exception
	{
		emlToMessage = Outside.service(this,"gus06.mail.message.emlfile.tomessage");
		messageToProp = Outside.service(this,"gus06.mail.retrieve.message.toprop");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		obj = emlToMessage.t(obj);
		return messageToProp.t(obj);
	}
}
