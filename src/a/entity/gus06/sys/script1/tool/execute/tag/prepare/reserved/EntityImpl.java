package a.entity.gus06.sys.script1.tool.execute.tag.prepare.reserved;

import a.framework.*;
import java.util.Arrays;
import java.util.List;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20160121";}
	
	
	public static final String P_CONTEXT = "context";
	public static final String P_RESERVED = "reserved";
	public static final String P_PARENT = "parent";
	public static final String P_STACK = "stack";
	public static final String P_SCRIPT = "script";
	public static final String P_POOL = "pool";
	
	
	public static final List RESERVED = Arrays.asList(new String[]{
		P_RESERVED,P_CONTEXT,P_PARENT,P_STACK,P_SCRIPT,P_POOL
	});
	
	public Object g() throws Exception
	{return RESERVED;}
}
