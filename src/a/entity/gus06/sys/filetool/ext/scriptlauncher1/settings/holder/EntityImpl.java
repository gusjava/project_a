package a.entity.gus06.sys.filetool.ext.scriptlauncher1.settings.holder;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20220608";}
	
	
	private Service tab;
	private Service gui1;
	
	private Map map;
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		gui1 = Outside.service(this,"*gus06.sys.filetool.ext.scriptlauncher1.settings.holder.gui1");
		
		tab.v("Init Script",gui1.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		gui1.p(map);
	}
}