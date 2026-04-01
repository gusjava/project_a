package a.entity.gus06.string.transform.character.remove.kanji;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250803";}
	
	public static final String BLOCKS = "CJK_UNIFIED_IDEOGRAPHS";
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(!isValid(c)) b.append(c);
		}
		return b.toString();
	}
	
	
	private boolean isValid(char c)
	{return BLOCKS.contains(Character.UnicodeBlock.of(c).toString());}
}