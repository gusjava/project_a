package a.entity.gus06.string.encoding.forbiddenchar.decode;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220524";}
	
	
	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String input = o[0];
		String rule = o[1];
		
		int len = rule.length();
		if(len<3 || len%2==0) throw new Exception("Invalid rule length: "+len);
		
		if(input==null) return null;
		
		char escapChar = rule.charAt(0);
		
		boolean found = false;
		StringBuffer b = new StringBuffer();
		for(int i=0;i<input.length();i++)
		{
			char c = input.charAt(i);
			int n = rule.indexOf(c);
			if(n==-1 || found)
			{
				b.append(c);
				found = false;
			}
			else if(n==0)
			{
				found = true;
			}
			else if(n%2==1)
			{
				b.append(rule.charAt(n+1));
			}
			else if(n%2==0)
			{
				b.append(c);
			}
		}
		return b.toString();
	}
}