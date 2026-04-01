package a.entity.gus06.list.build.popper;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191031";}
	
	
	public Object t(Object obj) throws Exception
	{return new G1((List) obj);}
	
	
	private class G1 implements G
	{
		private List list;
		public G1(List list){this.list = list;}
		
		public Object g() throws Exception
		{
			if(list.isEmpty()) return null;
			Object element = list.get(0);
			list.remove(0);
			return element;
		}
	}
}
