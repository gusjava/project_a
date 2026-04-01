package a.entity.gus06.string.transform.character.keep.diacritics;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150927";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(DIACRITICS.isDiacritic(c)) b.append(c);
		}
		return b.toString();
	}
}
