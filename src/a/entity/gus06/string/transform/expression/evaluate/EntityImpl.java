package a.entity.gus06.string.transform.expression.evaluate;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160828";}
	
	public static final String DELIM = "\n";


	private Service evaluate;
	private Service getOpMap;

	public EntityImpl() throws Exception
	{
		evaluate = Outside.service(this,"gus06.sys.expression1.evaluate");
		getOpMap = Outside.service(this,"gus06.sys.expression1.apply.opmap");
	}
	
	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		Map opMap = (Map) getOpMap.g();
		
		StringBuffer b = new StringBuffer();
		String[] lines = input.split(DELIM,-1);
		int nb = lines.length;
		for(int i=0;i<nb;i++)
		{
			String newLine = computeLine(opMap, lines[i]);
			b.append(newLine);
			if(i<nb-1) b.append(DELIM);
		}
		return b.toString();
	}
	
	
	
	private String computeLine(Map opMap, String line) throws Exception
	{
		if(!line.contains("=")) return evaluate(opMap, line);
		
		String[] parts = line.split("=",-1);
		if(parts.length>2) throw new Exception("Invalid expression line: "+line);
		
		String result = evaluate(opMap, parts[0]);
		String result0 = parts[1].trim();
		
		if(result0.equals("")) return line+result;
		if(result0.equals(result)) return line;
		return parts[0]+"="+result+" (before:"+result0+")";		
	}
	
	
	private String evaluate(Map opMap, String exp) throws Exception
	{
		Map pool = new HashMap();
		return ""+evaluate.t(new Object[]{pool, opMap, formatExp(exp)});
	}
	
	private String formatExp(String exp)
	{return exp.replace("x","*").replace(",",".");}
}