package a.entity.gus06.sys.expression1.apply.op._populate_ids;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160229";}
	
	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.data.perform.populateids");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return new P1(obj);
	}
	
	
	
	private class P1 implements P
	{
		private Object data;
		public P1(Object data)
		{this.data = data;}
		
		public void p(Object obj) throws Exception
		{perform.p(new Object[]{data,obj});}
	}
}
