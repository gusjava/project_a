package a.entity.gus06.debug.printerr;

import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140723";}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private String now() {return sdf.format(new Date());}
	
	
	private List errors;

	public EntityImpl() throws Exception
	{
		errors = (List) Outside.resource(this,"errors");
	}
	
	
	public void e() throws Exception
	{
		File file = new File("ERR_"+now()+".txt");
		PrintStream p = new PrintStream(file);
		
		for(int i=0;i<errors.size();i++)
		{
			Object[] o = (Object[]) errors.get(i);
			if(o.length!=4) throw new Exception("Invalid error info array size at index: "+i);
			printErr(p,o,i);
		}
		p.close();
	}
	
	
	
	private void printErr(PrintStream p, Object[] o, int index)
	{
		Entity entity = (Entity) o[0];
		String id = (String) o[1];
		Exception exp = (Exception) o[2];
		Date date = (Date) o[3];
		
		String src = entity.getClass().getName()+"@"+id;
		String timeStamp = sdf.format(date);
		
		p.println("___________________");
		p.println("Error ["+(index+1)+"/"+errors.size()+"]");
		p.println("time:"+timeStamp);
		p.println("src:"+src);
		p.println();
		exp.printStackTrace(p);
	}
}
