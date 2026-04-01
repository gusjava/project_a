package a.entity.gus06.sys.parser3.resolver1.tag.group3.set;

import a.framework.*;
import java.util.List;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}


	private Service cutMethod;
	private Service buildSet;

	public EntityImpl() throws Exception
	{
		cutMethod = Outside.service(this,"gus06.sys.parser3.cut.symbol.a1");
		buildSet = Outside.service(this,"gus06.set.factory.silentset");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List l = (List) o[0];
		T t = (T) o[1];
		
		Set result = (Set) buildSet.g();
		
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
