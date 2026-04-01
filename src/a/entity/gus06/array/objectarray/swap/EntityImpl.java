package a.entity.gus06.array.objectarray.swap;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180504";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object[] array = (Object[]) o[0];
		int n1 = ((Integer) o[1]).intValue();
		int n2 = ((Integer) o[2]).intValue();
		
		Object value = array[n1];
		array[n1] = array[n2];
		array[n2] = value;
	}
}
