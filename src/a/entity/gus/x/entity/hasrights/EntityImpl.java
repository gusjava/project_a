package a.entity.gus.x.entity.hasrights;

import a.framework.*;

public class EntityImpl implements Entity, F {
	public String creationDate() {return "20260426";}
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String devId = (String) o[0];
		String entityName = (String) o[0];
		
		if(devId.equals("gus")) return true;
		if(entityName.startsWith(devId+".")) return true;
		
		return false;
	}
}
