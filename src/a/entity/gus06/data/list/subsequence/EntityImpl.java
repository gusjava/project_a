package a.entity.gus06.data.list.subsequence;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151117";}


	private Service buildRule;
	
	public EntityImpl() throws Exception
	{
		buildRule = Outside.service(this,"gus06.data.string.subsequence.buildrule");
	}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		String rule = (String) buildRule.t(o[1]);
		
		return sub(input,rule);
	}
	
	
	private List sub(List input, String rule) throws Exception
	{
		List output = new ArrayList();
		String[] nn = rule.split(";");
		for(String n:nn) handle1(output,input,n);
		return output;
	}
	
	
	
	private void handle1(List output, List input, String rule) throws Exception
	{
		String[] nn = rule.split("\\|");
		int l = input.size();
		
		String rule0 = nn[0];
		int[] skip = new int[nn.length-1];
		for(int i=1;i<nn.length;i++) skip[i-1] = toInt(nn[i],l);

		handle2(output,input,rule0,skip);
	}
	
	
	private void handle2(List output, List input, String rule, int[] skip) throws Exception
	{
		if(rule.equals("")) return;
		
		if(rule.equals("..") || rule.equals("all"))
		{
			handleAll(output,input,skip);
			return;
		}
		
		if(rule.equals("2n") || rule.equals("even"))
		{
			handleEven(output,input,skip);
			return;
		}
		
		if(rule.equals("2n+1") || rule.equals("odd"))
		{
			handleOdd(output,input,skip);
			return;
		}
		
		
		int l = input.size();
		
		if(isInt(rule))
		{
			int n = toInt(rule,l);
			if(n<0 || n>=l) throw new Exception("Invalid subsequence rule: "+rule);
			if(!has(skip,n)) output.add(input.get(n));
			return;
		}
		
		if(rule.startsWith("..")) rule = "0"+rule;
		if(rule.endsWith("..")) rule = rule+"-1";
		
		String[] m = rule.split("\\.\\.");
		if(m.length!=2) throw new Exception("Invalid subsequence rule: "+rule);
		
		int start = toInt(m[0],l);
		if(start<0 || start>=l) throw new Exception("Invalid subsequence rule: "+rule);
		
		int end = toInt(m[1],l);
		if(end<0 || end>=l) throw new Exception("Invalid subsequence rule: "+rule);
		
		if(start<=end)
		{
			for(int i=start;i<=end;i++)
			if(!has(skip,i)) output.add(input.get(i));
		}
		else
		{
			for(int i=start;i>=end;i--)
			if(!has(skip,i)) output.add(input.get(i));
		}
	}
	
	
	private boolean isInt(String s)
	{
		try{Integer.parseInt(s);return true;}
		catch(NumberFormatException e){}
		return false;
	}
	
	
	private void handleAll(List output, List input, int[] skip)
	{
		for(int i=0;i<input.size();i++)
		if(!has(skip,i)) output.add(input.get(i));
	}
	
	
	private void handleEven(List output, List input, int[] skip)
	{
		for(int i=0;i<input.size();i++)
		if(i%2==0 && !has(skip,i)) output.add(input.get(i));
	}
	
	private void handleOdd(List output, List input, int[] skip)
	{
		for(int i=0;i<input.size();i++)
		if(i%2==1 && !has(skip,i)) output.add(input.get(i));
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
