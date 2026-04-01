package a.entity.gus06.list.findall.before0.last;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170115";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		F filter = (F) o[1];
		
		int nb = input.size();
		
		int index = -1;
		for(int i=0;i<nb;i++)
		{
			Object element = input.get(nb-i-1);
			if(filter.f(element)) {index = i;break;}
		}
		if(index==-1) return new ArrayList();
		
		List output = new ArrayList();
		for(int i=0;i<=index;i++) output.add(input.get(i));
		
		return output;
	}
}
