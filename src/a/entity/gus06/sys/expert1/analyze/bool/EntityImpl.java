package a.entity.gus06.sys.expert1.analyze.bool;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160810";}


	private Service useless;
	private Service simplifier;
	private Service variable1;
	
	private Service iftrue;
	private Service iffalse;


	public EntityImpl() throws Exception
	{
		useless = Outside.service(this,"gus06.sys.expert1.analyze.bool.useless");
		simplifier = Outside.service(this,"gus06.sys.expert1.analyze.bool.simplifier");
		variable1 = Outside.service(this,"gus06.sys.expert1.analyze.bool.variable1");
		
		iftrue = Outside.service(this,"gus06.sys.expert1.analyze.bool.iftrue");
		iffalse = Outside.service(this,"gus06.sys.expert1.analyze.bool.iffalse");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Map m = (Map) obj;
		
		if(useless.f(m)) return true;
		if(simplifier.f(m)) return true;
		if(variable1.f(m)) return true;
		
		Boolean value = (Boolean) m.get("value");
		F f = value.booleanValue() ? iftrue : iffalse;
		
		return f.f(m);
	}
}
