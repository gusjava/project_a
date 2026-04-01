package a.entity.gus06.sys.script1.analyze2.buildtag.root;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, G {
	
	public static final String T_ROOT = "root";


	public String creationDate() {return "20160121";}
	


	private Service newMap;
	
	public EntityImpl() throws Exception
	{newMap = Outside.service(this,"gus06.sys.script1.analyze1.buildtag.newmap");}

	
	
	public Object g() throws Exception
	{
		Map tag = (Map) newMap.t(T_ROOT);
		return tag;
	}
}
