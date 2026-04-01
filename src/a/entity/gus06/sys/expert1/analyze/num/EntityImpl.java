package a.entity.gus06.sys.expert1.analyze.num;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160810";}


	private Service useless;
	private Service simplifier;
	private Service variable1;
	
	private Service rewrite;


	public EntityImpl() throws Exception
	{
		useless = Outside.service(this,"gus06.sys.expert1.analyze.num.useless");
		simplifier = Outside.service(this,"gus06.sys.expert1.analyze.num.simplifier");
		variable1 = Outside.service(this,"gus06.sys.expert1.analyze.num.variable1");
		
		rewrite = Outside.service(this,"gus06.sys.parser3.analyzer1.rewrite");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Map m = (Map) obj;
		
		if(useless.f(m)) return true;
		if(simplifier.f(m)) return true;
		if(variable1.f(m)) return true;
		
		return false;
	}
}
