package a.entity.gus06.string.split.block1.list;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220620";}
	

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.replace("\r","").split("\n");
		
		List list = new ArrayList();
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		{
			String line = n[i];
			if(line.trim().equals(""))
			{
				if(b.length()>0) list.add(b.toString());
				b.delete(0, b.length());
			}
			else
			{
				if(b.length()>0) b.append("\n"+line);
				else b.append(line);
			}
		}
		
		if(b.length()>0) list.add(b.toString());
		return list;
	}
}