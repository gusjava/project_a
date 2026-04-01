package a.entity.gus06.sys.script1.tool.names.tagarray;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, G, F {

	public String creationDate() {return "20160729";}
	
	public static final String[] TAGS = new String[]{
		"alias","comment","execute","output","print","println",
		"set","set0","set1","block","block0",
		"code","case","each","extends","extends1",
		"for","if","ignore","redirect","repeat","clock",
		"switch","try","until","while","call",
		"debug","else","elseif","end","include",
		"include1","parent","return","stop","timeout","run"};

	
	public Object g() throws Exception
	{
		return TAGS;
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		for(String name:TAGS) if(name.equals(s)) return true;
		return false;
	}
}
