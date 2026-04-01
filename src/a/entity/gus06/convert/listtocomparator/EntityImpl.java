package a.entity.gus06.convert.listtocomparator;

import a.framework.*;
import java.util.List;
import java.util.Comparator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160510";}
	
	
	public Object t(Object obj) throws Exception
	{return new Comparator1((List) obj);}
	
	
	
	private class Comparator1 implements Comparator
	{
		private List l;
		public Comparator1(List l){this.l = l;}
		
		public int compare(Object o1, Object o2)
		{
			if(l==null) return defaultCompare(o1,o2);
			
			int index1 = l.indexOf(o1);
			int index2 = l.indexOf(o2);
			
			if(index1==-1 || index2==-1) return defaultCompare(o1,o2);
			if(index1==index2) return defaultCompare(o1,o2);
			
			return index2-index1;
		}
		
		private int defaultCompare(Object o1, Object o2)
		{return ((Comparable) o1).compareTo((Comparable) o2);}
	}
}
