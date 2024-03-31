package a.entity.gus.y.ruletonumber1.find2;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240121";}
	
	public static int LAST_OFFSET = 1;
	public static int FIRST = 1;

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Integer size = (Integer) o[0];
		Object rule = o[1];
		
		int value = find(size, rule);
		while(value<0) value += size+1;
		if(value<FIRST) return FIRST;
		return value;
	}
	
	private int find(int size, Object rule) throws Exception
	{
		if(rule instanceof Integer) return ((Integer) rule).intValue();
		if(rule instanceof String) return find(size, (String) rule);
		
		throw new Exception("Invalid rule type: "+rule.getClass().getName());
	}
	
	private int find(int size, String rule)
	{
		if(rule.startsWith("(") && rule.endsWith(")"))
			rule = rule.substring(1,rule.length()-1);
		
		if(rule.startsWith("[") && rule.endsWith("]"))
			rule = rule.substring(1,rule.length()-1);
		
		if(rule.equals("first")) return FIRST;
		if(rule.equals("last")) return FIRST + size - LAST_OFFSET;
		if(rule.equals("random")) return FIRST + random(size);
		if(rule.equals("middle1")) return FIRST + middle1(size);
		if(rule.equals("middle2")) return FIRST + middle2(size);
		
		rule = rule.trim();
		if(rule.startsWith("m"))
		{
			int c = Integer.parseInt(rule.substring(1));
			return -1*Math.abs(c);
		}
		if(rule.startsWith("p"))
		{
			return Integer.parseInt(rule.substring(1));
		}
		return Integer.parseInt(rule);
	}
	
	private int random(int n)
	{return (int) (Math.random()*n);}
	
	private int middle1(int n)
	{return (n/2)-(n-1)%2;}
	
	private int middle2(int n)
	{return n/2;}
}