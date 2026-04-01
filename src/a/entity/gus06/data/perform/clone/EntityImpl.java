package a.entity.gus06.data.perform.clone;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151205";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof boolean[])	return clone((boolean[]) obj);
		if(obj instanceof int[])	return clone((int[]) obj);
		if(obj instanceof double[])	return clone((double[]) obj);
		if(obj instanceof float[])	return clone((float[]) obj);
		if(obj instanceof long[])	return clone((long[]) obj);
		
		if(obj instanceof String[])	return clone((String[]) obj);
		if(obj instanceof File[])	return clone((File[]) obj);
		if(obj instanceof URL[])	return clone((URL[]) obj);
		if(obj instanceof Map[])	return clone((Map[]) obj);
		if(obj instanceof List[])	return clone((List[]) obj);
		if(obj instanceof Set[])	return clone((Set[]) obj);
		if(obj instanceof Object[])	return clone((Object[]) obj);
		
		if(obj instanceof List)		return clone((List) obj);
		if(obj instanceof Set)		return clone((Set) obj);
		if(obj instanceof Map)		return clone((Map) obj);
		
		if(obj instanceof StringBuffer)	return clone((StringBuffer) obj);
		
		return obj;
	}
	
	
	private Object clone(boolean[] a)
	{
		boolean[] b = new boolean[a.length];
		for(int i=0;i<a.length;i++) b[i] = a[i];
		return b;
	}
	
	private Object clone(int[] a)
	{
		int[] b = new int[a.length];
		for(int i=0;i<a.length;i++) b[i] = a[i];
		return b;
	}
	
	private Object clone(double[] a)
	{
		double[] b = new double[a.length];
		for(int i=0;i<a.length;i++) b[i] = a[i];
		return b;
	}
	
	private Object clone(float[] a)
	{
		float[] b = new float[a.length];
		for(int i=0;i<a.length;i++) b[i] = a[i];
		return b;
	}
	
	private Object clone(long[] a)
	{
		long[] b = new long[a.length];
		for(int i=0;i<a.length;i++) b[i] = a[i];
		return b;
	}
	
	
	
	
	private Object clone(String[] a) throws Exception
	{
		String[] b = new String[a.length];
		for(int i=0;i<a.length;i++) b[i] = (String) t(a[i]);
		return b;
	}
	
	private Object clone(File[] a) throws Exception
	{
		File[] b = new File[a.length];
		for(int i=0;i<a.length;i++) b[i] = (File) t(a[i]);
		return b;
	}
	
	private Object clone(URL[] a) throws Exception
	{
		URL[] b = new URL[a.length];
		for(int i=0;i<a.length;i++) b[i] = (URL) t(a[i]);
		return b;
	}
	
	private Object clone(Map[] a) throws Exception
	{
		Map[] b = new Map[a.length];
		for(int i=0;i<a.length;i++) b[i] = (Map) t(a[i]);
		return b;
	}
	
	private Object clone(List[] a) throws Exception
	{
		List[] b = new List[a.length];
		for(int i=0;i<a.length;i++) b[i] = (List) t(a[i]);
		return b;
	}
	
	private Object clone(Set[] a) throws Exception
	{
		Set[] b = new Set[a.length];
		for(int i=0;i<a.length;i++) b[i] = (Set) t(a[i]);
		return b;
	}
	
	private Object clone(Object[] a) throws Exception
	{
		Object[] b = new Object[a.length];
		for(int i=0;i<a.length;i++) b[i] = t(a[i]);
		return b;
	}
	
	
	private Object clone(List a) throws Exception
	{
		List b = new ArrayList();
		for(int i=0;i<a.size();i++) b.add(t(a.get(i)));
		return b;
	}
	
	private Object clone(Set a) throws Exception
	{
		Set b = new HashSet();
		Iterator it = a.iterator();
		while(it.hasNext()) b.add(t(it.next()));
		return b;
	}
	
	private Object clone(Map a) throws Exception
	{
		Map b = new HashMap();
		Iterator it = a.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = a.get(key);
			b.put(t(key),t(value));
		}
		return b;
	}
	
	
	private Object clone(StringBuffer b)
	{
		return new StringBuffer(b.toString());
	}
}
