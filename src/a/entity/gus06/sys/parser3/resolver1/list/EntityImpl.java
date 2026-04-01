package a.entity.gus06.sys.parser3.resolver1.list;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160809";}
	
	
	private Service opListProvider;
	private Service listToString;
	
	public EntityImpl() throws Exception
	{
		opListProvider = Outside.service(this,"gus06.sys.parser3.resolver1.list.oplist");
		listToString = Outside.service(this,"gus06.sys.parser3.resolver1.list.tostring");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return resolveList((List) o[0],(T) o[1]);
	}
	
	
	
	
	private Object resolveList(List l, T t) throws Exception
	{
		try
		{
			if(l.size()<2) throw new Exception("Invalid list size: "+l.size());
			
			List ops = (List) opListProvider.g();
			for(int i=0;i<ops.size();i++)
			{
				Object[] a = (Object[]) ops.get(i);
				T a1 = (T) a[0];
				T a2 = (T) a[1];
				
				Object cut = a1.t(l);
				if(cut!=null) return a2.t(new Object[]{cut,t});
			}
			throw new Exception("No operator is available");
		}
		catch(Exception e)
		{
			String message = "Failed to resolve instruction: ["+listToString.t(l)+"]";
			throw new Exception(message,e);
		}
	}
}