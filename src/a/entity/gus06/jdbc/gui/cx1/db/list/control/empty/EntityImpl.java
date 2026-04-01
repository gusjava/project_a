package a.entity.gus06.jdbc.gui.cx1.db.list.control.empty;

import a.framework.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20231014";}


	private Service perform;
	private Service dialog;
	private Service warning;
	private Service hasTables;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.db.recreate");
		dialog = Outside.service(this,"gus06.input.confirm.dialog");
		warning = Outside.service(this,"gus06.swing.optionpane.showmessage.warning");
		hasTables = Outside.service(this,"gus06.jdbc.mysql.perform.counttable.bydb.selection.has");
	}

	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object cx = o[0];
		List selection = (List) o[1];
		
		if(selection==null || selection.isEmpty())
		{
			String title = "Empty aborted";
			String message = "No db selection found";
			warning.p(new String[]{message, title});
			return false;
		}
		
		Integer count = (Integer) hasTables.t(new Object[]{cx, selection});
		if(count==0) return false;
		
		String title = count>1 ? "Emptying "+count+" databases ?" : "Emptying 1 database ?";
		boolean ok = dialog.f(title);
		if(!ok) return false;
		
		for(Object dbName : selection) perform.p(new Object[]{cx,dbName});
		return true;
	}
}