package a.entity.gus06.data.authorname.list.findbest;

import a.framework.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220904";}


	private Service countDiacritics;

	public EntityImpl() throws Exception
	{
		countDiacritics = Outside.service(this,"gus06.data.string.countchar.diacritics");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list.isEmpty()) return null;
		if(list.size()==1) return formatName((String) list.get(0));
		
		Map m = new HashMap();
		for(int i=0;i<list.size();i++)
		{
			String name = (String) list.get(i);
			Integer score = (Integer) countDiacritics.t(name);
			if(name.matches(".*Mc[A-Z].*")) score += 10;
			m.put(name, score);
		}
		
		List keys = new ArrayList(m.keySet());
		Collections.sort(keys, new Comparator1(m));
		return formatName((String) keys.get(0));
	}
	
	
	private String formatName(String s) throws Exception
	{
		s = s.trim();
		if(s.equals("")) return "";
		
		String[] n = s.split("[,; ]+");
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		{
			b.append(formatTerm(n[i])+" ");
		}
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	private String formatTerm(String s)
	{
		if(s.length()==1) return s.toUpperCase();
		if(s.matches("Mc[A-Z].*")) return s;
		
		boolean start = true;
		StringBuffer b = new StringBuffer();
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(start) b.append(Character.toUpperCase(c));
			else b.append(Character.toLowerCase(c));
			
			start = c=='-';
		}
		return b.toString();
	}
	
	
	
	
	private class Comparator1 implements Comparator
	{
		private Map m;
		public Comparator1(Map m) {this.m = m;}
		
		public int compare(Object o1, Object o2)
		{
			Integer n1 = (Integer)(m.get(o1));
			Integer n2 = (Integer)(m.get(o2));
			
			return n2.compareTo(n1);
		}
	}
}