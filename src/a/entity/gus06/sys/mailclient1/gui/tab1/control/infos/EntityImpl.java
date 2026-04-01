package a.entity.gus06.sys.mailclient1.gui.tab1.control.infos;

import a.framework.*;
import javax.mail.Message;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240314";}


	private Service showMessage;
	
	public EntityImpl() throws Exception
	{
		showMessage = Outside.service(this,"gus06.sys.mailclient1.tool.show.message");
	}
	
	public void p(Object obj) throws Exception
	{
		R holder = (R) obj;
		Message message = (Message) holder.r("message");
		showMessage.p(message);
	}
}