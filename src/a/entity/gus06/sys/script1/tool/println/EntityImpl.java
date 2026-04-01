package a.entity.gus06.sys.script1.tool.println;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170214";}
	
	public static final String TRANS_PRINT = "trans_print";


	private Service getOutput;
	private Service toString;

	public EntityImpl() throws Exception
	{
		getOutput = Outside.service(this,"gus06.sys.script1.access.context.output0");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		String text = (String) toString.t(o[1]);
		
		P output = (P) getOutput.t(context);
		if(output==null) return;
		
		boolean enabled = ((F) output).f("print");
		if(!enabled) return;
		
		T t = (T) get(context,TRANS_PRINT);
		if(t!=null) text = (String) t.t(text);
		
		output.p(text+"\n");
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
