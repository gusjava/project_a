package a.entity.gus06.y.entitysys1.perform.paste.split;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251125";}
	
	public static final String DELIM = "package\\s+gus06\\.entity\\.";
	public static final String START = "package gus06.entity.";
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) obj;
		String[] n = src.split(DELIM);
		List list = new ArrayList();
		for(int i=1;i<n.length;i++)
		{
			String s = START+n[i].trim();
			list.add(s);
		}
		return list;
	}
}
