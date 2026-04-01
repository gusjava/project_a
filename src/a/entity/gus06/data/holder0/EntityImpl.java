package a.entity.gus06.data.holder0;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, P, G {

	public String creationDate() {return "20161117";}
	
	private Object value;
	

	public Object g() throws Exception
	{return value;}
	
	
	public void p(Object obj) throws Exception
	{value = obj;changed();}
	
	
	private void changed()
	{send(this,"changed()");}
}
