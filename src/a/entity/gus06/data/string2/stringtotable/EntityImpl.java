package a.entity.gus06.data.string2.stringtotable;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		String[] lines = s.split("[\n\r]",-1);
		int x = lines.length;
		if(x==0) return new String[0][0];
		
		int y = 0;
		for(int i=0;i<x;i++)
		{
		    int n = lines[i].split("\t",-1).length;
		    if(y < n) y = n;
		}
		
		String[][] data = new String[y][x];
		for(int i=0;i<x;i++)
		{
		    String[] cells = lines[i].split("\t",-1);
		    int n = cells.length;
		    
		    for(int j=0;j<n;j++)
		    data[j][i] = cells[j];
		    if(n<y) for(int j=n;j<y;j++)
		    data[j][i] = "";
		}
		return data;
	}
}
