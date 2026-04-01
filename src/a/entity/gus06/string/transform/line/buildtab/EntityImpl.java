package a.entity.gus06.string.transform.line.buildtab;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141114";}
	
	public static final String DELIM = "\n";
	public static final String DELIM2 = "\t";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		
		List v1 = new ArrayList();
		List v2 = new ArrayList();
		int dimMax = 0;
		
		boolean gap = false;
		for(int i=0;i<n.length;i++)
		{
			if(n[i].equals("")) gap = true;
			else
			{
				if(gap)
				{
					v1.add(v2);
					if(v2.size()>dimMax)
						dimMax = v2.size();
					
					v2 = new ArrayList();
					gap = false;
				}
				v2.add(n[i]);
			}
		}
		
		if(v2.size()>0)
		{
			v1.add(v2);
			if(v2.size()>dimMax) dimMax = v2.size();
		}
		
		int x = dimMax;
		int y = v1.size();
			
		String[][] table = new String[x][y];
		initEmptyTable(table);
		
		for(int j=0;j<y;j++)
		{
			List v = (List) v1.get(j);
			for(int i=0;i<v.size();i++)
			table[i][j] = (String) v.get(i); 
		}
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<x;i++)
		{
			b.append(table[i][0]);
			for(int j=1;j<y;j++)
			b.append(DELIM2+table[i][j]);
			b.append(DELIM);
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	private void initEmptyTable(String[][] table)
	{
		for(int i=0;i<table.length;i++)
		for(int j=0;j<table[0].length;j++)
		table[i][j] = "";
	}
}