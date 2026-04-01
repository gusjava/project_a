package a.entity.gus06.data.perform.bsplit2;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180110";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		String glue1 = (String) o[1];
		String glue2 = (String) o[2];
		
		if(input instanceof String)
		return split((String) input,glue1,glue2);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	
	private String[][] split(String s, String glue1, String glue2) throws Exception
	{
		List list = new ArrayList();
		String[] nn = s.split(glue2,-1);
		for(String n:nn)
		{
			String[] kk = n.split(glue1,-1);
			list.add(kk);
		}
		
		int nb1 = list.size();
		int nb2 = findNb2(list);
		
		String[][] table = new String[nb1][nb2];
		for(int i=0;i<nb1;i++)
		{
			String[] row = (String[]) list.get(i);
			for(int j=0;j<nb2;j++)
			table[i][j] = j<row.length ? row[j] : null;
		}
		return table;
	}
	
	
	private int findNb2(List list)
	{
		int n = 0;
		for(int i=0;i<list.size();i++)
		{
			String[] row = (String[]) list.get(i);
			if(n<row.length) n = row.length;
		}
		return n;
	}
}
