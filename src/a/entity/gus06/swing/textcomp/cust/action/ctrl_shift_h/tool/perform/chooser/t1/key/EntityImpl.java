package a.entity.gus06.swing.textcomp.cust.action.ctrl_shift_h.tool.perform.chooser.t1.key;

import a.framework.*;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Set;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170225";}
	
	public static final String TITLE = "Custom Text Transform Chooser";
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	
	
	
	private Service listChooser;
	private Service annexe;
	private Service manager;
	

	public EntityImpl() throws Exception
	{
		listChooser = Outside.service(this,"*gus06.sys.listchooser1.dialog2");
		annexe = Outside.service(this,"*gus06.swing.textcomp.cust.action.ctrl_shift_h.tool.perform.chooser.annexe");
		manager = Outside.service(this,"*gus06.swing.textcomp.cust.action.ctrl_shift_h.tool.perform.chooser.manager");
	}
	
	
	
	public Object g() throws Exception
	{
		listChooser.v("title",TITLE);
		listChooser.v("width",""+WIDTH);
		listChooser.v("height",""+HEIGHT);
		listChooser.v("annexe",annexe);
		listChooser.v("persistKey",getClass().getName());
		
		Set set = (Set) manager.g();
		List keys = new ArrayList(set);
		Collections.sort(keys);
		
		return listChooser.t(keys);
	}
}
