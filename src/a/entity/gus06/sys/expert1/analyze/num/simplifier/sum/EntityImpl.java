package a.entity.gus06.sys.expert1.analyze.num.simplifier.sum;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160812";}



	public EntityImpl() throws Exception
	{
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		Map m = (Map) obj;
		
		Map analyze = (Map) m.get("analyze");
		List content = (List) analyze.get("content");
		
		Map coeffMap = new HashMap();
		
		for(Object part:content)
		handlePart(m,(Map) part,coeffMap);
		
		return false;
	}
	
	
	
	
	
	
	private void handlePart(Map m, Map part, Map coeffMap) throws Exception
	{
		String operator = (String) part.get("operator");
		Object content = part.get("content");
		
		while(operator.equals("group1"))
		{
			part = (Map) content;
			operator = (String) part.get("operator");
			content = part.get("content");
		}
		
		if(operator.equals("int") || operator.equals("double"))
		{
			double value = ((Number) content).doubleValue();
			addCoeff(coeffMap,"1",value);
			return;
		}
		
		if(operator.equals("element"))
		{
			String element = (String) content;
			addCoeff(coeffMap,element,1.0);
			return;
		}
		
		if(operator.equals("*"))
		{
			List l = (List) content;
			addCoeff(coeffMap,"?",1.0);
			return;
		}
		
		if(operator.equals("-"))
		{
			List l = (List) content;
			addCoeff(coeffMap,"?",-1.0);
			return;
		}
		
		
		throw new Exception("Unsupported operator for sum part: "+operator);
	}
	
	
	
	private void addCoeff(Map coeffMap, String key, double value)
	{
		if(!coeffMap.containsKey(key))
		{
			coeffMap.put(key,Double.valueOf(value));
		}
		else
		{
			double value0 = ((Double) coeffMap.get(key)).doubleValue();
			coeffMap.put(key,Double.valueOf(value0+value));
		}
	}
}
