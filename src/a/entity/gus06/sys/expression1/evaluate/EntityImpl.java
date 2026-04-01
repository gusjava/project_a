package a.entity.gus06.sys.expression1.evaluate;

import a.framework.*;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150905";}


	private Service evaluate;
	private Service buildExternal0;
	

	public EntityImpl() throws Exception
	{
		evaluate = Outside.service(this,"gus06.sys.parser3.evaluate");
		buildExternal0 = Outside.service(this,"gus06.sys.expression1.external.c0");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		
		
		if(o.length==3)
		{
			Map pool = (Map) o[0];
			Map opMap = (Map) o[1];
			String exp = ((String) o[2]).trim();
			
			T external = buildExternal0(pool,opMap);
			return evaluate.t(new Object[]{external,exp});
		}
		
		if(o.length==4)
		{
			Map pool = (Map) o[0];
			Map opMap = (Map) o[1];
			Object exp = o[2];
			Object rule = o[3];
			
			T external = buildExternal0(pool,opMap);
			return evaluate.t(new Object[]{external,exp,rule});
		}
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	
	
	private T buildExternal0(Map pool, Map opMap) throws Exception
	{return (T) buildExternal0.t(new Object[]{pool,opMap});}
}
