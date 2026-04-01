package a.entity.gus06.string.transform.str.titled.inv;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160518";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("")) return "";
		
		StringBuffer b = new StringBuffer();
		boolean space = true;
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(space) c = Character.toLowerCase(c);
			space = c==' ' || c=='\t' || c=='\n';
			b.append(c);
		}
		return b.toString();
	}
}
