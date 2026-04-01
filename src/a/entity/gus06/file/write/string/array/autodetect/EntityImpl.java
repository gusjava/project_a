package a.entity.gus06.file.write.string.array.autodetect;

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
		String[] array = toArray(o[1]);
		
		File parent = file.getParentFile();
		if(!parent.exists()) parent.mkdirs();
		
		PrintStream p = (PrintStream) buildPrintStream.t(file);
		printArray(p,array);
		p.close();
	}
	
	
	
	private void printArray(PrintStream p, String[] array)
	{
		int nb = array.length;
		for(int i=0;i<nb;i++)
		{
			if(i<nb-1) p.println(array[i]);
			else p.print(array[i]);
		}
	}
	
	private String[] toArray(Object obj) throws Exception
	{
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof List)
		{
			List list = (List) obj;
			String[] arr = new String[list.size()];
			for(int i=0;i<list.size();i++) arr[i] = (String) list.get(i);
			return arr;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
