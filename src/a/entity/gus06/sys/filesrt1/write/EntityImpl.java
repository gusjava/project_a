package a.entity.gus06.sys.filesrt1.write;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20230103";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		List data = (List) o[1];
		
		PrintStream p = new PrintStream(file);
		for(int i=0;i<data.size();i++)
		{
			Object[] section = (Object[]) data.get(i);
			int[] startInfos = (int[]) section[0];
			int[] endInfos = (int[]) section[1];
			String text = (String) section[2];
			
			p.println(i+1);
			printTimeStamp(p,startInfos);
			p.print(" --> ");
			printTimeStamp(p,endInfos);
			p.println();
			p.println(text.trim());
			p.println();
		}
	}
	
	private void printTimeStamp(PrintStream p, int[] timeStamp)
	{
		p.print(timeStamp[0]);
		p.print(":");
		p.print(timeStamp[1]);
		p.print(":");
		p.print(timeStamp[2]);
		p.print(",");
		p.print(timeStamp[3]);
	}
}