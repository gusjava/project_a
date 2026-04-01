package a.entity.gus06.sys.filemanagement1.gui.detailpanel.file.debug;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200920";}
	

	private Service tab;
	private Service mapViewer1;
	private Service mapViewer2;
	private Service mapViewer3;
	
	private Object engine;
	private Map selected;
	private Map prop;
	private Map info;
	private String md5;
	
	
	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		mapViewer1 = Outside.service(this,"*gus06.data.viewer.map-1");
		mapViewer2 = Outside.service(this,"*gus06.data.viewer.map-2");
		mapViewer3 = Outside.service(this,"*gus06.data.viewer.map-3");
		
		tab.v("Selected",mapViewer1.i());
		tab.v("Prop",mapViewer2.i());
		tab.v("Info",mapViewer3.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		prop = (Map) o[2];
		
		if(prop!=null && engine!=null)
		{
			md5 = (String) prop.get("md5");
			info = (Map) ((R)engine).r("info:"+md5);
		}
		else
		{
			md5 = null;
			info = null;
		}
		refresh();
	}
	
	
	
	private void refresh()
	{
		try
		{
			mapViewer1.p(selected);
			mapViewer2.p(prop);
			mapViewer3.p(info);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	
	
	private void reset()
	{
		try
		{
			engine = null;
			selected = null;
			prop = null;
			
			mapViewer1.p(null);
			mapViewer2.p(null);
			mapViewer3.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
}