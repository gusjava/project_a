package a.entity.gus06.jdbc.gui.cx1.db.list.control.duplicate;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20230219";}
	
	public static final String MESSAGE = "Enter new DB name";

	private Service createDb;
	private Service waitWritten;
	private Service info;
	private Service changeText;
	private Service format;
	
	public EntityImpl() throws Exception
	{
		createDb = Outside.service(this,"gus06.jdbc.mysql.perform.db.create");
		waitWritten = Outside.service(this,"gus06.file.tool.waituntil.written");
		info = Outside.service(this,"gus06.swing.optionpane.showmessage.info");
		changeText = Outside.service(this,"gus06.input.text.dialog.change");
		format = Outside.service(this,"gus06.string.transform.format.dbnameinput1");
	}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object cx = o[0];
		Object dumpHolder = o[1];
		List selection = (List) o[2];
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<selection.size();i++)
		{
			String dbName = (String) selection.get(i);
			String newDbName = (String) changeText.t(new String[]{MESSAGE, dbName});
			if(newDbName!=null)
			{
				newDbName = format(newDbName);
				createDb.p(new Object[]{cx,newDbName});
				
				((V)dumpHolder).v("backupTemp", dbName);
				File sqlFile = (File) ((R)dumpHolder).r("sqlFile");
				waitWritten.p(sqlFile);
				long size = sqlFile.length();
				
				((V)dumpHolder).v("restore",new Object[]{newDbName, sqlFile});
				b.append(dbName+" -> "+newDbName+" ["+size+"]\n");
			}
		}
		
		String title = "Duplication complete";
		info.p(new String[]{b.toString(), title});
		return true;
	}
	
	
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}