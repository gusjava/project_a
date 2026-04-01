package a.entity.gus06.filter.string.is.japanese.katakana;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160817";}

	public static final String BLOCKS = "KATAKANA";
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		
		for(int i=0;i<s.length();i++)
		if(!isValid(s.charAt(i))) return false;
		
		return true;
	}
	
	private boolean isValid(char c)
	{return BLOCKS.contains(Character.UnicodeBlock.of(c).toString());}
}
