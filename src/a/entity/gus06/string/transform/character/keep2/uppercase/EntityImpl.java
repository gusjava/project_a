package a.entity.gus06.string.transform.character.keep2.uppercase;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150926";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		StringBuffer b = new StringBuffer();
		
		boolean justAdded = false;
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(Character.isUpperCase(c)) {b.append(c);justAdded = true;}
			else if(justAdded) {b.append("\n");justAdded = false;}
		}
		return b.toString();
	}
}
