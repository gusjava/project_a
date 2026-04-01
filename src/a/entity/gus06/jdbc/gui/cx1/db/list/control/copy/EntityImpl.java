package a.entity.gus06.jdbc.gui.cx1.db.list.control.copy;

import a.framework.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20230223";}


	private Service info;
	private Service waitWritten;
	private Service clipboard;

	public EntityImpl() throws Exception
	{
		info = Outside.service(this,"gus06.swing.optionpane.showmessage.info");
		waitWritten = Outside.service(this,"gus06.file.tool.waituntil.written");
		clipboard = Outside.service(this,"gus06.clipboard.access.listfiles");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object dumpHolder = o[0];
		List selection = (List) o[1];
		
		List sqlFiles = new ArrayList();
		for(int i=0;i<selection.size();i++)
		{
			String dbName = (String) selection.get(i);
			((V)dumpHolder).v("backupTemp", dbName);
			
			File sqlFile = (File) ((R)dumpHolder).r("sqlFile");
			waitWritten.p(sqlFile);
			sqlFiles.add(sqlFile);
		}
		
		clipboard.p(sqlFiles);
		
		String title = "Copy complete";
		String message = buildMessage(sqlFiles);
		info.p(new String[]{message, title});
	}
	
	
	private String buildMessage(List sqlFiles) 
	{
		if(sqlFiles.size()==1) 
		return "SQL file: "+buildMessage((File) sqlFiles.get(0));
		
		StringBuffer b = new StringBuffer();
		b.append("SQL files: "+sqlFiles.size());
		
		for(int i=0;i<sqlFiles.size();i++)
		{
			File f = (File) sqlFiles.get(i);
			b.append("\n"+buildMessage(f));
		}
		return b.toString();
	}
	
	private String buildMessage(File sqlFile) 
	{
		return sqlFile+" ["+sqlFile.length()+"]";
	}
}