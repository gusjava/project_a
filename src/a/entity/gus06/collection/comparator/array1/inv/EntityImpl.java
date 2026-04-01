package a.entity.gus06.collection.comparator.array1.inv;

import a.framework.*;
import java.util.Comparator;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190821";}


	private Comparator comparator = new Comparator1();

	
	public Object g() throws Exception
	{return comparator;}
	
	
	private class Comparator1 implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			Object[] array1 = (Object[]) o1;
			Object[] array2 = (Object[]) o2;
			
			int nb = Math.min(array1.length,array2.length);
			for(int i=0;i<nb;i++)
			{
				Comparable c1 = (Comparable) array1[i];
				Comparable c2 = (Comparable) array2[i];
				int r = c2.compareTo(c1);
				if(r!=0) return r;
			}
			return 0;
		}
	}
}
