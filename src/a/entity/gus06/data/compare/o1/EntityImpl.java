package a.entity.gus06.data.compare.o1;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20161215";}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return equals(o[0],o[1]);
	}
	
	private boolean equals(Object o1, Object o2)
	{
		if(o1==null && o2==null) return true;
		if(o1==null || o2==null) return false;
		
		if(o1 instanceof Number && o2 instanceof Number)
		{
			double d1 = ((Number) o1).doubleValue();
			double d2 = ((Number) o2).doubleValue();
			return d1==d2;
		}
		
		if(o1 instanceof double[] && o2 instanceof double[])
		{
			double[] d1 = (double[]) o1;
			double[] d2 = (double[]) o2;
			
			int len1 = d1.length;
			int len2 = d2.length;
			
			if(len1!=len2) return false;
			for(int i=0;i<len1;i++) if(d1[i]!=d2[i]) return false;
			return true;
		}
		
		if(o1 instanceof double[][] && o2 instanceof double[][])
		{
			double[][] d1 = (double[][]) o1;
			double[][] d2 = (double[][]) o2;
			
			int x1 = d1.length;
			int y1 = x1>0 ? d1[0].length : 0;
			
			int x2 = d2.length;
			int y2 = x2>0 ? d2[0].length : 0;
			
			if(x1!=x2) return false;
			if(y1!=y2) return false;
			
			for(int i=0;i<x1;i++)
			for(int j=0;j<x2;j++)
			if(d1[i][j]!=d2[i][j]) return false;
			return true;
		}
		
		if(o1 instanceof float[] && o2 instanceof float[])
		{
			float[] d1 = (float[]) o1;
			float[] d2 = (float[]) o2;
			
			int len1 = d1.length;
			int len2 = d2.length;
			
			if(len1!=len2) return false;
			for(int i=0;i<len1;i++) if(d1[i]!=d2[i]) return false;
			return true;
		}
		
		if(o1 instanceof float[][] && o2 instanceof float[][])
		{
			float[][] d1 = (float[][]) o1;
			float[][] d2 = (float[][]) o2;
			
			int x1 = d1.length;
			int y1 = x1>0 ? d1[0].length : 0;
			
			int x2 = d2.length;
			int y2 = x2>0 ? d2[0].length : 0;
			
			if(x1!=x2) return false;
			if(y1!=y2) return false;
			
			for(int i=0;i<x1;i++)
			for(int j=0;j<x2;j++)
			if(d1[i][j]!=d2[i][j]) return false;
			return true;
		}
		
		if(o1 instanceof int[] && o2 instanceof int[])
		{
			int[] d1 = (int[]) o1;
			int[] d2 = (int[]) o2;
			
			int len1 = d1.length;
			int len2 = d2.length;
			
			if(len1!=len2) return false;
			for(int i=0;i<len1;i++) if(d1[i]!=d2[i]) return false;
			return true;
		}
		
		if(o1 instanceof int[][] && o2 instanceof int[][])
		{
			int[][] d1 = (int[][]) o1;
			int[][] d2 = (int[][]) o2;
			
			int x1 = d1.length;
			int y1 = x1>0 ? d1[0].length : 0;
			
			int x2 = d2.length;
			int y2 = x2>0 ? d2[0].length : 0;
			
			if(x1!=x2) return false;
			if(y1!=y2) return false;
			
			for(int i=0;i<x1;i++)
			for(int j=0;j<x2;j++)
			if(d1[i][j]!=d2[i][j]) return false;
			return true;
		}
		
		if(o1 instanceof long[] && o2 instanceof long[])
		{
			long[] d1 = (long[]) o1;
			long[] d2 = (long[]) o2;
			
			int len1 = d1.length;
			int len2 = d2.length;
			
			if(len1!=len2) return false;
			for(int i=0;i<len1;i++) if(d1[i]!=d2[i]) return false;
			return true;
		}
		
		if(o1 instanceof long[][] && o2 instanceof long[][])
		{
			long[][] d1 = (long[][]) o1;
			long[][] d2 = (long[][]) o2;
			
			int x1 = d1.length;
			int y1 = x1>0 ? d1[0].length : 0;
			
			int x2 = d2.length;
			int y2 = x2>0 ? d2[0].length : 0;
			
			if(x1!=x2) return false;
			if(y1!=y2) return false;
			
			for(int i=0;i<x1;i++)
			for(int j=0;j<x2;j++)
			if(d1[i][j]!=d2[i][j]) return false;
			return true;
		}
		
		if(o1 instanceof Object[] && o2 instanceof Object[])
		{
			Object[] d1 = (Object[]) o1;
			Object[] d2 = (Object[]) o2;
			
			int len1 = d1.length;
			int len2 = d2.length;
			
			if(len1!=len2) return false;
			for(int i=0;i<len1;i++) if(!equals(d1[i],d2[i])) return false;
			return true;
		}
		
		if(o1 instanceof Object[][] && o2 instanceof Object[][])
		{
			Object[][] d1 = (Object[][]) o1;
			Object[][] d2 = (Object[][]) o2;
			
			int x1 = d1.length;
			int y1 = x1>0 ? d1[0].length : 0;
			
			int x2 = d2.length;
			int y2 = x2>0 ? d2[0].length : 0;
			
			if(x1!=x2) return false;
			if(y1!=y2) return false;
			
			for(int i=0;i<x1;i++)
			for(int j=0;j<x2;j++)
			if(!equals(d1[i][j],d2[i][j])) return false;
			return true;
		}
		
		if(o1 instanceof List && o2 instanceof List)
		{
			List d1 = (List) o1;
			List d2 = (List) o2;
			
			int len1 = d1.size();
			int len2 = d2.size();
			
			if(len1!=len2) return false;
			for(int i=0;i<len1;i++) if(!equals(d1.get(i),d2.get(i))) return false;
			return true;
		}
		
		return o1.equals(o2);
	}
}
