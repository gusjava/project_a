package a.entity.gus06.math.maze.data.sysprint;

import a.framework.*;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20150327";}
	
	
	public void p(Object obj) throws Exception
	{
		boolean[][] data = (boolean[][]) obj;
		
		for(int i=0;i<data.length;i++)
		{
			boolean[] row = data[i];
			for(int j=0;j<row.length;j++) System.out.print(row[j]?" ":"#");
			System.out.println();
		}
	}
	
	
	public Object t(Object obj) throws Exception
	{
		boolean[][] data = (boolean[][]) obj;
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<data.length;i++)
		{
			boolean[] row = data[i];
			for(int j=0;j<row.length;j++) b.append(row[j]?" ":"#");
			b.append("\n");
		}
		return b.toString();
	}
}
