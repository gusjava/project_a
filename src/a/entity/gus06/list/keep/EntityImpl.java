package a.entity.gus06.list.keep;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170419";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		int index = toInt(o[1]);
		
		int nb = list.size();
		if(index<0) index += nb;
		
		if(index<0 || index>=nb) return;
		
		list.remove(index);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		int index = toInt(o[1]);
		
		int nb = list.size();
		if(index<0) index += nb;
		
		if(index<0 || index>=nb) return null;
		
		List list1 = new ArrayList(list);
		list1.remove(index);
		return list1;
	}
	
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
}
