package a.entity.gus06.sys.clipboard1.g.listfiles2.convertstring;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20151021";}

	
	
	public void p(Object obj) throws Exception
	{t(obj);}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] nn = s.split("\n");
		
		List list = new ArrayList();
		for(String n:nn)
		{
			String path = buildPath(n.trim());
			File f = new File(path);
			if(f.exists()) list.add(f);
		}
		return list;
	}
	
	
	private String buildPath(String s)
	{
		if(s.startsWith("%"))
		{
			String[] n = s.substring(1).split("%",2);
			if(n.length==2)
			{
				Map env = System.getenv();
				if(env.containsKey(n[0]))
				return env.get(n[0])+n[1];
			}
		}
		return s;
	}
}
