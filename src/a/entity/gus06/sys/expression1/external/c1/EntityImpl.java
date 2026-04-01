package a.entity.gus06.sys.expression1.external.c1;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160820";}


	private Service buildExternal;
	private Service getContext;
	private Service contextPool;

	public EntityImpl() throws Exception
	{
		buildExternal = Outside.service(this,"gus06.sys.expression1.external.c0");
		getContext = Outside.service(this,"gus06.sys.script1.access.opmap.context");
		contextPool = Outside.service(this,"gus06.sys.script1.access.context.pool.latest");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map opMap = (Map) o[1];
		
		Map context = (Map) getContext.t(opMap);
		Map pool1 = (Map) contextPool.t(context);
		
		Map pool = new HashMap();
		
		pool.put("a",data);
		if(pool1!=null)	pool.put("parent",pool1);
		
		if(data instanceof List)
		{
			List l = (List) data;
			for(int i=0;i<l.size();i++)
			{
				Object element = l.get(i);
				char c = (char)(98+i);
				pool.put(""+c,element);
			}
		}
		else if(data instanceof Object[])
		{
			Object[] arr = (Object[]) data;
			for(int i=0;i<arr.length;i++)
			{
				Object element = arr[i];
				char c = (char)(98+i);
				pool.put(""+c,element);
			}
		}
		
		return buildExternal.t(new Object[]{pool,opMap});
	}
}