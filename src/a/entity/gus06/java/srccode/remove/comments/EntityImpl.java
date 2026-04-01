package a.entity.gus06.java.srccode.remove.comments;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170220";}

	public static final String REGEX_COMMENT1 = "(?sm)/\\*.*?\\*/";
	public static final String REGEX_COMMENT2 = "(?-sm)[ \t]*//.*";
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String src = (String) obj;
		
		return src.replaceAll(REGEX_COMMENT1," ").replaceAll(REGEX_COMMENT2," ");
	}
}
