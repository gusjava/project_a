package a.entity.gus06.data.filter.number.fromrule1;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160905";}
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Number number = (Number) o[0];
		String rule = (String) o[1];
		
		double v = number.doubleValue();
		
		String[] nn = rule.split(";");
		for(String n:nn) if(check(v,n.trim())) return true;
		
		return false;
	}
	
	
	
	private boolean check(double v, String n) throws Exception
	{
		if(isDouble(n)) return v==toDouble(n);
		if(n.startsWith("!")) return v!=toDouble(n.substring(1));
		
		if(n.contains(" "))
		{
			boolean a1 = n.startsWith("]");
			boolean a2 = n.startsWith("[");
			if(!a1 && !a2) throw new Exception("Invalid rule: "+n);
			
			boolean b1 = n.endsWith("]");
			boolean b2 = n.endsWith("[");
			if(!b1 && !b2) throw new Exception("Invalid rule: "+n);
			
			String[] k = n.substring(1,n.length()-1).split(" ");
			if(k.length!=2) throw new Exception("Invalid rule: "+n);
			
			double v1 = toDouble(k[0]);
			double v2 = toDouble(k[1]);
			
			if(a1) if(v<=v1) return false;
			if(a2) if(v<v1) return false;
			
			if(b1) if(v>v2) return false;
			if(b2) if(v>=v2) return false;
			
			return true;
		}
		
		if(n.startsWith("]"))
		{
			double v0 = toDouble(n.substring(1));
			return v>v0;
		}
		
		if(n.startsWith("["))
		{
			double v0 = toDouble(n.substring(1));
			return v>=v0;
		}
		
		if(n.endsWith("]"))
		{
			double v0 = toDouble(n.substring(0,n.length()-1));
			return v<=v0;
		}
		
		if(n.endsWith("["))
		{
			double v0 = toDouble(n.substring(0,n.length()-1));
			return v<v0;
		}
		
		throw new Exception("Invalid rule: "+n);
	}
	
	
	private boolean isDouble(String s)
	{
		try{Double.parseDouble(s);return true;}
		catch(NumberFormatException e){return false;}
	}
	
	private double toDouble(String s)
	{
		return Double.parseDouble(s);
	}
}
