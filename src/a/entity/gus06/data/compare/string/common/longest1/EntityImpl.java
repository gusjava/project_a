package a.entity.gus06.data.compare.string.common.longest1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150910";}



	public Object t(Object obj) throws Exception
	{
		String[] t = (String[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		return longestCommonSubstring(t[0],t[1]);
	}

	
	
	private String longestCommonSubstring(String s1, String s2)
	{
		int start = 0;
		int max = 0;
		
		int l1 = s1.length();
		int l2 = s2.length();
		
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		
		for(int i=0;i<l1;i++)
		for(int j=0;j<l2;j++)
		{
			if(l1-i<max || l2-j<max) break;
			
			int x = 0;
			while(c1[i+x]==c2[j+x])
			{
				x++;
				if(i+x>=l1 || j+x>=l2) break;
			}
			if(x>max)
			{
				max = x;
				start = i;
			}
		}
		return s1.substring(start,start+max);
	}
}
