package a.entity.gus06.data.viewer.class1.lazy;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import java.util.*;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140821";}


	private Service shiftPanel;
	private Service viewer;
	

	public EntityImpl() throws Exception
	{shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");}
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public Object g() throws Exception
	{
		init();
		return viewer.g();
	}
	
	public void p(Object obj) throws Exception
	{
		if(obj!=null) init();
		if(viewer!=null) viewer.p(obj);
	}
	
	
	private void init() throws Exception
	{
		if(viewer!=null) return;
		viewer = Outside.service(this,"*gus06.data.viewer.class1");
		shiftPanel.p(viewer.i());
	}
}
