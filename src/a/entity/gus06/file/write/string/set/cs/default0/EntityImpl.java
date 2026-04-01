package a.entity.gus06.file.write.string.set.cs.default0;

import a.framework.*;

import java.io.File;
import java.io.PrintStream;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220617";}
	
	private Service buildPrintStream;

	public EntityImpl() throws Exception
	{
		buildPrintStream = Outside.service(this,"gus06.io.printstream.cs.default0");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		Set set = (Set) o[1];
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		PrintStream p = (PrintStream) buildPrintStream.t(file);
		printSet(p,set);
		p.close();
	}
	
	
	
	private void printSet(PrintStream p, Set set)
	{
		Iterator it = set.iterator();
		int nb = set.size();
		int i = 0;
		while(it.hasNext())
		{
			String line = (String) it.next();
			if(i<nb-1) p.println(line);
			else p.print(line);
			i++;
		}
	}
}
