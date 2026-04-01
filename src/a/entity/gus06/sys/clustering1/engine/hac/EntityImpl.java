package a.entity.gus06.sys.clustering1.engine.hac;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170107";}


	private Service findShortest;
	private Service scalarDivide;
	private Service findPoint;
	private Service opAdd;

	public EntityImpl() throws Exception
	{
		findShortest = Outside.service(this,"gus06.sys.clustering1.distance.shortest");
		scalarDivide = Outside.service(this,"gus06.math.tabdouble.scalar.divide");
		findPoint = Outside.service(this,"gus06.find.doublearray");
		opAdd = Outside.service(this,"gus06.math.tabdouble.op.add");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list1 = (List) o[0];
		int target = Integer.parseInt(""+o[1]);
		
		
		List list2a = new ArrayList();  //centroids
		List list2b = new ArrayList();  //sums
		List list2c = new ArrayList();  //clusters
		
		//situation initiale : chaque point est un cluster
		
		for(int i=0;i<list1.size();i++)
		{
			Object point = list1.get(i);
			list2a.add(findPoint(point));
			list2b.add(findPoint(point));
			list2c.add(toSet(i));
		}
		
		//tant que le nombre de clusters est superieur au nombre cible (target)
		//on fusionne les 2 clusters les plus proches
		
		while(list2a.size()>target)
		{
			Object[] info = (Object[]) findShortest.t(list2a);
			int n1 = ((Integer) info[0]).intValue();
			int n2 = ((Integer) info[1]).intValue();
			
			Object sum1 = list2b.get(n1);
			Object sum2 = list2b.get(n2);
			Object sumT = add(sum1,sum2);
			
			Set set1 = (Set) list2c.get(n1);
			Set set2 = (Set) list2c.get(n2);
			Set setT = mergeSet(set1,set2);
			
			int n1_ = Math.min(n1,n2);
			int n2_ = Math.max(n1,n2);
			
			list2a.remove(n2_);
			list2b.remove(n2_);
			list2c.remove(n2_);
			
			list2a.remove(n1_);
			list2b.remove(n1_);
			list2c.remove(n1_);
			
			list2a.add(centroid(sumT,setT));
			list2b.add(sumT);
			list2c.add(setT);
		}
		
		return new List[]{list2c,list2a};
	}
	
	
	private Object findPoint(Object obj) throws Exception
	{return findPoint.t(obj);}
	
	
	private Object add(Object d1, Object d2) throws Exception
	{return opAdd.t(new Object[]{d1,d2});}
	
	
	private Object centroid(Object sum, Set set) throws Exception
	{
		Integer nb = Integer.valueOf(set.size());
		return scalarDivide.t(new Object[]{sum,nb});
	}
	
	
	private Set toSet(int i)
	{
		Set s = new HashSet();
		s.add(Integer.valueOf(i));
		return s;
	}
	
	private Set mergeSet(Set s1, Set s2)
	{
		Set ss = new HashSet();
		ss.addAll(s1);
		ss.addAll(s2);
		return ss;
	}
}
