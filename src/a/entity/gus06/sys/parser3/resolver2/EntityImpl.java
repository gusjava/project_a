package a.entity.gus06.sys.parser3.resolver2;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151119";}
	


	private Service resolver1;
	private Service handleCase;
	private Service listToString;

	public EntityImpl() throws Exception
	{
		resolver1 = Outside.service(this,"gus06.sys.parser3.resolver1");
		handleCase = Outside.service(this,"gus06.sys.parser3.resolver2.handlecase");
		listToString = Outside.service(this,"gus06.sys.parser3.resolver1.list.tostring");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		T external = (T) o[0];
		List list = (List) o[1];
		Object rule = o[2];
		
		T t = (T) resolver1.t(external);
		String[] rr = handleRule(rule);
		
		Exception e1 = null;
		
		for(String r:rr)
		{
			try
			{
				List list1 = new ArrayList(list);
				return handleCase.t(new Object[]{t,list1,r});
			}
			catch(Exception e)
			{e1 = e;}
		}
		
		throw new Exception("No rule could be applied to expression: "+listToString.t(list),e1);
	}
	
	
	private String[] handleRule(Object obj) throws Exception
	{
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return ((String) obj).split("\\|");
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
