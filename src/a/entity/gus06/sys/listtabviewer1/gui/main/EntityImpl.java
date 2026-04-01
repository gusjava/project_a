package a.entity.gus06.sys.listtabviewer1.gui.main;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, I, P, G, V {

	public String creationDate() {return "20200405";}


	
	private Service tabbedpane;
	private Service buildTabDisplay;
	private Service buildTabHolder;
	
	private List tabs;
	private List data;
	

	public EntityImpl() throws Exception
	{
		tabbedpane = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		buildTabDisplay = Outside.service(this,"gus06.sys.listtabviewer1.build.tabdisplay");
		buildTabHolder = Outside.service(this,"gus06.sys.listtabviewer1.build.tabholder");
		
		tabs = new ArrayList();
	}
	
	
	public Object i() throws Exception
	{return tabbedpane.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		data = (List) obj;
		
		for(int i=0;i<tabs.size();i++)
		{
			try
			{
				P tab = (P) tabs.get(i);
				tab.p(data);
			}
			catch(Exception e)
			{
				String message = "Failed to load data at tab index="+i;
				throw new Exception(message,e);
			}
		}
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("addTab")) {addTab((Map) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void addTab(Map map) throws Exception
	{
		
		String tabDisplay = (String) buildTabDisplay.t(map);
		Object tabHolder = buildTabHolder.t(map);
		
		((V)tabbedpane).v(tabDisplay,tabHolder);
		tabs.add(tabHolder);
	}
}
