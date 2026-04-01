package a.entity.gus06.feature.wrap.ee.e;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161212";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		E e1 = (E) o[0];
		E e2 = (E) o[1];
		
		return new Wrap(e1,e2);
	}
	
	
	
	
	
	private class Wrap implements E
	{
		private E e1;
		private E e2;
		
		public Wrap(E e1, E e2)
		{
			this.e1 = e1;
			this.e2 = e2;
		}
		
		public void e() throws Exception
		{
			e1.e();
			e2.e();
		}
	}
}
