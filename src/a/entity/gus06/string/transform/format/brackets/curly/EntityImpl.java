package a.entity.gus06.string.transform.format.brackets.curly;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160107";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return s.replace("\\","\\\\").replace("{","\\{").replace("}","\\}");
	}
}