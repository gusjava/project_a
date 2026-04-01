package a.entity.gus06.sys.expert1.engine;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160810";}
	
	public static final int LIMIT = 500;


	private Service analyzeBool;
	private Service analyzeNum;
	private Service analyzer;
	private Service parser;


	public EntityImpl() throws Exception
	{
		analyzeBool = Outside.service(this,"gus06.sys.expert1.analyze.bool");
		analyzeNum = Outside.service(this,"gus06.sys.expert1.analyze.num");
		analyzer = Outside.service(this,"gus06.sys.parser3.analyzer1");
		parser = Outside.service(this,"gus06.sys.parser3.prepare");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map input = (Map) o[0];
		Map opMap = (Map) o[1];
		
		Map pool = new HashMap(input);
		Map output = new HashMap();
		
		boolean go = true;
		int k = 0;
		
		while(go && k<LIMIT)
		{
			Set keys = new HashSet(pool.keySet());
			Iterator it = keys.iterator();
			
			boolean analyzed = false;
			while(it.hasNext())
			{
				String exp = (String) it.next();
				Object value = pool.get(exp);
				if(analyze(exp,opMap,value,output,pool)) analyzed = true;
			}
			
			go = analyzed;
			k++;
		}
		
		if(k>=LIMIT) throw new Exception("Infinite loop detected in expert engine");
		
		output.putAll(pool);
		return output;
	}
	
	
	
	private boolean analyze(String exp, Map opMap, Object value, Map output, Map pool) throws Exception
	{
		try
		{
			Object data = parser.t(exp);
			Object analyze = analyzer.t(data);
			
			Map m = new HashMap();
			
			m.put("exp",exp);
			m.put("pool",pool);
			m.put("opMap",opMap);
			m.put("output",output);
			m.put("value",value);
			m.put("analyze",analyze);
			
			if(value instanceof Boolean) return analyzeBool.f(m);
			if(value instanceof Number) return analyzeNum.f(m);
			
			throw new Exception("Value type not supported yet: "+value.getClass().getName());
		}
		catch(Exception e)
		{
			String message = "Analyze failed with expression=["+exp+"] and value=["+value+"]";
			throw new Exception(message,e);
		}
	}
}
