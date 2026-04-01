package a.entity.gus06.list.list1;

import a.framework.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150910";}

	
	
	public Object t(Object obj) throws Exception
	{return new List1((String) obj);}
	
	
	
	
	private class List1 implements List
	{
		private List l;
		private String name;
		
		public List1(String name)
		{
			this.name = name;
			l = new ArrayList();
		}
		
		public int size()
		{return l.size();}
		
		public boolean isEmpty()
		{return l.isEmpty();}
		
		public void clear()
		{l.clear();}
		
		public boolean add(Object obj)
		{return l.add(obj);}
		
		public void add(int index, Object element)
		{l.add(index,element);}
		
		public boolean addAll(Collection c)
		{return l.addAll(c);}
		
		public boolean addAll(int index, Collection c)
		{return l.addAll(c);}
		
		public boolean contains(Object o)
		{return l.contains(o);}
		
		public boolean containsAll(Collection c)
		{return l.containsAll(c);}
		
		public Object get(int index)
		{return l.get(index);}
		
		public int indexOf(Object o)
		{return l.indexOf(o);}
		
		public Iterator iterator()
		{return l.iterator();}
		
		public int lastIndexOf(Object o)
		{return l.lastIndexOf(o);}
		
		public ListIterator listIterator()
		{return l.listIterator();}
		
		public ListIterator listIterator(int index)
		{return l.listIterator(index);}
		
		public Object remove(int index)
		{return l.remove(index);}
		
		public boolean remove(Object o)
		{return l.remove(o);}
		
		public boolean removeAll(Collection c)
		{return l.removeAll(c);}
		
		public boolean retainAll(Collection c)
		{return l.retainAll(c);}
		
		public Object set(int index, Object element)
		{return l.set(index,element);}
		
		public List subList(int fromIndex, int toIndex)
		{return l.subList(fromIndex,toIndex);}
		
		public Object[] toArray()
		{return l.toArray();}
		
		public Object[] toArray(Object[] a)
		{return l.toArray(a);}
		
		public String toString()
		{return name;}
	}
}
