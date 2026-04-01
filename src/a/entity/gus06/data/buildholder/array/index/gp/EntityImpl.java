package a.entity.gus06.data.buildholder.array.index.gp;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191114";}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object[] array = (Object[]) o[0];
		Integer index = (Integer) o[1];
		
		return new Holder(array,index);
	}

	
	
	
	private class Holder implements P, G
	{
		private Object[] array;
		private Integer index;
		
		public Holder(Object[] array, Integer index)
		{
			this.array = array;
			this.index = index;
		}
		
		public Object g() throws Exception
		{return array.length>index ? array[index] : null;}
		
		public void p(Object obj) throws Exception
		{array[index] = obj;}
	}
}
