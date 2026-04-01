package a.entity.gus06.appli.gusexplorer.gui.centralpane.dnd;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141208";}


	private Service bgPaint;
	private Service dnd;
	private Service paste;
	private Service manager;
	
	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		bgPaint = Outside.service(this,"gus06.swing.comp.graphics.cust3.icon.dnd1");
		paste = Outside.service(this,"gus06.swing.comp.cust3.filepaste");
		dnd = Outside.service(this,"gus06.awt.dnd");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JComponent comp = (JComponent) obj;
		
		((V) comp).v("bgPaint",bgPaint);
		dnd.p(new Object[]{comp,manager,null});
		paste.p(new Object[]{comp,manager});
	}
}
