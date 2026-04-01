package a.entity.gus06.data.compare.string.comparator2;

import a.framework.*;

public class EntityImpl implements Entity, T, F {

	public String creationDate() {return "20150529";}

	public static final double LIMIT = 0.75;
	
	
	private Service algo;
	
	
	public EntityImpl() throws Exception
	{
		algo = Outside.service(this,"gus06.data.compare.string.common.longest1");
	}
	

	public Object t(Object obj) throws Exception
	{return Double.valueOf(compute(obj));}
	
	
	public boolean f(Object obj) throws Exception
	{return compute(obj)>=LIMIT;}

	
	
	private double compute(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String s1 = (String) o[0];
		String s2 = (String) o[1];
		
		if(s1.equals(s2)) return 1;
		if(s1.equals("")) return 0;
		if(s2.equals("")) return 0;
		
		boolean s1Long = isLong(s1);
		boolean s2Long = isLong(s2);
		
		if(s1Long && s2Long) return computeLongs(s1,s2);
		if(!s1Long && !s2Long) return computeStrings(s1,s2);
		return 0;
	}
	
	
	private double computeLongs(String s1, String s2)
	{
		return long_(s1)==long_(s2)?1:0;
	}
	
	
	private double computeStrings(String s1, String s2) throws Exception
	{
		String s0 = (String) algo.t(new String[]{s1,s2});
		
		int length0 = s0.length();
		int length1 = s1.length();
		int length2 = s2.length();
		
		int length = Math.max(length1,length2);
		return length0/(double)length;
	}
	
	
	private long long_(String s)
	{return Long.parseLong(s.replace(" ",""));}
	
	
	
	private boolean isLong(String s)
	{
		try{long_(s);return true;}
		catch(NumberFormatException e){return false;}
	}
}
