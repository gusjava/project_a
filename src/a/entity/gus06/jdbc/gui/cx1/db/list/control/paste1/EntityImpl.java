package a.entity.gus06.jdbc.gui.cx1.db.list.control.paste1;

import a.framework.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20231014";}


	private Service dialog;
	private Service warning;
	private Service clipboard;
	private Service recreateDb;
	private Service hasTables;

	public EntityImpl() throws Exception
	{
		dialog = Outside.service(this,"gus06.input.confirm.dialog");
		warning = Outside.service(this,"gus06.swing.optionpane.showmessage.warning");
		clipboard = Outside.service(this,"gus06.clipboard.access.file");
		recreateDb = Outside.service(this,"gus06.jdbc.mysql.perform.db.recreate");
		hasTables = Outside.service(this,"gus06.jdbc.mysql.perform.counttable.bydb.selection.has");
	}

	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length==3) return handle(o[0], o[1], (List) o[2]);
		if(o.length==4) return handle(o[0], o[1], (List) o[2], (File) o[3]);
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	
	private boolean handle(Object cx, Object dumpHolder, List selection) throws Exception
	{
		File sqlFile = (File) clipboard.g();
		return handle(cx, dumpHolder, selection, sqlFile);
	}
	
	
	private boolean handle(Object cx, Object dumpHolder, List selection, File sqlFile) throws Exception
	{
		if(sqlFile==null)
		{
			String title = "Paste aborted";
			String message = "No file inside clipboard";
			warning.p(new String[]{message, title});
			return false;
		}
		
		if(selection==null || selection.isEmpty())
		{
			String title = "Paste aborted";
			String message = "No db selection found";
			warning.p(new String[]{message, title});
			return false;
		}
		
		Integer count = (Integer) hasTables.t(new Object[]{cx, selection});
		if(count>0)
		{
			String title = count>1 ? "Overwriting "+count+" databases ?" : "Overwriting 1 database ?";
			title += "\nfrom dump file: "+sqlFile.getName();
			boolean ok = dialog.f(title);
			if(!ok) return false;
		}
		
		for(int i=0;i<selection.size();i++)
		{
			String dbName = (String) selection.get(i);
			recreateDb.p(new Object[]{cx,dbName});
			((V)dumpHolder).v("restore", new Object[]{dbName, sqlFile});
		}
		return true;
	}
}