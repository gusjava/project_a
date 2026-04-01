package a.entity.gus06.string.transform.character.remove.eol;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(c!='\n' && c!='\r') b.append(c);
		}
		return b.toString();
	}
}
