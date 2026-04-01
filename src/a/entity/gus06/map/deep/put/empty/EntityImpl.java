package a.entity.gus06.map.deep.put.empty;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151204";}

	private Service perform;
	private Service nextData;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.empty");
		nextData = Outside.service(this,"gus06.map.deep.nextdata0");
	}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2)	throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		String key = (String) o[1];
		
		String[] n = key.split("\\.");
		
		for(int i=0;i<n.length;i++)
		data = nextData(data,n[i]);
		
		if(data!=null) perform.p(data);
	}
	
	
	private Object nextData(Object data, String key) throws Exception
	{return nextData.t(new Object[]{data,key});}
}
