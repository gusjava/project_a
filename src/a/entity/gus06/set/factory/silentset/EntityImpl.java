package a.entity.gus06.set.factory.silentset;

import a.framework.*;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20200327";}
	
	
	public Object g() throws Exception
	{return new Set1();}
	
	
	private class Set1 implements Set
	{
		private Set set = new HashSet();
		
		public boolean add(Object obj){return set.add(obj);}
		public boolean addAll(Collection c){return set.addAll(c);}
		public void clear(){set.clear();}
		public boolean contains(Object obj){return set.contains(obj);}
		public boolean containsAll(Collection c){return set.containsAll(c);}
		public boolean isEmpty(){return set.isEmpty();}
		public Iterator iterator(){return set.iterator();}
		public boolean remove(Object obj){return set.remove(obj);}
		public boolean removeAll(Collection c){return set.removeAll(c);}
		public boolean retainAll(Collection c){return set.retainAll(c);}
		public int size() {return set.size();}
		public Object[] toArray(){return set.toArray();}
		public Object[] toArray(Object[] a){return set.toArray(a);}
		
		public String toString(){return "Set1";}
	}
}
