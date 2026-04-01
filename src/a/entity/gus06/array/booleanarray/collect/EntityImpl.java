package a.entity.gus06.array.booleanarray.collect;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20171014";}


	private Service findBoolean;
	
	public EntityImpl() throws Exception
	{
		findBoolean = Outside.service(this,"gus06.find.boolean1");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		boolean[] input = (boolean[]) o[0];
		Object[] output = (Object[]) t(obj);
		
		for(int i=0;i<input.length;i++)
		input[i] = findBoolean.f(output[i]);
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		boolean[] input = (boolean[]) o[0];
		T t = (T) o[1];
		
		int nb = input.length;
		Object[] output = new Object[nb];
		
		for(int i=0;i<nb;i++)
		{
			Boolean element = Boolean.valueOf(input[i]);
			output[i] = t.t(element);
		}
		return output;
	}
}