package a.entity.gus06.sys.expression1.apply.op._holder_jpanel;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170805";}


	private Service build;
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"factory#gus06.swing.panel.shiftpanel");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return build.g();
	}
}
