package a.entity.gus06.list.equals.full;

import a.framework.*;
import java.util.List;
import java.util.Objects;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201213";}

	
	
	public boolean f(Object obj) throws Exception
	{
		List[] o = (List[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list1 = o[0];
		List list2 = o[1];
		
		int nb1 = list1.size();
		int nb2 = list2.size();
		
		if(nb1!=nb2) return false;
		
		for(int i=0;i<nb1;i++)
		if(!Objects.equals(list1.get(i),list2.get(i))) return false; 
		return true;
	}
}
