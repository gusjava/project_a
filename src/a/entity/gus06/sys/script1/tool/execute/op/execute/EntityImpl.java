package a.entity.gus06.sys.script1.tool.execute.op.execute;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150906";}


	private Service eval;

	public EntityImpl() throws Exception
	{
		eval = Outside.service(this,"gus06.sys.script1.context.evaluate");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		String line = (String) o[1];
		
		E exec = (E) eval.t(new Object[]{context,line});
		if(exec==null) throw new Exception("Could not execute null object");
		exec.e();
	}
}
