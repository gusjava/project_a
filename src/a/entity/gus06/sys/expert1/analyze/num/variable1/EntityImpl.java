package a.entity.gus06.sys.expert1.analyze.num.variable1;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160812";}


	
	
	public boolean f(Object obj) throws Exception
	{
		Map m = (Map) obj;
		
		Map pool = (Map) m.get("pool");
		Map output = (Map) m.get("output");
		Map analyze = (Map) m.get("analyze");
		Number value = (Number) m.get("value");
		
		String operator = (String) analyze.get("operator");
		Object content = analyze.get("content");
		
		if(!operator.equals("element")) return false;
		
		String name = (String) content;
		output.put(name,value);
		pool.remove(name);
		
		return true;
	}
}
