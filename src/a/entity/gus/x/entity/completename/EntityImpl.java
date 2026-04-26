package a.entity.gus.x.entity.completename;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260426";}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String devId = (String) o[0];
		String entityName = (String) o[1];
		
		if(!entityName.startsWith(devId+".")) return devId+"."+entityName;
		return entityName;
	}
}
