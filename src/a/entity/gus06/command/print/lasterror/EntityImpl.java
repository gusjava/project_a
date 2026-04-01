package a.entity.gus06.command.print.lasterror;

import java.io.PrintStream;
import java.util.Date;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, E, P {

	public String creationDate() {return "20140703";}

	private List errors;
	private PrintStream out;

	public EntityImpl() throws Exception
	{
		errors = (List) Outside.resource(this,"errors");
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	
	public void e() throws Exception
	{
		int index = errors.size()-1;
		printError(index);
	}

	
	public void p(Object obj) throws Exception
	{
		int index = Integer.parseInt((String)obj);
		printError(index);
	}
	
	
	
	private void printError(int index) throws Exception
	{
		if(errors.isEmpty())
		{
			out.println("No error found");
			return;
		}
		if(index<0 || index>=errors.size())
			throw new Exception("Error not found at index "+index);
		
		Object[] infos = (Object[]) errors.get(index);
		if(infos.length!=4) throw new Exception("Invalid error info array size at index: "+index);

		Entity entity = (Entity) infos[0];
		String id = (String) infos[1];
		Exception exp = (Exception) infos[2];
		Date date = (Date) infos[3];

		String src = entity.getClass().getName()+"@"+id;
		String message = "Error ["+(index+1)+"/"+errors.size()+"] occured at "+src;
		
		out.println(message);
		exp.printStackTrace(out);
	}
}
