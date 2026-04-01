package a.entity.gus06.sys.script1.analyze2.finduntil;

import a.framework.*;

public class EntityImpl implements Entity, T, G {
	
	public static final String V_END = "end";


	public String creationDate() {return "20150829";}

	
	public static final String[] LIST = new String[] {
		"block",
		"block0",
		"code",
		"each",
		"extends",
		"extends1",
		"for",
		"if",
		"ignore",
		"redirect",
		"repeat",
		"switch",
		"root",
		"try",
		"while",
		"until",
		"clock",
		"timeout",
		"run"
	};
	
	
	public Object g() throws Exception
	{
		return LIST;
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		
		if(inList(name)) return V_END;
		if(name.startsWith("_")) return V_END;
		
		return null;
	}
	
	
	private boolean inList(String name)
	{
		for(String s:LIST) if(s.equals(name)) return true;
		return false;
	}
}