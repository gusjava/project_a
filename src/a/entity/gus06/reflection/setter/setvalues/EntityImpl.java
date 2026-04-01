package a.entity.gus06.reflection.setter.setvalues;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160907";}


	private Service setValue;

	public EntityImpl() throws Exception
	{
		setValue = Outside.service(this,"gus06.reflection.setter.setvalue");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map map = (Map) o[1];
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			Object value = map.get(name);
			
			setValue.p(new Object[]{data,name,value});
		}
	}
}
