package a.entity.gus06.sys.objfactory1.engine.builder2;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191124";}


	private Service buildEngine;
	private Service buildRuleMap1;

	public EntityImpl() throws Exception
	{
		buildEngine = Outside.service(this,"gus06.sys.objfactory1.engine.builder1");
		buildRuleMap1 = Outside.service(this,"gus06.sys.objfactory1.rulemap1");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map ruleMap2 = (Map) obj;
		Map ruleMap1 = (Map) buildRuleMap1.g();
		
		Map ruleMap = new HashMap(ruleMap1);
		if(ruleMap2!=null) ruleMap.putAll(ruleMap2);
		
		return buildEngine.t(ruleMap);
	}
}
