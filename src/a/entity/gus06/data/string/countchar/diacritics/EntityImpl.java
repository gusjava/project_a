package a.entity.gus06.data.string.countchar.diacritics;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220904";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		int count = 0;
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(DIACRITICS.isDiacritic(c)) count++;
		}
		return Integer.valueOf(count);
	}
}