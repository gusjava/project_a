package a.entity.gus06.list.factory.silentlist;

import a.framework.*;
import java.util.Collection;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20191215";}
	
	
	public Object g() throws Exception
	{return new List1();}
	
	
	private class List1 implements List
	{
		private List list = new ArrayList();
		
		
		public boolean add(Object element){return list.add(element);}
		public void add(int index, Object element){list.add(index,element);}
		public boolean addAll(Collection c){return list.addAll(c);}
		public boolean addAll(int index, Collection c){return list.addAll(index,c);}
		public void clear(){list.clear();}
		public boolean contains(Object o){return list.contains(o);}
		public boolean containsAll(Collection c){return list.containsAll(c);}
		public Object get(int index){return list.get(index);}
		public int indexOf(Object o){return list.indexOf(o);}
		public boolean isEmpty(){return list.isEmpty();}
		public Iterator iterator(){return list.iterator();}
		public int lastIndexOf(Object o){return list.lastIndexOf(o);}
		public ListIterator listIterator(){return list.listIterator();}
		public ListIterator listIterator(int index){return list.listIterator(index);}
		public Object remove(int index){return list.remove(index);}
		public boolean remove(Object o){return list.remove(o);}
		public boolean removeAll(Collection c){return list.removeAll(c);}
		public boolean retainAll(Collection c){return list.retainAll(c);}
		public Object set(int index, Object element){return list.set(index,element);}
		public int size() {return list.size();}
		public List subList(int fromIndex, int toIndex){return list.subList(fromIndex,toIndex);}
		public Object[] toArray(){return list.toArray();}
		public Object[] toArray(Object[] a){return list.toArray(a);}
		
		public String toString(){return "List1";}
	}
}
