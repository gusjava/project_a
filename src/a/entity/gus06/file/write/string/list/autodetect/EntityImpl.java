package a.entity.gus06.file.write.string.list.autodetect;

import a.framework.*;

import java.io.File;
import java.io.PrintStream;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190420";}
	


	private Service buildPrintStream;

	public EntityImpl() throws Exception
	{
		buildPrintStream = Outside.service(this,"gus06.io.printstream.autodetect");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		List list = (List) o[1];
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		PrintStream p = (PrintStream) buildPrintStream.t(file);
		printList(p,list);
		p.close();
	}
	
	
	
	private void printList(PrintStream p, List list)
	{
		int nb = list.size();
		for(int i=0;i<nb;i++)
		{
			String line = (String) list.get(i);
			if(i<nb-1) p.println(line);
			else p.print(line);
		}
	}
}
