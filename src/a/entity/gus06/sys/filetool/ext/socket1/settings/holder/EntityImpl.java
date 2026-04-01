package a.entity.gus06.sys.filetool.ext.socket1.settings.holder;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250402";}
	
	
	private Service tab;
	private Service guiServer;
	private Service guiClient;
	
	private Map map;
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		guiServer = Outside.service(this,"*gus06.sys.filetool.ext.socket1.settings.holder.server");
		guiClient = Outside.service(this,"*gus06.sys.filetool.ext.socket1.settings.holder.client");
		
		tab.v("Server",guiServer.i());
		tab.v("Client",guiClient.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		guiServer.p(map);
	}
}