package a.entity.gus06.convert.iteratortog;

import a.framework.*;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191031";}
	
	
	public Object t(Object obj) throws Exception
	{return new G1((Iterator) obj);}
	
	
	private class G1 implements G
	{
		private Iterator it;
		public G1(Iterator it){this.it = it;}
		
		public Object g() throws Exception
		{
			if(it.hasNext()) return it.next();
			return null;
		}
	}
}
