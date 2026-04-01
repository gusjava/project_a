package a.entity.gus06.sys.opposite1.set.perform;

import a.framework.*;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;

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
	{return new Set1((Set) obj);}
	
	
	
	private class Set1 implements Set, R
	{
		private Set set;
		
		public Set1(Set set)
		{this.set = set;}
		
		public int size()				{return set.size();}
		public boolean isEmpty()			{return set.isEmpty();}
		public boolean contains(Object value)		{return set.contains(value);}
		public boolean containsAll(Collection c)	{return set.containsAll(c);}
		public Iterator iterator()			{return set.iterator();}
		public Object[] toArray()			{return set.toArray();}
		public Object[] toArray(Object[] array)		{return set.toArray(array);}
		public String toString()			{return set.toString();}
		public boolean equals(Object o)			{return set.equals(o);}
		public boolean add(Object value)		{return set.add(value);}
		public boolean remove(Object value)		{return set.remove(value);}
		public boolean addAll(Collection c)		{return set.addAll(c);}
		public boolean retainAll(Collection c)		{return set.retainAll(c);}
		public boolean removeAll(Collection c)		{return set.removeAll(c);}
		public void clear()				{set.clear();}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("type")) return isOpp() ? MINUS : PLUS;
			if(key.equals("content")) return set;
				
			if(key.equals("keys")) return new String[]{"type","content"};
			throw new Exception("unknown key "+key);
		}
		
		private boolean isOpp() throws Exception
		{return !check.f(set);}
	}
}
