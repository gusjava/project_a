package a.entity.gus06.string.transform.url.extractlinks.external;

import a.framework.*;
import java.net.URL;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250501";}


	private Service extract;
	private Service join;

	public EntityImpl() throws Exception
	{
		extract = Outside.service(this,"gus06.url.extractlinks.external");
		join = Outside.service(this,"gus06.tostring.list.join.n");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		List list = (List) extract.t(s);
		return join.t(list);
	}
}