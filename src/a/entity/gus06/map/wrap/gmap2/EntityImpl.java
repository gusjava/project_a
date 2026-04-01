package a.entity.gus06.map.wrap.gmap2;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191114";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof G) return ((G) obj).g();
		
		if(obj instanceof Map1) return obj;
		if(obj instanceof List1) return obj;
		
		if(obj instanceof Map) return new Map1((Map) obj);
		if(obj instanceof List) return new List1((List) obj);
		
		return obj;
	}
	
	private Object t_(Object obj)
	{
		try{return t(obj);}
		catch(Exception e)
		{Outside.err(this,"t_(Object)",e);}
		return obj;
	}
	
	
	
	private class Map1 implements Map
	{
		private Map map;
		
		public Map1(Map map)
		{this.map = map;}
		
		public int size() 				{return map.size();}
		public boolean isEmpty() 			{return map.isEmpty();}
		public boolean containsKey(Object key) 		{return map.containsKey(key);}
		public boolean containsValue(Object value) 	{return map.containsValue(value);}
		public Set keySet()				{return map.keySet();}
		public Collection values()			{return map.values();}
		public Set entrySet() 				{return map.entrySet();}
		public boolean equals(Object o)			{return map.equals(o);}
		public Object put(Object key, Object value)	{return map.put(key,value);}
		public Object remove(Object key)		{return map.remove(key);}
		public void putAll(Map m)			{map.putAll(m);}
		public void clear()				{map.clear();}
		
		public Object get(Object key)			{return t_(map.get(key));}
	}
	
	
	
	private class List1 implements List
	{
		private List list;
		
		public List1(List list)
		{this.list = list;}
		
		public int size()				{return list.size();}
		public boolean isEmpty()			{return list.isEmpty();}
		public void clear()				{list.clear();}
		public boolean add(Object obj)			{return list.add(obj);}
		public void add(int index, Object element)	{list.add(index,element);}
		public boolean addAll(Collection c)		{return list.addAll(c);}
		public boolean addAll(int index, Collection c)	{return list.addAll(c);}
		public boolean contains(Object o)		{return list.contains(o);}
		public boolean containsAll(Collection c)		{return list.containsAll(c);}
		public int indexOf(Object o)			{return list.indexOf(o);}
		public Iterator iterator()			{return list.iterator();}
		public int lastIndexOf(Object o)		{return list.lastIndexOf(o);}
		public ListIterator listIterator()		{return list.listIterator();}
		public ListIterator listIterator(int index)	{return list.listIterator(index);}
		public Object remove(int index)			{return list.remove(index);}
		public boolean remove(Object o)			{return list.remove(o);}
		public boolean removeAll(Collection c)		{return list.removeAll(c);}
		public boolean retainAll(Collection c)		{return list.retainAll(c);}
		public Object set(int index, Object element)	{return list.set(index,element);}
		public List subList(int fromIndex, int toIndex)	{return list.subList(fromIndex,toIndex);}
		public Object[] toArray()			{return list.toArray();}
		public Object[] toArray(Object[] a)		{return list.toArray(a);}
		
		public Object get(int index)			{return t_(list.get(index));}
	}
}
