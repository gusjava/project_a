package a.entity.gus06.set.build.popper;

import a.framework.*;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191031";}
	
	
	public Object t(Object obj) throws Exception
	{return new G1((Set) obj);}
	
	
	private class G1 implements G
	{
		private Set set;
		public G1(Set set){this.set = set;}
		
		public Object g() throws Exception
		{
			if(set.isEmpty()) return null;
			Object element = set.iterator().next();
			set.remove(element);
			return element;
		}
	}
}
