package a.entity.gus06.filter.string.haschar.japanese.kana;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150526";}

	public static final String BLOCKS = "HIRAGANA;KATAKANA";
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;
		
		String str = (String) obj;
		for(int i=0;i<str.length();i++)
		if(isValid(str.charAt(i))) return true;
		
		return false;
	}
	
	private boolean isValid(char c)
	{return BLOCKS.contains(Character.UnicodeBlock.of(c).toString());}
}