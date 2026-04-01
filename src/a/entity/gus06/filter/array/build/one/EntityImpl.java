package a.entity.gus06.filter.array.build.one;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170301";}

	
	public Object t(Object obj) throws Exception
	{return new F1((F[]) obj);}
	
	
	private class F1 implements F
	{
		private F[] ff;
		public F1(F[] ff){this.ff = ff;}
		
		public boolean f(Object obj) throws Exception
		{
			int found = 0;
			for(F f:ff) if(f.f(obj))
			{
				found++;
				if(found>1) return false;
			}
			return found==1;
		}
	}
}
