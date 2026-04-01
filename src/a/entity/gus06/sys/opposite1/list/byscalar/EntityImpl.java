package a.entity.gus06.sys.opposite1.list.byscalar;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160721";}


	private Service check;
	private Service oppList;


	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.sys.opposite1.check");
		oppList = Outside.service(this,"gus06.sys.opposite1.list.perform");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Integer k = (Integer) o[1];
		
		if(check.f(list)) k *= -1;
		
		int times = Math.abs(k);
		List list1 = new ArrayList();
		
		for(int i=0;i<times;i++) list1.addAll(list);
		
		return k<0 ? oppList.t(list1) : list1;
	}
}
