package a.entity.gus06.string.transform.conway.lookandsay;

import a.framework.*;

public class EntityImpl implements T {

	public String creationDate() {return "20251204";}

	public Object t(Object obj) throws Exception
	{
		if(obj == null) return "";
		String s = (String) obj;
		StringBuilder sb = new StringBuilder();
		
		int count = 1;
		char prev = s.charAt(0);
		
		for(int i = 1; i < s.length(); i++)
		{
			char curr = s.charAt(i);
			if(curr != prev)
			{
				sb.append(count).append(prev);
				prev = curr;
				count = 1;
			}
			else count++;
		}
		sb.append(count).append(prev);
		
		return sb.toString();
	}
}