package a.entity.gus06.sys.jwpce1.importer1;

import a.framework.*;
import java.io.File;
import java.sql.Connection;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250722";}


	private Service formatRow;
	private Service insert;

	public EntityImpl() throws Exception
	{
		formatRow = Outside.service(this,"gus06.sys.jwpce1.importer1.formatrow");
		insert = Outside.service(this,"gus06.sys.jwpce1.engine.cx.insert.edict");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length==2) {importData((File) o[0], (Connection) o[1]);return;}
		if(o.length==3) {importData((File) o[0], (Connection) o[1], o[2]);return;}
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	
	private void importData(File file, Connection cx) throws Exception
	{
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		
		String line;
		while((line = reader.readLine()) != null)
		{
			Map data = (Map) formatRow.t(line);
			insert.p(new Object[]{cx, data});
		}
		reader.close();
		cx.close();
	}
	
	private void importData(File file, Connection cx, Object progress) throws Exception
	{
		int nb = lineNb(file);
		
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		
		String line;
		
		((V)progress).v("size",""+nb);
		for(int i=0;i<nb;i++)
		{
			line = reader.readLine();
			Map data = (Map) formatRow.t(line);
			insert.p(new Object[]{cx, data});
			((E)progress).e();
		}
		reader.close();
		cx.close();
	}
	
	private int lineNb(File file) throws Exception
	{
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		int number = 0;
		while((br.readLine())!=null) number++;
		fis.close();
		return number;
	}
}