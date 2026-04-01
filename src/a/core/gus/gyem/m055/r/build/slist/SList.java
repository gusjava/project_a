package a.core.gus.gyem.m055.r.build.slist;

import a.framework.*;
import java.util.*;

public class SList extends S1 implements List, R, V, T, G, P, F {
	
	private List list;
	private String name;
	
	private Object lastElement;
	private Object lastResult;
	private int lastIndex;
	
	
	public SList(String name)
	{
		list = new ArrayList();
		this.name = name;
	}
	
	public String toString()
	{return name;}
	
	public int size()							{return list.size();}
	public boolean isEmpty()					{return list.isEmpty();}
	public int indexOf(Object value)			{return list.indexOf(value);}
	public int lastIndexOf(Object value)		{return list.lastIndexOf(value);}
	public ListIterator listIterator()			{return list.listIterator();}
	public ListIterator listIterator(int index)	{return list.listIterator(index);}
	public List subList(int index1, int index2)	{return list.subList(index1,index2);}
	public Iterator iterator()					{return list.iterator();}
	public Object[] toArray()					{return list.toArray();}
	public Object[] toArray(Object[] array)		{return list.toArray(array);}
	public boolean contains(Object value)		{return list.contains(value);}
	public boolean containsAll(Collection c)	{return list.containsAll(c);}
	public Object get(int index)				{return list.get(index);}
	public boolean equals(Object o)				{return list.equals(o);}
	
	public void clear()
	{
		list.clear();
		send(this,"clear()");
	}
	
	public boolean add(Object value)
	{
		lastElement = value;
		boolean result = list.add(value);
		if(result) send(this,"add(Object)");
		return result;
	}
	
	public void add(int index, Object value)
	{
		lastIndex = index;
		lastElement = value;
		list.add(index,value);
		send(this,"add(int,Object)");
	}
	
	public boolean remove(Object value)
	{
		lastElement = value;
		boolean result = list.remove(value);
		if(result) send(this,"remove(Object)");
		return result;
	}
	
	public Object remove(int index)
	{
		lastIndex = index;
		lastResult = list.remove(index);
		send(this,"remove(int)");
		return lastResult;
	}
	
	public boolean addAll(Collection c)
	{
		boolean result = list.addAll(c);
		if(result) send(this,"addAll(Collection)");
		return result;
	}
	
	public boolean addAll(int index, Collection c)
	{
		lastIndex = index;
		boolean result = list.addAll(index,c);
		if(result) send(this,"addAll(int,Collection)");
		return result;
	}
	
	public boolean removeAll(Collection c)
	{
		boolean result = list.removeAll(c);
		if(result) send(this,"removeAll(Collection)");
		return result;
	}
	
	public boolean retainAll(Collection c)
	{
		boolean result = list.retainAll(c);
		if(result) send(this,"retainAll(Collection)");
		return result;
	}
	
	
	public Object set(int index, Object value)
	{
		lastIndex = index;
		lastElement = value;
		lastResult = list.set(index,value);
		send(this,"set(index,Object)");
		return lastResult;
	}
	
	
	

	
	public Object r(String key) throws Exception
	{
		if(key.equals("lastElement"))return lastElement;
		if(key.equals("lastResult"))return lastResult;
		if(key.equals("lastIndex"))return new Integer(lastIndex);
		if(key.equals("name")) return name;
		if(key.equals("list"))return list;
		
		if(key.equals("keys"))
			return new String[]{"lastElement","lastResult","lastIndex","name","list"};
		
		throw new Exception("Unknown key "+key);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("lastElement")) {lastElement = obj;return;}
		if(key.equals("lastResult")) {lastResult = obj;return;}
		if(key.equals("lastIndex")) {lastIndex = ((Integer)obj).intValue();return;}
		if(key.equals("name")) {name = (String) obj;return;}
		if(key.equals("list")) {list = (List) obj;return;}

		throw new Exception("Unknown key "+key);
	}

	public boolean f(Object obj) throws Exception 		{return list.contains(obj);}
	public Object t(Object obj) throws Exception 		{return list.get(((Integer)obj).intValue());}
	public Object g() throws Exception 			{return list;}
	public void p(Object obj) throws Exception		{list = (List) obj;}
}