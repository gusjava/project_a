package a.entity.gus06.sys.script1.context.builder1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150901";}



	private Service builder1a;
	private Service getOpMap;

	public EntityImpl() throws Exception
	{
		builder1a = Outside.service(this,"gus06.sys.script1.context.builder1.a");
		getOpMap = Outside.service(this,"gus06.sys.expression1.apply.opmap");
	}
	

	
	public Object t(Object obj) throws Exception
	{
		Object input1 = null;
		Object output1 = null;
		
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			input1 = o[0];
			output1 = o[1];
		}
		else output1 = obj;
		
		Object opMap = getOpMap.g();
		
		return builder1a.t(new Object[]{input1,output1,opMap});
	}
}
