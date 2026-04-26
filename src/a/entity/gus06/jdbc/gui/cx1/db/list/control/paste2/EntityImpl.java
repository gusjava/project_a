package a.entity.gus06.jdbc.gui.cx1.db.list.control.paste2;

import a.framework.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20231016";}


	private Service dialog;
	private Service warning;
	private Service clipboard;
	private Service recreateDb;
	private Service getName0;
	private Service hasTables;

	public EntityImpl() throws Exception
	{
		dialog = Outside.service(this,"gus06.input.confirm.dialog");
		warning = Outside.service(this,"gus06.swing.optionpane.showmessage.warning");
		clipboard = Outside.service(this,"gus.y.clipboard1.files");
		recreateDb = Outside.service(this,"gus06.jdbc.mysql.perform.db.recreate");
		getName0 = Outside.service(this,"gus06.file.getname0");
		hasTables = Outside.service(this,"gus06.jdbc.mysql.perform.counttable.bydb.selection.has");
	}

	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length==2) return handle(o[0], o[1]);
		if(o.length==3) return handle(o[0], o[1], (List) o[2]);
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	private boolean handle(Object cx, Object dumpHolder) throws Exception
	{
		List sqlFiles = (List) clipboard.g();
		return handle(cx, dumpHolder, sqlFiles);
	}
	
	private boolean handle(Object cx, Object dumpHolder, List sqlFiles) throws Exception
	{
		if(sqlFiles==null || sqlFiles.isEmpty())
		{
			String title = "Paste aborted";
			String message = "No files inside clipboard";
			warning.p(new String[]{message, title});
			return false;
		}
		
		List dbNames = new ArrayList();
		for(int i=0;i<sqlFiles.size();i++)
		{
			File sqlFile = (File) sqlFiles.get(i);
			String dbName = (String) getName0.t(sqlFile);
			if(dbNames.contains(dbName))
			{
				String title = "Paste aborted";
				String message = "Files with same name detected inside clipboard";
				warning.p(new String[]{message, title});
				return false;
			}
			dbNames.add(dbName);
		}
		
		Integer count = (Integer) hasTables.t(new Object[]{cx, dbNames});
		if(count>0)
		{
			String title = count>1 ? "Overwriting "+count+" databases ?" : "Overwriting 1 database ?";
			boolean ok = dialog.f(title);
			if(!ok) return false;
		}
		
		for(int i=0;i<sqlFiles.size();i++)
		{
			File sqlFile = (File) sqlFiles.get(i);
			String dbName = (String) getName0.t(sqlFile);
			
			recreateDb.p(new Object[]{cx,dbName});
			((V)dumpHolder).v("restore", new Object[]{dbName, sqlFile});
		}
		return true;
	}
}