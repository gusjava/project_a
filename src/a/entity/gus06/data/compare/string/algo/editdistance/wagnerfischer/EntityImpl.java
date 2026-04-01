package a.entity.gus06.data.compare.string.algo.editdistance.wagnerfischer;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}



	public Object t(Object obj) throws Exception
	{
		String[] s = (String[]) obj;
		if(s.length!=2) throw new Exception("Wrong data number: "+s.length);
		return getLevenshteinDistance1(s[0],s[1]);
	}


	
	private Integer getLevenshteinDistance1(String s1, String s2)
	{	
		int l1 = s1.length()+1;
		int l2 = s2.length()+1;
		int[][] array = new int[l1][l2];

		for(int i=0;i<l1;i++) array[i][0] = i;
		for(int i=1;i<l2;i++) array[0][i] = i;

		for(int i=1;i<l1;i++) for(int j=1;j<l2;j++)
		{
			int v1 = array[i-1][j]+1;
			int v2 = array[i][j-1]+1;
			
			int v3 = array[i-1][j-1];
			if(s1.charAt(i-1)!=s2.charAt(j-1)) v3++;
			
			array[i][j] = Math.min(Math.min(v1,v2),v3);
		}
		return Integer.valueOf(array[l1-1][l2-1]);
	}
}
