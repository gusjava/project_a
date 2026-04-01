package a.entity.gus06.appli.gusdbmanager.connection.statusdisplay;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150613";}

	public static final String DISPLAY_CONNECTED = "SERVER_colorblue#connected";
	public static final String DISPLAY_CONNECTING = "SERVER_colororange#connecting";
	public static final String DISPLAY_DISCONNECTED = "SERVER_colorblack#disconnected";
	public static final String DISPLAY_FAILED = "SERVER_colorred#failed";
	
	
	public Object t(Object obj) throws Exception
	{
		String status = (String) obj;
		
		if(status.equals(CX_STATUS.STATUS_CONNECTED)) return DISPLAY_CONNECTED;
		if(status.equals(CX_STATUS.STATUS_CONNECTING)) return DISPLAY_CONNECTING;
		if(status.equals(CX_STATUS.STATUS_DISCONNECTED)) return DISPLAY_DISCONNECTED;
		if(status.equals(CX_STATUS.STATUS_FAILED)) return DISPLAY_FAILED;
		
		throw new Exception("Unknown status: "+status);
	}
}
