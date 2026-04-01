package a.entity.gus06.sys.expression1.apply.op._doc_prefixes;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190822";}

	public static final String T = "constant";
	
	
	private Map map;
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		// PREFIXES
		
		map.put("!","comment");
		map.put("!!","ignore");
		map.put(">","print");
		map.put(">>","println");
		map.put("*","execute");
		map.put("$","affectation (regional)");
		map.put("&","affectation (global)");
		map.put("�","affectation (local)");
	}

	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return map;
	}
}
