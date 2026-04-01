package a.entity.gus06.map.deep.put.replace;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151204";}


	private Service nextData;
	private Service ruleToIndex;
	private Service performAppend;
	private Service performAppend0;

	public EntityImpl() throws Exception
	{
		nextData = Outside.service(this,"gus06.map.deep.nextdata1");
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex1");
		performAppend = Outside.service(this,"gus06.data.perform.append");
		performAppend0 = Outside.service(this,"gus06.data.perform.append0");
	}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3)	throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		String key = (String) o[1];
		Object value = o[2];
		
		String[] n = key.split("\\.");
		String lastKey = n[n.length-1];
		
		for(int i=0;i<n.length-1;i++)
		data = nextData(data,n[i]);
		
		putValue(data,lastKey,value);
	}
	
	
	private Object nextData(Object data, String key) throws Exception
	{return nextData.t(new Object[]{data,key});}
	
	
	
	
	private void putValue(Object data, String k, Object value) throws Exception
	{
		if(data instanceof Map)
		{
			Map m = (Map) data;
			m.put(k,value);
			return;
		}
		if(data instanceof Object[])
		{
			Object[] t = (Object[]) data;
			int index = Integer.parseInt(k);
			while(index<0) index += t.length;
			
			t[index] = value;
			return;
		}
		if(data instanceof int[])
		{
			int[] t = (int[]) data;
			int index = Integer.parseInt(k);
			while(index<0) index += t.length;
			
			t[index] = toInt(value);
			return;
		}
		if(data instanceof long[])
		{
			long[] t = (long[]) data;
			int index = Integer.parseInt(k);
			while(index<0) index += t.length;
			
			t[index] = toLong(value);
			return;
		}
		if(data instanceof double[])
		{
			double[] t = (double[]) data;
			int index = Integer.parseInt(k);
			while(index<0) index += t.length;
			
			t[index] = toDouble(value);
			return;
		}
		if(data instanceof float[])
		{
			float[] t = (float[]) data;
			int index = Integer.parseInt(k);
			while(index<0) index += t.length;
			
			t[index] = toFloat(value);
			return;
		}
		if(data instanceof List)
		{
			List l = (List) data;
			
			if(k.equals("after"))
			{
				l.add(value);
				return;
			}
			if(k.equals("before"))
			{
				l.add(0,value);
				return;
			}
			
			Integer n = (Integer) ruleToIndex.t(new Object[]{l,k});
			if(n==null) return;
			
			int index = n.intValue();
			while(index>=l.size()) l.add(null);
			l.set(index,value);
			return;
		}
		if(data instanceof JTextComponent)
		{
			if(k.equals("after"))
			{
				performAppend.p(new Object[]{data,value});
				return;
			}
			if(k.equals("before"))
			{
				performAppend0.p(new Object[]{data,value});
				return;
			}
		}
		if(data instanceof Set)
		{
			Set s = (Set) data;
			s.add(value);
			return;
		}
		if(data instanceof V)
		{
			V v = (V) data;
			v.v(k,value);
			return;
		}
		if(data instanceof P)
		{
			P p = (P) data;
			if(k.equals("p")) {p.p(value);return;}
			throw new Exception("p is expected for P object");
		}
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	
	
	private int toInt(Object value)
	{return ((Number) value).intValue();}
	
	private long toLong(Object value)
	{return ((Number) value).longValue();}
	
	private double toDouble(Object value)
	{return ((Number) value).doubleValue();}
	
	private float toFloat(Object value)
	{return ((Number) value).floatValue();}
}
