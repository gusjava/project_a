package a.entity.gus06.appli.gusgadgets.manager;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JDialog;
import java.util.Map;
import java.util.HashMap;
import java.awt.Dimension;

public class EntityImpl extends S1 implements Entity, R, G, V, P {

	public String creationDate() {return "20160605";}


	private Service onTop;
	private Service draggable;
	private Service locationPersister;

	private Map map;


	public EntityImpl() throws Exception
	{
		onTop = Outside.service(this,"gus06.swing.dialog.build.dialogontop");
		draggable = Outside.service(this,"gus06.swing.comp.cust.dragframe");
		locationPersister = Outside.service(this,"gus06.swing.dialog.persister.position");
		
		map = new HashMap();
	}
	
	
	private void init(Service s) throws Exception
	{
		String name = (String) s.r("name");
		Dimension size = (Dimension) s.r("size");
		
		map.put(name,s);
		
		JComponent gui = (JComponent) s.i();
		JDialog dialog = (JDialog) onTop.t(gui);
		
		if(size==null) size = gui.getPreferredSize();
		
		dialog.setMinimumSize(size);
		dialog.setMaximumSize(size);
		dialog.setPreferredSize(size);
		
		draggable.p(dialog);
		locationPersister.v("GADGET_POSITION_"+name,dialog);
		
    		dialog.setVisible(true);
		updated();
	}
	
	
	
	public Object g() throws Exception
	{return map;}
	
	
	public void p(Object obj) throws Exception
	{init((Service) obj);}
	
	
	
	public Object r(String key) throws Exception
	{
		return null;
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		
	}
	
	private void updated()
	{send(this,"updated()");}
}
