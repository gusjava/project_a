package a.entity.gus06.sys.objfactory1.rulemap1.engine;

import a.framework.*;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20191122";}


	private Service buildEngine;
	private Service ruleMap;
	
	private R engine;


	public EntityImpl() throws Exception
	{
		buildEngine = Outside.service(this,"gus06.sys.objfactory1.engine.builder1");
		ruleMap = Outside.service(this,"gus06.sys.objfactory1.rulemap1");
		
		engine = (R) buildEngine.t(ruleMap.g());
	}
	
	public Object r(String key) throws Exception
	{return engine.r(key);}
}
