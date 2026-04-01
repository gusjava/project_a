package a.entity.gus06.sys.ai1.genetics.tsp.crossover.ox;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170427";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list1 = (List) o[0];
		List list2 = (List) o[1];
		
		int len1 = list1.size();
		int len2 = list2.size();
		
		if(len1!=len2) throw new Exception("Lists are not the same size: "+len1+" and "+len2);
		
		int a1 = random(len1);
		int a2 = random(len1);
		
		int b1 = Math.min(a1,a2);
		int b2 = Math.max(a1,a2);
		
		int len0 = len1-b2-1+b1;
		
		List k1 = list1.subList(b1,b2+1);
		List k2 = list2.subList(b1,b2+1);
		
		List j1 = new ArrayList(list1);
		List j2 = new ArrayList(list2);
		
		j1.removeAll(k2);
		j2.removeAll(k1);
		
		List output1 = new ArrayList();
		List output2 = new ArrayList();
		
		output1.addAll(j1.subList(0,b1));
		output1.addAll(k2);
		output1.addAll(j1.subList(b1,len0));
		
		output2.addAll(j2.subList(0,b1));
		output2.addAll(k1);
		output2.addAll(j2.subList(b1,len0));
		
		return new Object[]{output1,output2};
	}
	
	
	
	private int random(int limit)
	{return (int) (Math.random()*limit);}
}
