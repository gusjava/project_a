package a.entity.gus06.sys.expression1.apply.op._res_icons;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160530";}

	public static final String T = "constant";
	
	private Service findMap;
	private Map map;
		
	public EntityImpl() throws Exception
	{
		findMap = Outside.service(this,"gus06.app.jarfile.listing.resources.iconmap.gyem");
		map = (Map) findMap.g();
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return map;
	}
}
