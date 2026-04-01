package a.entity.gus06.sys.expert1.analyze.num.simplifier;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160810";}


	private Service performSum;
	private Service performOpp;
	private Service rewrite;

	public EntityImpl() throws Exception
	{
		performSum = Outside.service(this,"gus06.sys.expert1.analyze.num.simplifier.sum");
		performOpp = Outside.service(this,"gus06.data.perform.opp");
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
			Number value = (Number) m.get("value");
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
		Number value = (Number) m.get("value");
		Map analyze = (Map) m.get("analyze");
		
		String operator = (String) analyze.get("operator");
		Object content = analyze.get("content");
		
		if(operator.equals("element")) return false;
		if(operator.equals("double")) return false;
		if(operator.equals("int")) return false;
		
		
		if(operator.equals("group1"))
		{
			m.put("analyze",content);
			return true;
		}
		
		if(operator.equals("-"))
		{
			m.put("analyze",content);
			m.put("value",performOpp.t(value));
			return true;
		}
		
		if(value.doubleValue()!=0)
		{
			m.put("analyze",createSum1(analyze,value));
			m.put("value",Integer.valueOf(0));
			return true;
		}
		
		if(operator.equals("+"))
			return performSum.f(m);
		
		throw new Exception("Unsupported operator for num simplification: "+operator);
	}
	
	
	
	
	
	private Map createSum1(Map a, Number value)
	{
		List content = new ArrayList();
		
		String op0 = (String) a.get("operator");
		Object c0 = a.get("content");
		
		if(op0.equals("+"))
			content.addAll((List) c0);
		else content.add(a);
		
		Map v = new HashMap();
		v.put("operator","double");
		v.put("content",Double.valueOf(-value.doubleValue()));
		
		content.add(v);
		
		Map a1 = new HashMap();
		a1.put("operator","+");
		a1.put("content",content);
		
		return a1;
	}
}
