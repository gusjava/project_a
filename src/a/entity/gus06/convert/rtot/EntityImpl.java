package a.entity.gus06.convert.rtot;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151118";}


	public Object t(Object obj) throws Exception
	{return new T1((R) obj);}
	
	
	
		
	
	private class T1 implements T
	{
		private R r;
		public T1(R r) {this.r = r;}
		
		public Object t(Object obj) throws Exception
		{return r.r(""+obj);}
	}
}
