package a.entity.gus06.sys.xhtmlparser1.engine;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170131";}


	private Service parser;
	private Service analyzer1;
	private Service analyzer2;
	
	
	public EntityImpl() throws Exception
	{
		parser = Outside.service(this,"gus06.sys.xhtmlparser1.parser");
		analyzer1 = Outside.service(this,"gus06.sys.xhtmlparser1.analyze1");
		analyzer2 = Outside.service(this,"gus06.sys.xhtmlparser1.analyze2");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		
		Object r = parser.t(input);
		r = analyzer1.t(r);
		r = analyzer2.t(r);
		
		return r;
	}
}