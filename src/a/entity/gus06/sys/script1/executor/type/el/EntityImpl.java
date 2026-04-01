package a.entity.gus06.sys.script1.executor.type.el;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150829";}


	private Service elMap;
	private Service elDefault;
	private Service getName;


	public EntityImpl() throws Exception
	{
		elMap = Outside.service(this,"gus06.sys.script1.executor.type.elmap");
		elDefault = Outside.service(this,"gus06.sys.script1.executor.type.eldefault");
		getName = Outside.service(this,"gus06.sys.script1.access.tag.name1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map tag = (Map) obj;
		String name = (String) getName.t(tag);
		
		T builder = (T) elMap.t(name);
		if(builder!=null) return builder.t(tag);
		return elDefault.t(tag);
	}
}
