package a.entity.gus06.collection.comparator.smart1;

import a.framework.*;
import java.util.Comparator;
import java.util.Collection;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20220904";}


	private Comparator comparator = new Comparator1();

	
	public Object g() throws Exception
	{return comparator;}
	
	
	private class Comparator1 implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			Integer size1 = ((Collection) o1).size();
			Integer size2 = ((Collection) o1).size();
			
			return size1.compareTo(size2);
		}
	}
}
