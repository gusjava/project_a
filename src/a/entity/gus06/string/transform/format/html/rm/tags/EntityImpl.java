package a.entity.gus06.string.transform.format.html.rm.tags;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20211013";}

	
	private Service split;
	private Service toString;

	public EntityImpl() throws Exception
	{
		split = Outside.service(this,"gus06.string.html.tag.split");
		toString = Outside.service(this,"gus06.tostring.list.join.n");
	}
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) split.t(obj);
		return toString.t(list);
	}
}
