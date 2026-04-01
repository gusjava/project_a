package a.entity.gus06.data.holder;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, P, G {

	public String creationDate() {return "20140908";}
	
	private Object value;
	

	public Object g() throws Exception
	{return value;}
	
	
	public void p(Object obj) throws Exception
	{
		if(equals(value,obj)) return;
		value = obj;
		changed();
	}
	
	
	
	private void changed()
	{send(this,"changed()");}
	
	
	
	private boolean equals(Object o1, Object o2)
	{
		if(o1==null && o2==null) return true;
		if(o1==null || o2==null) return false;
		return o1.equals(o2);
	}
}
