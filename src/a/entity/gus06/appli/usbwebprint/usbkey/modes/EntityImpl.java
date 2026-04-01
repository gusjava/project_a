package a.entity.gus06.appli.usbwebprint.usbkey.modes;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;


public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140910";}

	public static final String ROOTNAME = "gus.appli.usbwebprint.usbkey.modes";

	private Map modes;


	public EntityImpl() throws Exception
	{
		modes = new HashMap();
		initMode("aloka");
	}
	
	
	public Object g() throws Exception
	{return modes;}
	
	
	
	private void initMode(String mode) throws Exception
	{
		Service s = Outside.service(this,ROOTNAME+"."+mode);
		modes.put(mode,s);
	}
}
