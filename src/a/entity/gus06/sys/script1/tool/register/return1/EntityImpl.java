package a.entity.gus06.sys.script1.tool.register.return1;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170219";}
	
	public static final String X_RETURN = "return";


	private Service getExecution;

	public EntityImpl() throws Exception
	{
		getExecution = Outside.service(this,"gus06.sys.script1.access.context.execution");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		Object value = o[1];
		
		Set returnSet = new HashSet();
		returnSet.add(value);
 		
		Map execution = (Map) getExecution.t(context);
		execution.put(X_RETURN,returnSet);
	}
}
