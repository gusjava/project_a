package a.entity.gus06.mail.retrieve.message.date.received;

import a.framework.*;
import javax.mail.Message;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240324";}


	public Object t(Object obj) throws Exception
	{
		Message message = (Message) obj;
		return message.getReceivedDate();
	}
}