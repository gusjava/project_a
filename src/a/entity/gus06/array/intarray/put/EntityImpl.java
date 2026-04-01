package a.entity.gus06.array.intarray.put;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20170428";}


	private Service ruleToIndex;
	
	public EntityImpl() throws Exception
	{
		ruleToIndex = Outside.service(this,"gus06.list.ruletoindex");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		int[] array = (int[]) o[0];
		Object key = o[1];
		Integer value = (Integer) o[2];
		
		Integer n = (Integer) ruleToIndex.t(new Object[]{array,key});
		if(n==null) throw new Exception("Invalid rule for index: "+key);
		
		int index = n.intValue();
		array[index] = value.intValue();
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		int[] array = (int[]) o[0];
		Object key = o[1];
		Integer value = (Integer) o[2];
		
		int[] array1 = new int[array.length];
		for(int i=0;i<array.length;i++) array1[i] = array[i];
		
		Integer n = (Integer) ruleToIndex.t(new Object[]{array,key});
		if(n==null) throw new Exception("Invalid rule for index: "+key);
		
		int index = n.intValue();
		array1[index] = value.intValue();
		
		return array1;
	}
}
