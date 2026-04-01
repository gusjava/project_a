package a.entity.gus06.convert.ftor;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151124";}


	public Object t(Object obj) throws Exception
	{return new R1((F) obj);}
	
	
	
		
	
	private class R1 implements R
	{
		private F f;
		public R1(F f) {this.f = f;}
		
		public Object r(String key) throws Exception
		{return Boolean.valueOf(f.f(key));}
	}
}