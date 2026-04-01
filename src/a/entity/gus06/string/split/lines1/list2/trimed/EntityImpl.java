package a.entity.gus06.string.split.lines1.list2.trimed;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220621";}


	private Service split;

	public EntityImpl() throws Exception
	{
		split = Outside.service(this,"gus06.string.split.lines1");
	}
	
	public Object t(Object obj) throws Exception
	{
		List list = new ArrayList();
		if(obj instanceof List) 
			handleList(list,(List) obj);
		else if(obj instanceof Object[]) 
			handleArray(list,(Object[]) obj);
		return list;
	}
	
	
	
	private void handleList(List list, List l) throws Exception
	{
		for(int i=0;i<l.size();i++)
		{
			String element = (String) l.get(i);
			String[] nn = (String[]) split.t(element);
			fill(list,nn);
		}
	}
	
	private void handleArray(List list, Object[] a) throws Exception
	{
		for(int i=0;i<a.length;i++)
		{
			String element = (String) a[i];
			String[] nn = (String[]) split.t(element);
			fill(list,nn);
		}
	}
	
	private void fill(List list, String[] nn) throws Exception
	{
		for(String n:nn)
		{
			String n0 = n.trim();
			if(!n0.equals("")) list.add(n0);
		}
	}
}