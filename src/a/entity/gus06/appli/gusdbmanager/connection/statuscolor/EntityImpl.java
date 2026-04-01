package a.entity.gus06.appli.gusdbmanager.connection.statuscolor;

import java.awt.Color;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150613";}
	
	public static final Color COLOR_CONNECTED = Color.BLUE;
	public static final Color COLOR_CONNECTING = Color.ORANGE;
	public static final Color COLOR_DISCONNECTED = Color.BLACK;
	public static final Color COLOR_FAILED = Color.RED;
	

	public Object t(Object obj) throws Exception
	{
		String status = (String) obj;
		
		if(status.equals(CX_STATUS.STATUS_CONNECTED)) return COLOR_CONNECTED;
		if(status.equals(CX_STATUS.STATUS_CONNECTING)) return COLOR_CONNECTING;
		if(status.equals(CX_STATUS.STATUS_DISCONNECTED)) return COLOR_DISCONNECTED;
		if(status.equals(CX_STATUS.STATUS_FAILED)) return COLOR_FAILED;
		
		throw new Exception("Unknown status: "+status);
	}
}
