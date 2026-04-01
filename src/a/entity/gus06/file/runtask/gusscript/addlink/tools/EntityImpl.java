package a.entity.gus06.file.runtask.gusscript.addlink.tools;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Properties;
import java.util.ArrayList;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220609";}
	
	public static final String MESSAGE = "Please, enter script tool name";


	private Service dirHolder;
	private Service getName0;
	private Service inputDialog;

	public EntityImpl() throws Exception
	{
		dirHolder = Outside.service(this,"gus06.appli.gusexplorer.scripts.tools.manager");
		getName0 = Outside.service(this,"gus06.file.getname0");
		inputDialog = Outside.service(this,"gus06.input.text.dialog");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		String name0 = (String) getName0.t(file);
		String name1 = (String) inputDialog.t(new String[]{MESSAGE,name0});
		if(name1==null || name1.equals("")) return;
		
		if(progress!=null) ((V)progress).v("size","1");
		addLink(file,name1);
		if(progress!=null) ((E)progress).e();
	}
	
	
	
	private void addLink(File file, String name1) throws Exception
	{
		File dir = (File) dirHolder.r("dir");
		File targetFile = new File(dir,name1+".gus");
		
		String filePath = file.getAbsolutePath().replace(File.separator, "/");
		
		PrintStream p = new PrintStream(targetFile);
		p.println("@code");
		p.println("include \""+filePath+"\"");
		p.close();
	}
}