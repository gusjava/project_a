package a.entity.gus06.string.split.lines1.list.trimed;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170131";}


	private Service split;

	public EntityImpl() throws Exception
	{
		split = Outside.service(this,"gus06.string.split.lines1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String[] nn = (String[]) split.t(obj);
		List list = new ArrayList();
		for(String n:nn)
		{
			String n0 = n.trim();
			if(!n0.equals("")) list.add(n0);
		}
		return list;
	}
}