package a.entity.gus06.data.string.build.lineblocks;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231204";}
	
	public static final String DELIM = "\n";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] n = s.split(DELIM,-1);
		List blocks = new ArrayList();
		List current = new ArrayList();
		
		boolean gap = false;
		for(int i=0;i<n.length;i++)
		{
			if(n[i].equals("")) gap = true;
			else
			{
				if(gap)
				{
					blocks.add(current);
					current = new ArrayList();
					gap = false;
				}
				current.add(n[i]);
			}
		}
		if(current.size()>0) blocks.add(current);
		return blocks;
	}
}