package a.entity.gus06.sys.ai1.genetics.tsp.crossover.ox.old;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170429";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		int[] list1 = (int[]) o[0];
		int[] list2 = (int[]) o[1];
		
		int len1 = list1.length;
		int len2 = list2.length;
		
		if(len1!=len2) throw new Exception("Arrays are not the same length: "+len1+" and "+len2);
		
		int a1 = random(len1);
		int a2 = random(len1);
		
		int b1 = Math.min(a1,a2);
		int b2 = Math.max(a1,a2);
		
		
		int[] output1 = new int[len1];
		int[] output2 = new int[len1];
		
		Set<Integer> s1 = new HashSet<>();
		Set<Integer> s2 = new HashSet<>();
		
		for(int i=b1;i<=b2;i++)
		{
			output1[i] = list2[i];
			output2[i] = list1[i];
			
			s1.add(output1[i]);
			s2.add(output2[i]);
		}
		
		
		int k = b2+1;
		if(k==len1) k = 0;
		
		for(int i=b2+1;i<len1;i++)
		if(!s1.contains(list1[i]))
		{
			output1[k] = list1[i];
			k++;
			if(k==len1) k = 0;
		}
		for(int i=0;i<=b2;i++)
		if(!s1.contains(list1[i]))
		{
			output1[k] = list1[i];
			k++;
			if(k==len1) k = 0;
		}
		
		
		k = b2+1;
		if(k==len1) k = 0;
		
		for(int i=b2+1;i<len1;i++)
		if(!s2.contains(list2[i]))
		{
			output2[k] = list2[i];
			k++;
			if(k==len1) k = 0;
		}
		for(int i=0;i<=b2;i++)
		if(!s2.contains(list2[i]))
		{
			output2[k] = list2[i];
			k++;
			if(k==len1) k = 0;
		}
		
		
		return new Object[]{output1,output2};
	}
	
	
	
	private int random(int limit)
	{return (int) (Math.random()*limit);}
}
