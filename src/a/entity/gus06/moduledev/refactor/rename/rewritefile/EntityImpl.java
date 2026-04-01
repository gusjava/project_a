package a.entity.gus06.moduledev.refactor.rename.rewritefile;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140926";}


	private Service read;
	
	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.string.array");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		File file1 = (File) o[0];
		File file2 = (File) o[1];
		String name1 = (String) o[2];
		String name2 = (String) o[3];
		
		refactorFile(file1,file2,name1,name2);
	}
	
	
	
	
	private void refactorFile(File file1, File file2, String name1, String name2) throws Exception
	{
		String[] n = (String[]) read.t(file1);
		PrintStream p = new PrintStream(file2);
		
		for(String line:n)
		p.println(formatLine(line,name1,name2));
		p.close();
	}
	
	
	private String formatLine(String line, String name1, String name2)
	{
		String var1 = name1.toUpperCase().replace(".","_");
		String var2 = name2.toUpperCase().replace(".","_");
		return line.replace(var1,var2).replace(name1,name2);
	}
}
