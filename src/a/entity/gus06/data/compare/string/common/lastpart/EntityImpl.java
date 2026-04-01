package a.entity.gus06.data.compare.string.common.lastpart;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150620";}



	public Object t(Object obj) throws Exception
	{
		String[] t = (String[]) obj;
		if(t.length==0) return null;
		if(t.length==1) return t[0];
		
		return commonPart(t);
	}


	
	private String commonPart(String[] t)
	{
		int number = minLength(t);
		StringBuffer b = new StringBuffer(number);
		
		for(int i=0;i<number;i++)
		{
			boolean added = handleChar(b,t,i);
			if(!added) break;
		}
		return b.reverse().toString();
	}
	
	
	
	private boolean handleChar(StringBuffer b, String[] t, int index)
	{
		char c0 = charAt(t[0],index);
		
		for(int i=1;i<t.length;i++)
		{
			char c = charAt(t[i],index);
			if(c0!=c) return false;
		}
		b.insert(0,c0);
		return true;
	}
	
	
	
	
	private char charAt(String s, int index)
	{
		int l = s.length();
		return s.charAt(l-1-index);
	}
	
	
	
	
	private int minLength(String[] t)
	{
		int min = Integer.MAX_VALUE;
		for(String s:t) if(s!=null && s.length()<min) min = s.length();
		return min;
	}
}
