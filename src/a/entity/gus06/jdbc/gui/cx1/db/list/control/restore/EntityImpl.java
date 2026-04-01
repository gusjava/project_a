package a.entity.gus06.jdbc.gui.cx1.db.list.control.restore;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20230219";}
	

	private Service clipboard;
	private Service choose;
	private Service warning;
	private Service info;
	private Service isSqlFile;
	private Service recreateDb;
	
	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus06.clipboard.access.file");
		choose = Outside.service(this,"gus06.input.choose.dialog");
		warning = Outside.service(this,"gus06.swing.optionpane.showmessage.warning");
		info = Outside.service(this,"gus06.swing.optionpane.showmessage.info");
		isSqlFile = Outside.service(this,"gus06.file.filter.ext.istype.text.sql");
		recreateDb = Outside.service(this,"gus06.jdbc.mysql.perform.db.recreate");
	}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object cx = o[0];
		Object dumpHolder = o[1];
		List selection = (List) o[2];
		
		Object source = findSource(dumpHolder);
		if(source==null) return false;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<selection.size();i++)
		{
			String dbName = (String) selection.get(i);
			recreateDb.p(new Object[]{cx,dbName});
			((V)dumpHolder).v("restore",new Object[]{dbName, source});
		
			File sqlFile = (File) ((R)dumpHolder).r("sqlFile");
			long size = sqlFile.length();
		
			String message = "SQL file: "+sqlFile+" ["+size+"]";
		}
			
		String title = "Restore complete";
		info.p(new String[]{b.toString(), title});
		return true;
	}
	
	private Object findSource(Object dumpHolder) throws Exception
	{
		File file = (File) clipboard.g();
		if(isSqlFile.f(file)) return file; 
		
		List listing = (List) ((G)dumpHolder).g();
		if(listing.isEmpty()) 
		{
			warning.p("No dump file available for restore");
			return null;
		}
		
		String message = "Please, choose a dump backup:";
		String title = "Dump chooser";
		return (String) choose.t(new Object[]{message,title,listing});
	}
}