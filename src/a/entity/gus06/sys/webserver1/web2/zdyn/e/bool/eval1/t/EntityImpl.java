package a.entity.gus06.sys.webserver1.web2.zdyn.e.bool.eval1.t;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141007";}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return ""+eval(s);
	}
	
	
	// combinaisons d'entiers avec des +, -, *
	
	private int eval(String s) throws Exception
	{
		s = s.replace("--","+");
		s = s.replace("-","+-");
		while(s.startsWith("+")) s = s.substring(1);
		while(s.contains("++")) s = s.replace("++","+");
		
		if(s.contains("+"))
		{
			String[] n = s.split("\\+");
			return sum(n);
		}
		if(s.startsWith("-"))
		{
			return -1 * eval(s.substring(1));
		}
		if(s.contains("*"))
		{
			String[] n = s.split("\\*",2);
			return eval(n[0]) * eval(n[1]);
		}
		
		try{return Integer.parseInt(s);}
		catch(NumberFormatException e)
		{throw new Exception("Invalid int expression: "+s);}
	}
	
	
	
	private int sum(String[] n) throws Exception
	{
		int sum = 0;
		for(String s:n) sum+= eval(s);
		return sum;
	}
}
