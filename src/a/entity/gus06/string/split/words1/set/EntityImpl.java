package a.entity.gus06.string.split.words1.set;

import a.framework.*;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160604";}


	private Service split;


	public EntityImpl() throws Exception
	{
		split = Outside.service(this,"gus06.string.split.words1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String[] nn = (String[]) split.t(obj);
		Set set = new HashSet();
		for(String n:nn)
		if(!n.trim().equals("")) set.add(n.trim());
		return set;
	}
}
