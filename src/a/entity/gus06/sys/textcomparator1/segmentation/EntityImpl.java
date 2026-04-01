package a.entity.gus06.sys.textcomparator1.segmentation;

import a.framework.*;
import java.util.Objects;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190613";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String[] lines1 = (String[]) o[0];
		String[] lines2 = (String[]) o[1];
		
		return segment(lines1,lines2);
	}
	
	
	private Object[] segment(String[] lines1, String[] lines2)
	{
		int nb1 = lines1.length;
		int nb2 = lines2.length;
		int nb = Math.min(nb1,nb2);

		int startOffset = nb;
		for(int i=0;i<nb;i++)
		{
			String s1 = lines1[i];
			String s2 = lines2[i];
			if(!Objects.equals(s1,s2))
			{
				startOffset = i;
				break;
			}
		}
		
		if(nb1==nb2 && startOffset==nb) return null;
		
		int endOffset = 0;
		for(int i=0;i<nb-startOffset;i++)
		{
			String s1 = lines1[nb1-1-i];
			String s2 = lines2[nb2-1-i];
			if(!Objects.equals(s1,s2))
			{
				endOffset = i;
				break;
			}
		}
		
		int[] pos1 = new int[nb1];
		int[] pos2 = new int[nb2];
		
		for(int i=0;i<nb1;i++) pos1[i] = -1;
		for(int i=0;i<nb2;i++) pos2[i] = -1;
		
		for(int i=0;i<startOffset;i++)
		{
			pos1[i] = i;
			pos2[i] = i;
		}
		
		for(int i=0;i<endOffset;i++)
		{
			int x1 = nb1-1-i;
			int x2 = nb2-1-i;
			
			pos1[x1] = x2;
			pos2[x2] = x1;
		}
		
		int start1 = startOffset;
		int end1 = nb1-endOffset;
		
		int start2 = startOffset;
		int end2 = nb2-endOffset;
		
		handleRange(lines1,lines2,pos1,pos2,start1,end1,start2,end2);
		
		return new Object[]{pos1,pos2};
	}
	
	
	private void handleRange(String[] lines1, String[] lines2, int[] pos1, int[] pos2, int start1, int end1, int start2, int end2)
	{
		int t1 = start1;
		int t2 = start2;
		int max = 0;
		
		for(int i=start1;i<end1;i++)
		for(int j=start2;j<end2;j++)
		{
			int x = 0;
			while(Objects.equals(lines1[i+x],lines2[j+x]))
			{
				x++;
				if(i+x>=end1 || j+x>=end2) break;
			}
			if(x>max)
			{
				max = x;
				t1 = i;
				t2 = j;
			}
			else if(x==max)
			{
				int k1 = Math.abs(t1-t2+start1+start2);
				int k2 = Math.abs(i-j+start1+start2);
				
				if(k2<k1)
				{
					t1 = i;
					t2 = j;
				}
			}
		}
		
		if(max==0) return;
		
		for(int i=0;i<max;i++)
		{
			int x1 = t1+i;
			int x2 = t2+i;
			
			pos1[x1] = x2;
			pos2[x2] = x1;
		}
		
		if(t1>start1 && t2>start2)
			handleRange(lines1,lines2,pos1,pos2,start1,t1,start2,t2);
		if(t1+max<end1 && t2+max<end2)
			handleRange(lines1,lines2,pos1,pos2,t1+max,end1,t2+max,end2);
	}
}
