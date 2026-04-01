package a.entity.gus06.collection.comparator.tostring_i.inv;

import a.framework.*;
import java.util.Comparator;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170111";}


	private Comparator comparator = new Comparator1();

	
	public Object g() throws Exception
	{return comparator;}
	
	
	private class Comparator1 implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			String s1 = o1.toString();
			String s2 = o2.toString();
			
			String s1i = s1.toLowerCase();
			String s2i = s2.toLowerCase();
			
			int c = s2i.compareTo(s1i);
			if(c!=0) return c;
			
			return s2.compareTo(s1);
		}
	}
}
