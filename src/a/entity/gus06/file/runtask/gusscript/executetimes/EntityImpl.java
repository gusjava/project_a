package a.entity.gus06.file.runtask.gusscript.executetimes;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20230309";}
	
	public static final String MESSAGE = "Please, enter how many times";


	private Service fileToExe;
	private Service inputDialog;

	public EntityImpl() throws Exception
	{
		fileToExe = Outside.service(this,"gus06.file.string.perform.execute.script1.filetoexecute");
		inputDialog = Outside.service(this,"gus06.input.text.dialog");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String input = (String) inputDialog.t(new String[]{MESSAGE,"10"});
		if(input==null || input.equals("")) return;
		
		int times = toInt(input);
		if(times==0) return;
		
		E exe = (E) fileToExe.t(file);
		if(progress!=null) ((V)progress).v("size",""+times);
		for(int i=0;i<times;i++)
		{
			exe.e();	
			if(progress!=null) ((E)progress).e();
		}
	}
	
	
	private int toInt(String s)
	{
		try{return Integer.parseInt(s);}
		catch(NumberFormatException e){return 0;}
	}
}