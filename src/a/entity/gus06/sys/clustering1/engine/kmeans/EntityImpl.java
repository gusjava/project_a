package a.entity.gus06.sys.clustering1.engine.kmeans;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180502";}


	private Service findShortest;
	private Service computeCentroid;
	private Service randomGroup;
	private Service pointEquals;

	public EntityImpl() throws Exception
	{
		findShortest = Outside.service(this,"gus06.sys.clustering1.distance.shortest2");
		computeCentroid = Outside.service(this,"gus06.math.tabdouble.set.centroid");
		randomGroup = Outside.service(this,"gus06.list.findall.random");
		pointEquals = Outside.service(this,"gus06.math.tabdouble.equals");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list1 = (List) o[0];
		int target = Integer.parseInt(""+o[1]);
		
		//centroids : on definit des centres aleatoires
		
		List list2a = (List) randomGroup.t(new Object[]{list1,Integer.valueOf(target)});
		
		//clusters : on initialise les clusters vides
		
		List list2c = new ArrayList();
		for(int i=0;i<target;i++)
		list2c.add(new HashSet());
		
		// clusters : on attribue a chaque cluster les points les plus proches de chaque centre
		
		for(int i=0;i<list1.size();i++)
		{
			Object point = list1.get(i);
			Object[] info = findShortest(list2a,point);
			int index = ((Integer) info[0]).intValue();
			((Set) list2c.get(index)).add(Integer.valueOf(i));
		}
		
		boolean stable = false;
		while(!stable)
		{
			boolean changed = false;
			for(int i=0;i<target;i++)
			{
				Set cluster = (Set) list2c.get(i);
				Set cluster2 = (Set) indexesToPoints(cluster,list1);
				
				Object centroid = computeCentroid.t(cluster2);
				Object pCentroid = list2a.set(i,centroid);
				if(!equals(centroid,pCentroid)) changed = true;
				
				cluster.clear();
			}
			for(int i=0;i<list1.size();i++)
			{
				Object point = list1.get(i);
				Object[] info = findShortest(list2a,point);
				int index = ((Integer) info[0]).intValue();
				((Set) list2c.get(index)).add(Integer.valueOf(i));
			}
			stable = !changed;
		}
		return new List[]{list2c,list2a};
	}
	
	
	
	private Set indexesToPoints(Set cluster, List list1)
	{
		Set cluster2 = new HashSet();
		Iterator it = cluster.iterator();
		while(it.hasNext())
		{
			Integer n = (Integer) it.next();
			Object point = list1.get(n.intValue());
			cluster2.add(point);
		}
		return cluster2;
	}
	
	
	private boolean equals(Object point1, Object point2) throws Exception
	{return pointEquals.f(new Object[]{point1,point2});}
	
	private Object[] findShortest(List list2a, Object point) throws Exception
	{return (Object[]) findShortest.t(new Object[]{list2a,point});}
}
