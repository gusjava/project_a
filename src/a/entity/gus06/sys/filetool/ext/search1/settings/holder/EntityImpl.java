package a.entity.gus06.sys.filetool.ext.search1.settings.holder;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20220501";}
	
	
	private Service tab;
	private Service gui1;
	private Service gui2;
	private Service gui3;
	
	private Map map;
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		gui1 = Outside.service(this,"*gus06.sys.filetool.ext.search1.settings.holder.gui1");
		gui2 = Outside.service(this,"*gus06.sys.filetool.ext.search1.settings.holder.gui2");
		gui3 = Outside.service(this,"*gus06.sys.filetool.ext.search1.settings.holder.gui3");
		
		tab.v("Terms",gui1.i());
		tab.v("Filter",gui2.i());
		tab.v("Roots",gui3.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		gui1.p(map);
		gui2.p(map);
		gui3.p(map);
	}
}