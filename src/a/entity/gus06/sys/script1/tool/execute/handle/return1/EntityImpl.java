package a.entity.gus06.sys.script1.tool.execute.handle.return1;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160221";}
	
	public static final String KEY_RETURN = "return";


	private Service getPool;
	private Service getParentPool;

	public EntityImpl() throws Exception
	{
		getPool = Outside.service(this,"gus06.sys.script1.access.tag.stack1.pool1");
		getParentPool = Outside.service(this,"gus06.sys.script1.access.tag.parent0.stack1.pool1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map tag = (Map) o[0];
		String returnKey = (String) o[1];
		
		Map pool1 = (Map) getPool.t(tag);
		Map pool2 = (Map) getParentPool.t(tag);
		
		if(!pool1.containsKey(KEY_RETURN)) return;
		
		Object returnValue = pool1.get(KEY_RETURN);
		pool2.put(returnKey,returnValue);
	}
}
