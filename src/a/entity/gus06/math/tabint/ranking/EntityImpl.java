package a.entity.gus06.math.tabint.ranking;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}

	
	public Object t(Object obj) throws Exception
	{
		int[] t = (int[]) obj;
		int n = t.length;
		
		int[] ranking = new int[n];
		for(int i=0;i<n;i++)
		{
			int r = 1;
			for(int j=0;j<n;j++)
			if(j!=i && t[j]>t[i]) r++;
			ranking[i] = r;
		}
		return ranking;
	}
}
