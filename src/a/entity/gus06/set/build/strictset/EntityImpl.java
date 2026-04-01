package a.entity.gus06.set.build.strictset;

import a.framework.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20161027";}
	
	
	public Object g() throws Exception
	{return new StrictSet();}
	
	
	public class StrictSet implements Set
	{
		private List list = new ArrayList();
	
		public boolean add(Object o)
		{
			if(contains(o)) return false;
			list.add(o);
			return true;
		}
	
		public boolean addAll(Collection c)
		{
			Iterator it = c.iterator();
			boolean added = false;
			while(it.hasNext()) if(add(it.next())) added = true;
			return added;
		}
	
		public void clear()
		{
			list.clear();
		}
	
		public boolean contains(Object o)
		{
			Iterator it = list.iterator();
			while(it.hasNext()) if(it.next()==o) return true;
			return false;
		}
		
		public boolean containsAll(Collection c)
		{
			Iterator it = c.iterator();
			while(it.hasNext()) if(!contains(it.next())) return false;
			return true;
		}
		
		public boolean isEmpty()
		{
			return list.isEmpty();
		}
	
		public Iterator iterator()
		{
			return list.iterator();
		}
	
		public boolean remove(Object o)
		{
			return list.remove(o);
		}
	
		public boolean removeAll(Collection c)
		{
			return list.removeAll(c);
		}
	
		public boolean retainAll(Collection c)
		{
			return list.retainAll(c);
		}
	
		public int size()
		{
			return list.size();
		}
	
		public Object[] toArray()
		{
			return list.toArray();
		}
	
		public Object[] toArray(Object[] a)
		{
			return list.toArray(a);
		}
	}
}