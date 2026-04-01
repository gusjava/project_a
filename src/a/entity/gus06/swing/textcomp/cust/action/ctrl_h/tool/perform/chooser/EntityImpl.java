package a.entity.gus06.swing.textcomp.cust.action.ctrl_h.tool.perform.chooser;

import a.framework.*;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Map;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141105";}
	
	public static final String TITLE = "Text Transform Chooser";
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	
	
	
	private Service listChooser;
	private Service annexe;
	private Service findEntityMap;
	
	private Map map;
	private List keys;



	public EntityImpl() throws Exception
	{
		listChooser = Outside.service(this,"*gus06.sys.listchooser1.dialog2");
		annexe = Outside.service(this,"*gus06.swing.textcomp.cust.action.ctrl_h.tool.perform.chooser.annexe");
		findEntityMap = Outside.service(this,"gus06.swing.textcomp.cust.action.ctrl_h.tool.perform.chooser.names");
		
		map = (Map) findEntityMap.g();
		keys = new ArrayList(map.keySet());
		Collections.sort(keys);
	}
	
	
	
	public Object g() throws Exception
	{
		listChooser.v("title",TITLE);
		listChooser.v("width",""+WIDTH);
		listChooser.v("height",""+HEIGHT);
		listChooser.v("annexe",annexe);
		listChooser.v("persistKey",getClass().getName());
		
		String key = (String) listChooser.t(keys);
		return key!=null ? map.get(key) : null;
	}
}
