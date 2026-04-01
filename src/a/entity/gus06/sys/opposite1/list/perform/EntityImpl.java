package a.entity.gus06.sys.opposite1.list.perform;

import a.framework.*;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160721";}
	
	public static final String MINUS = "MINUS";
	public static final String PLUS = "PLUS";
	
	
	private Service check;
	
	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.sys.opposite1.check");
	}
	
	
	
	
	
	public Object t(Object obj) throws Exception
	{return new List1((List) obj);}
	
	
	
	private class List1 implements List, R
	{
		private List list;
		
		public List1(List list)
		{this.list = list;}
		
		public boolean add(Object value)		{return list.add(value);}
		public void add(int index, Object value)	{list.add(index,value);}
		public boolean addAll(Collection c)		{return list.addAll(c);}
		public boolean addAll(int index, Collection c)	{return list.addAll(index,c);}
		public void clear()				{list.clear();}
		public boolean contains(Object value)		{return list.contains(value);}
		public boolean containsAll(Collection c)	{return list.containsAll(c);}
		public Object get(int index)			{return list.get(index);}
		public int indexOf(Object o)			{return list.indexOf(o);}
		public boolean isEmpty()			{return list.isEmpty();}
		public Iterator iterator()			{return list.iterator();}
		public int lastIndexOf(Object o)		{return list.lastIndexOf(o);}
		public ListIterator listIterator()		{return list.listIterator();}
		public ListIterator listIterator(int index)	{return list.listIterator(index);}
		public Object remove(int index)			{return list.remove(index);}
		public boolean remove(Object value)		{return list.remove(value);}
		public boolean removeAll(Collection c)		{return list.removeAll(c);}
		public boolean retainAll(Collection c)		{return list.retainAll(c);}
		public Object set(int index, Object value)	{return list.set(index,value);}
		public int size()				{return list.size();}
		public List subList(int fromIndex, int toIndex)	{return list.subList(fromIndex,toIndex);}
		public Object[] toArray()			{return list.toArray();}
		public Object[] toArray(Object[] a)		{return list.toArray(a);}
		public String toString()			{return list.toString();}
		public boolean equals(Object o)			{return list.equals(o);}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("type")) return isOpp() ? MINUS : PLUS;
			if(key.equals("content")) return list;
				
			if(key.equals("keys")) return new String[]{"type","content"};
			throw new Exception("unknown key "+key);
		}
		
		private boolean isOpp() throws Exception
		{return !check.f(list);}
	}
}
