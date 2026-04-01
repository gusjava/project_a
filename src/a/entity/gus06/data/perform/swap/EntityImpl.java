package a.entity.gus06.data.perform.swap;

import a.framework.*;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170423";}


	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Object n1 = o[1];
		Object n2 = o[2];
		
		int[] indexes = findIndexes(value,n1,n2);
		
		if(n1.equals("random") || n2.equals("random"))
		{
			int r = 0;
			while(indexes[0]==indexes[1] && r<10)
			{
				indexes = findIndexes(value,n1,n2);
				r++;
			}
		}
		
		if(value instanceof StringBuffer)	{handleSwap((StringBuffer) value, indexes[0], indexes[1]);return;}
		if(value instanceof List)		{handleSwap((List) value, indexes[0], indexes[1]);return;}
		if(value instanceof Object[])		{handleSwap((Object[]) value, indexes[0], indexes[1]);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object value = o[0];
		Object n1 = o[1];
		Object n2 = o[2];
		
		int[] indexes = findIndexes(value,n1,n2);
		
		if(n1.equals("random") || n2.equals("random"))
		{
			int r = 0;
			while(indexes[0]==indexes[1] && r<10)
			{
				indexes = findIndexes(value,n1,n2);
				r++;
			}
		}
		
		if(value instanceof String) return performSwap((String) value, indexes[0], indexes[1]);
		if(value instanceof List) return performSwap((List) value, indexes[0], indexes[1]);
		if(value instanceof Object[]) return performSwap((Object[]) value, indexes[0], indexes[1]);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private int[] findIndexes(Object value, Object n1, Object n2) throws Exception
	{
		Integer k1 = (Integer) ruleToIndex.t(new Object[]{value,n1});
		Integer k2 = (Integer) ruleToIndex.t(new Object[]{value,n2});
		
		if(k1==null) throw new Exception("Invalid index rule: "+n1);
		if(k2==null) throw new Exception("Invalid index rule: "+n2);
		
		return new int[]{k1.intValue(),k2.intValue()};
	}
	
	
	
	
	
	private void handleSwap(StringBuffer b, int n1, int n2) throws Exception
	{
		char c1 = ' ';
		char c2 = ' ';
		
		for(int i=0;i<b.length();i++)
		{
			if(i==n1) c1 = b.charAt(i);
			if(i==n2) c2 = b.charAt(i);
		}
		
		b.setCharAt(n1,c2);
		b.setCharAt(n2,c1);
	}
	
	private void handleSwap(List l, int n1, int n2)
	{
		Collections.swap(l,n1,n2);
	}
	
	private void handleSwap(Object[] t, int n1, int n2) throws Exception
	{
		Object c1 = null;
		Object c2 = null;
		
		for(int i=0;i<t.length;i++)
		{
			if(i==n1) c1 = t[i];
			if(i==n2) c2 = t[i];
		}
		
		t[n1] = c2;
		t[n2] = c1;
	}
	
	
	
	
	private String performSwap(String s, int n1, int n2) throws Exception
	{
		StringBuffer b = new StringBuffer(s);
		handleSwap(b,n1,n2);
		return b.toString();
	}
	
	private List performSwap(List l, int n1, int n2)
	{
		List l1 = new ArrayList(l);
		handleSwap(l,n1,n2);
		return l1;
	}
	
	private Object[] performSwap(Object[] t, int n1, int n2)
	{
		Object[] t1 = new Object[t.length];
		
		Object c1 = null;
		Object c2 = null;
		
		for(int i=0;i<t.length;i++)
		{
			if(i==n1) c1 = t[i];
			else if(i==n2) c2 = t[i];
			else t1[i] = t[i];
		}
		
		t1[n1] = c2;
		t1[n2] = c1;
		return t1;
	}
}
