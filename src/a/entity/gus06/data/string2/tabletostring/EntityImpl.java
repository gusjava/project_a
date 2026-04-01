package a.entity.gus06.data.string2.tabletostring;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}

	
	
	public Object t(Object obj) throws Exception
	{
		String[][] table = (String[][]) obj;
		
		int x = table[0].length;
		int y = table.length;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<x;i++)
		{
		    b.append(table[0][i]);
		    for(int j=1;j<y;j++) b.append("\t"+table[j][i]);
		    b.append("\n");
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
