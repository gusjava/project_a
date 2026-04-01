package a.entity.gus06.appli.gusappmonitor.tool.warnpopup;

import a.framework.*;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190529";}

	public static final String KEY_MESSAGE = "message";
	public static final String KEY_MESSAGE_SIZE = "message_size";
	public static final String KEY_MESSAGE_FOREGROUND = "message_foreground";
	
	

	private Service warnPopup;
	
	public EntityImpl() throws Exception
	{
		warnPopup = Outside.service(this,"gus06.sys.popup1.manager");
	}
	
	
	public void p(Object obj) throws Exception
	{
		String buildId = (String) obj;
		
		Map map = new HashMap();
		map.put(KEY_MESSAGE,"Application lost: "+buildId);
		map.put(KEY_MESSAGE_SIZE,16);
		map.put(KEY_MESSAGE_FOREGROUND,Color.RED);
		
		warnPopup.p(map);
	}
}
