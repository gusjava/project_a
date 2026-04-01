package a.entity.gus06.jdbc.gui.cx1.db.list.control.script;

import a.framework.*;
import java.sql.Connection;
import java.awt.Dimension;
import javax.swing.JComponent;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20231015";}
	
	public static final String TITLE = "Custom Db script Chooser";
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;


	private Service manager;
	private Service listChooser;
	private Service annexe;
	private Service useDb;
	private Service findDbName;

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"*gus06.jdbc.gui.cx1.db.list.control.script.manager");
		listChooser = Outside.service(this,"*gus06.sys.listchooser1.dialog2");
		annexe = Outside.service(this,"gus06.jdbc.gui.cx1.db.list.control.script.annexe");
		useDb = Outside.service(this,"gus06.jdbc.mysql.perform.cx.usedb");
		findDbName = Outside.service(this,"gus06.jdbc.mysql.perform.cx.dbname");
	}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		List selection = (List) o[1];
		
		if(selection==null || selection.isEmpty()) return false;
		String dbName = (String) selection.get(0);
		
		listChooser.v("title",TITLE);
		listChooser.v("width",""+WIDTH);
		listChooser.v("height",""+HEIGHT);
		listChooser.v("annexe",annexe);
		listChooser.v("persistKey",getClass().getName());
		
		Set set = (Set) manager.g();
		if(set.isEmpty()) return false;
		
		List keys = new ArrayList(set);
		Collections.sort(keys);
		
		String key = (String) listChooser.t(keys);
		if(key==null) return false;
		
		P handler = (P) manager.r("p:"+key);
		
		Map data = new HashMap();
		data.put("cx",cx);
		data.put("dbName",dbName);
		
		String previousName = (String) findDbName.t(cx);
		useDb.p(new Object[]{cx, dbName});
		handler.p(data);
		useDb.p(new Object[]{cx, previousName});
		
		return false;
	}
	
}