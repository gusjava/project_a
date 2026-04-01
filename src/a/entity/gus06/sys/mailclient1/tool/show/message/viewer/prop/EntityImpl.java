package a.entity.gus06.sys.mailclient1.tool.show.message.viewer.prop;

import a.framework.*;
import javax.mail.Message;
import java.util.Map;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20240317";}


	private Service messageToProp;
	private Service viewer;
	
	private Message message;
	private Map prop;
	
	public EntityImpl() throws Exception
	{
		messageToProp = Outside.service(this,"gus06.mail.retrieve.message.toprop");
		viewer = Outside.service(this,"*gus06.data.viewer.map.stringmap");
	}
	
	
	public Object i() throws Exception
	{return viewer.i();}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null){resetGui();return;}
		message = (Message) obj;
		prop = (Map) messageToProp.t(message);
		viewer.p(prop);
	}
	
	private void resetGui() throws Exception
	{
		message = null;
		prop = null;
		viewer.p(null);
	}
}