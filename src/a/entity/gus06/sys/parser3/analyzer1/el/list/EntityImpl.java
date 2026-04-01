package a.entity.gus06.sys.parser3.analyzer1.el.list;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160810";}
	
	
	private Service opListProvider;
	private Service listToString;
	private Service buildResult;
	
	public EntityImpl() throws Exception
	{
		opListProvider = Outside.service(this,"gus06.sys.parser3.resolver1.list.oplist");
		listToString = Outside.service(this,"gus06.sys.parser3.resolver1.list.tostring");
		buildResult = Outside.service(this,"gus06.sys.parser3.analyzer1.build.result");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List l = (List) o[0];
		T t = (T) o[1];
		
		try
		{
			if(l.size()<3) throw new Exception("Invalid list size: "+l.size());
			
			List ops = (List) opListProvider.g();
			for(int i=0;i<ops.size();i++)
			{
				Object[] a = (Object[]) ops.get(i);
				T a1 = (T) a[0];
				T a2 = (T) a[1];
				String a3 = (String) a[2];
				
				List cut = (List) a1.t(l);
				if(cut!=null)
				{
					List cut1 = new ArrayList();
					for(Object k:cut) cut1.add(t.t(k));
					
					return buildResult.t(new Object[]{a3,cut1});
				}
			}
			throw new Exception("No operator is available");
		}
		catch(Exception e)
		{
			String message = "Failed to analyze list: ["+listToString.t(l)+"]";
			throw new Exception(message,e);
		}
	}
}
