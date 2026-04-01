package a.entity.gus06.sys.parser3.resolver1.tag.group2.list;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}


	private Service cutMethod;
	private Service silentList;

	public EntityImpl() throws Exception
	{
		cutMethod = Outside.service(this,"gus06.sys.parser3.cut.symbol.a1");
		silentList = Outside.service(this,"gus06.list.factory.silentlist");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List l = (List) o[0];
		T t = (T) o[1];
		
		List result = (List) silentList.g();
		
		List cut = (List) cutMethod.t(new Object[]{l,","});
		if(cut==null)
		{
			result.add(t.t(l));
		}
		else
		{
			int number = cut.size();
			for(int i=0;i<number;i++)
			result.add(t.t(cut.get(i)));
		}
		return result;
	}
}
