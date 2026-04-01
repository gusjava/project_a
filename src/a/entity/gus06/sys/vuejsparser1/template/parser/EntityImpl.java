package a.entity.gus06.sys.vuejsparser1.template.parser;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260104";}
	
	private Service parser;

	public EntityImpl() throws Exception
	{
		parser = Outside.service(this,"gus06.sys.xhtmlparser1.engine");
	}
	
	public Object t(Object obj) throws Exception
	{return parser.t(obj);}
}
