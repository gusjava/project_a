package a.entity.gus06.sys.expert1.analyze.bool.simplifier;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160810";}


	private Service invert;
	private Service rewrite;

	public EntityImpl() throws Exception
	{
		invert = Outside.service(this,"gus06.sys.parser3.analyzer1.op.bool.invert");
		rewrite = Outside.service(this,"gus06.sys.parser3.analyzer1.rewrite");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Map m = (Map) obj;
		
		int k=0;
		while(simplify(m) && k<1000) k++;
		
		if(k>0)
		{
			Map pool = (Map) m.get("pool");
			String exp = (String) m.get("exp");
			Boolean value = (Boolean) m.get("value");
			Map analyze = (Map) m.get("analyze");
			
			String exp1 = (String) rewrite.t(analyze);
			if(exp1.equals(exp)) throw new Exception("Simplification returned the same expression: "+exp);
			
			pool.remove(exp);
			pool.put(exp1,value);
		}
		return k>0;
	}
	
	
	
	private boolean simplify(Map m) throws Exception
	{
		Boolean value = (Boolean) m.get("value");
		Map analyze = (Map) m.get("analyze");
		
		String operator = (String) analyze.get("operator");
		Object content = analyze.get("content");
		
		if(operator.equals("element")) return false;
		if(operator.equals("boolean")) return false;
		
		
		if(!value.booleanValue())
		{
			analyze = (Map) invert.t(analyze);
			if(analyze==null) return false;
			
			m.put("value",Boolean.TRUE);
			m.put("analyze",analyze);
			return true;
		}
		
		if(operator.equals("group1"))
		{
			m.put("analyze",content);
			return true;
		}
		
		if(operator.equals("!"))
		{
			content = invert.t(content);
			if(content==null) return false;
			
			m.put("analyze",content);
			return true;
		}
		
		return false;
	}
}
