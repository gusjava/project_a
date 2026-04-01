package a.entity.gus06.io.printstream.println.row.tab;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191128";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		PrintStream p = (PrintStream) o[0];
		String[] row = (String[]) o[1];
		
		int nb = row.length;
		for(int i=0;i<nb;i++)
		{
			p.print(row[i]);
			if(i<nb-1) p.print('\t');
		}
		p.println();
	}
}
