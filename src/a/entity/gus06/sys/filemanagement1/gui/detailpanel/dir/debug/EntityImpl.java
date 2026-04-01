package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.debug;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250610";}
	

	private Service tab;
	private Service mapViewer1;
	
	private Object engine;
	private Map selected;
	
	
	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		mapViewer1 = Outside.service(this,"*gus06.data.viewer.map-1");
		
		tab.v("Selected",mapViewer1.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		
		mapViewer1.p(selected);
	}
	
	
	private void reset() throws Exception
	{
		engine = null;
		selected = null;
		mapViewer1.p(null);
	}
}