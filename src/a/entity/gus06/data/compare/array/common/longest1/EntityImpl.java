package a.entity.gus06.data.compare.array.common.longest1;

import a.framework.*;
import java.util.Arrays;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160714";}



	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		return longestCommon((Object[]) t[0],(Object[]) t[1]);
	}

	
	
	private Object[] longestCommon(Object[] s1, Object[] s2)
	{
		int start = 0;
		int max = 0;
		
		int l1 = s1.length;
		int l2 = s2.length;
		
		for(int i=0;i<l1;i++)
		for(int j=0;j<l2;j++)
		{
			if(l1-i<max || l2-j<max) break;
			
			int x = 0;
			while(equals(s1[i+x],s2[j+x]))
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
		return Arrays.copyOfRange(s1,start,start+max);
	}
	
	
	private boolean equals(Object o1, Object o2)
	{
		if(o1==null && o2==null) return true;
		if(o1==null || o2==null) return false;
		return o1.equals(o2);
	}
}
