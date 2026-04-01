package a.entity.gus06.list.filter.build;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151101";}
	
	
	public Object t(Object obj) throws Exception
	{return new T1((F) obj);}
	
	
	
	private class T1 implements T
	{
		private F f;
		public T1(F f) {this.f = f;}
		
		public Object t(Object obj) throws Exception
		{
			List input = (List) obj;
			List output = new ArrayList();
			int nb = input.size();
			
			for(int i=0;i<nb;i++)
			{
				Object elem = input.get(i);
				if(f.f(elem)) output.add(elem);
			}
			return output;
		}
	}
}
