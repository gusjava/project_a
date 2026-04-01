package a.entity.gus06.collection.comparator.length1.inv;

import a.framework.*;
import java.util.Comparator;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20240302";}
	
	public Object g() throws Exception
	{return new Comparator1();}
	
	private class Comparator1 implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			String s1 = (String) o2;
			String s2 = (String) o1;
			int r = s2.length()-s1.length();
			return r!=0?r:s1.compareTo(s2);
		}
	}
}