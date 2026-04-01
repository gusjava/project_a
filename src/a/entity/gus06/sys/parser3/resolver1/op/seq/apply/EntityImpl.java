package a.entity.gus06.sys.parser3.resolver1.op.seq.apply;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151031";}

	private Service apply;

	public EntityImpl() throws Exception
	{
		apply = Outside.service(this,"gus06.data.perform.apply");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		if(cut.isEmpty()) return null;
		
		Object base = t.t(cut.get(0));
		
		for(int i=1;i<cut.size();i++)
		{
			Object nextObj = t.t(cut.get(i));
			base = apply(base,nextObj);
		}
		return base;
	}
	
	
	private Object apply(Object o1, Object o2) throws Exception
	{
		try
		{
			return apply.t(new Object[]{o1,o2});
		}
		catch(Exception e)
		{
			String message = "Perform operator failed with o1="+o1+" & o2="+o2;
			throw new Exception(message,e);
		}	
	}
}
