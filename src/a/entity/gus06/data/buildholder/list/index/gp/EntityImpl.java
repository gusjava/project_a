package a.entity.gus06.data.buildholder.list.index.gp;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191114";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Integer index = (Integer) o[1];
		
		return new Holder(list,index);
	}

	
	
	
	private class Holder implements P, G
	{
		private List list;
		private Integer index;
		
		public Holder(List list, Integer index)
		{
			this.list = list;
			this.index = index;
		}
		
		public Object g() throws Exception
		{return list.size()>index ? list.get(index) : null;}
		
		public void p(Object obj) throws Exception
		{list.set(index,obj);}
	}
}
