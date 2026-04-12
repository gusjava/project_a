package a.entity.gus06.string.transform.javasrc.minimize;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251204";}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		StringBuilder b = new StringBuilder();
		boolean inString = false;
	
		for(int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
	
			if(c == '"')
			{
				inString = !inString;
				b.append(c);
				continue;
			}
			if(inString)
			{
				b.append(c);
				continue;
			}
			if(Character.isWhitespace(c))
			{
				int len = b.length();
				if(len == 0 || b.charAt(len - 1) != ' ')
					b.append(' ');
			}
			else
			{
				b.append(c);
			}
		}
	
		return b.toString();
	}
}