package a.entity.gus06.sys.expert1.analyze.bool.iffalse;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160811";}


	private Service rewrite;

	public EntityImpl() throws Exception
	{
		rewrite = Outside.service(this,"gus06.sys.parser3.analyzer1.rewrite");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Map m = (Map) obj;
		
		Map output = (Map) m.get("output");
		Map analyze = (Map) m.get("analyze");
		
		//String newExp = (String) rewrite.t(analyze);
		//output.put(newExp,Boolean.FALSE);
		
		return false;
	}
}
