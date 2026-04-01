package a.entity.gus06.convert.listtorectangle;

import a.framework.*;
import java.awt.Rectangle;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200510";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		List list = (List) obj;
		int number = list.size();
		
		if(number==2) return new Rectangle(0,0,get(list,0),get(list,1));	
		if(number==4) return new Rectangle(get(list,0),get(list,1),get(list,2),get(list,3));	
		
		throw new Exception("Wrong data number: "+number);
	}
	
	
	private int get(List list, int index)
	{
		Object v = list.get(index);
		return Integer.parseInt(""+v);	
	}
}
