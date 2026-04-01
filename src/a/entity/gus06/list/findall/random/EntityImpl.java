package a.entity.gus06.list.findall.random;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170506";}


	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List input = (List) o[0];
		int nb = ((Integer) ruleToIndex.t(obj)).intValue();
		
		int size = input.size();
		List indexes = new ArrayList();
		
		while(indexes.size()<nb)
		{
			Integer n = Integer.valueOf(random(size));
			if(!indexes.contains(n)) indexes.add(n);
		}
		
		Collections.sort(indexes);
		
		List output = new ArrayList();
		for(int i=0;i<nb;i++)
		{
			Integer n = (Integer) indexes.get(i);
			Object element = input.get(n.intValue());
			output.add(element);
		}
		return output;
	}
	
	
	
	private int random(int limit)
	{return (int) (Math.random()*limit);}
}
