package a.entity.gus06.array.unique0_n;

import a.framework.*;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160626";}


	private Service normalize;

	public EntityImpl() throws Exception
	{normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] oo = (Object[]) obj;
		
		Set over = new HashSet();
		List l1 = new ArrayList();
		
		for(int i=0;i<oo.length;i++)
		{
			String element = format((String) oo[i]);
			if(!over.contains(element))
			{
				if(!l1.contains(element)) l1.add(element);
				else
				{
					l1.remove(element);
					over.add(element);
				}
			}
		}
		
		int nb = l1.size();
		Object[] oo1 = new Object[nb];
		for(int i=0;i<nb;i++) oo1[i] = l1.get(i);
		
		return oo1;
	}
	
	
	private String format(String s) throws Exception
	{return (String) normalize.t(s);}
}
