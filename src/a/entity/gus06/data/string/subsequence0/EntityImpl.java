package a.entity.gus06.data.string.subsequence0;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151119";}


	private Service buildRule;
	
	public EntityImpl() throws Exception
	{
		buildRule = Outside.service(this,"gus06.data.string.subsequence.buildrule");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String input = (String) o[0];
		String rule = (String) buildRule.t(o[1]);
		
		return sub(input,rule);
	}
	
	
	private String sub(String input, String rule) throws Exception
	{
		StringBuffer b = new StringBuffer();
		
		String[] nn = rule.split(";");
		for(String n:nn) b.append(sub1(input,n));
		
		return b.toString();
	}
	
	
	private String sub1(String input, String rule) throws Exception
	{
		String[] nn = rule.split("\\|");
		int l = input.length();
		
		String rule0 = nn[0];
		int[] skip = new int[nn.length-1];
		for(int i=1;i<nn.length;i++) skip[i-1] = toInt(nn[i],l);

		return sub2(input,rule0,skip);
	}
	
	
	
	private String sub2(String input, String rule, int[] skip) throws Exception
	{
		if(rule.equals("..")) return all(input,skip);
		if(rule.equals("all")) return all(input,skip);
		
		if(rule.equals("even")) return even(input,skip);
		if(rule.equals("2n")) return even(input,skip);
		
		if(rule.equals("odd")) return odd(input,skip);
		if(rule.equals("2n+1")) return odd(input,skip);
		
		
		int l = input.length();
		
		if(isInt(rule))
		{
			int n = toInt(rule,l);
			if(n<0 || n>=l) return "";
			return has(skip,n)?"":""+input.charAt(n);
		}
		
		if(rule.startsWith("..")) rule = "0"+rule;
		if(rule.endsWith("..")) rule = rule+"-1";
		
		
		String[] m = rule.split("\\.\\.");
		if(m.length!=2) throw new Exception("Invalid subsequence rule: "+rule);
		
		int start = toInt(m[0],l);
		int end = toInt(m[1],l);
		
		
		if(start<=end) return substring(input,start,end,skip);
		return substring_re(input,start,end,skip);
	}
	
	private boolean isInt(String s)
	{
		try{Integer.parseInt(s);return true;}
		catch(NumberFormatException e){}
		return false;
	}
	
	
	
	
	private String substring(String s, int start, int end, int[] skip)
	{
		int l = s.length();
		if(start<0) start = 0;
		if(end>=l) end = l-1;
		
		StringBuffer b = new StringBuffer();
		for(int i=start;i<=end;i++)
		if(!has(skip,i)) b.append(s.charAt(i));
		return b.toString();
	}
	
	
	private String substring_re(String s, int start, int end, int[] skip)
	{
		int l = s.length();
		
		StringBuffer b = new StringBuffer();
		for(int i=start;i>=end;i--)
		if(i>=0 && i<l) if(!has(skip,i)) b.append(s.charAt(i));
		return b.toString();
	}
	
	
	
	
	private String all(String s, int[] skip)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<s.length();i++)
		if(!has(skip,i)) b.append(s.charAt(i));
		return b.toString();
	}
	
	private String even(String s, int[] skip)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<s.length();i++)
		if(i%2==0 && !has(skip,i)) b.append(s.charAt(i));
		return b.toString();
	}
	
	private String odd(String s, int[] skip)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<s.length();i++)
		if(i%2==1 && !has(skip,i)) b.append(s.charAt(i));
		return b.toString();
	}
	
	
	
	private int toInt(String s)
	{return Integer.parseInt(s);}
	
	
	private int toInt(String s, int l)
	{
		int n = toInt(s);
		return n<0? n+l:n;
	}
	
	private boolean has(int[] nn, int v)
	{
		for(int n:nn) if(n==v) return true;
		return false;
	}
}