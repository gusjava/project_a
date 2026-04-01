package a.entity.gus06.convert.htor;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151124";}


	public Object t(Object obj) throws Exception
	{return new R1((H) obj);}
	
	
	
		
	
	private class R1 implements R
	{
		private H h;
		public R1(H h) {this.h = h;}
		
		public Object r(String key) throws Exception
		{return Double.valueOf(h.h(toDouble(key)));}
	}
	
	
	private double toDouble(String key)
	{return Double.parseDouble(key);}
}
