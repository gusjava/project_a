package a.entity.gus06.data.viewer.object1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20220929";}


	private Service viewerBuilder;
	private Service shiftPanel;
	
	private Object data;

	
	public EntityImpl() throws Exception
	{
		viewerBuilder = Outside.service(this,"*gus06.data.viewer.object.builder.async");
		shiftPanel = Outside.service(this,"*gus06.swing.panel.shiftpanel");
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	
	public void p(Object obj) throws Exception
	{
		data = obj;
		
		if(data==null)
		{initViewerNull();return;}
		
		Map viewers = (Map) viewerBuilder.t(data);
		
		if(viewers==null || viewers.isEmpty())
		{initViewerNull();return;}
		
		if(viewers.size()==1)
		{
			I viewer = (I) viewers.values().iterator().next();
			shiftPanel.p(viewer.i());
		}
		else
		{
			shiftPanel.p(viewersTab(viewers));
		}
	}
	
	
	
	private void initViewerNull() throws Exception
	{
		shiftPanel.p(null);
	}
	
	private JTabbedPane viewersTab(Map viewers) throws Exception
	{
		JTabbedPane tab = new JTabbedPane();
		ArrayList keys = new ArrayList(viewers.keySet());
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			I viewer = (I) viewers.get(key);
			tab.addTab(key,(JComponent) viewer.i());
		}
		return tab;
	}
}
