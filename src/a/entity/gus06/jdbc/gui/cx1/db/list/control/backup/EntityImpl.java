package a.entity.gus06.jdbc.gui.cx1.db.list.control.backup;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20230219";}


	private Service info;
	private Service waitWritten;

	public EntityImpl() throws Exception
	{
		info = Outside.service(this,"gus06.swing.optionpane.showmessage.info");
		waitWritten = Outside.service(this,"gus06.file.tool.waituntil.written");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object dumpHolder = o[0];
		List selection = (List) o[1];
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<selection.size();i++)
		{
			String dbName = (String) selection.get(i);
			((V)dumpHolder).v("backup", dbName);
			
			File sqlFile = (File) ((R)dumpHolder).r("sqlFile");
			waitWritten.p(sqlFile);
			long size = sqlFile.length();
			String message = "SQL file: "+sqlFile+" ["+size+"]";
			b.append(message+"\n");
		}
		
		String title = "Backup complete";
		info.p(new String[]{b.toString(), title});
	}
}
