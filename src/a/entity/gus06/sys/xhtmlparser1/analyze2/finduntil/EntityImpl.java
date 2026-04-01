package a.entity.gus06.sys.xhtmlparser1.analyze2.finduntil;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170131";}

	
	
	
	public Object t(Object obj) throws Exception
	{
		String name = (String) obj;
		if(isAutoClosing(name)) return null;
		return "/"+name;
	}
	
	
	private boolean isAutoClosing(String name)
	{
		// <area /> , <br /> , <hr /> , <img /> , <input /> , <link /> , <meta /> , <param /> 
		
		return name.equals("area") || 
			name.equals("br") || 
			name.equals("hr") || 
			name.equals("img") || 
			name.equals("input") || 
			name.equals("link") || 
			name.equals("meta") ||
			name.equals("param");
	}
	
}