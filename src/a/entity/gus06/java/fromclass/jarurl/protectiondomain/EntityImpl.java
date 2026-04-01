package a.entity.gus06.java.fromclass.jarurl.protectiondomain;

import a.framework.*;
import java.net.URL;
import java.security.*;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140819";}

	
	
	public Object t(Object obj) throws Exception
	{
		Class c = (Class) obj;
	
		ProtectionDomain pd = c.getProtectionDomain();
		if(pd==null) return "null";
		
		CodeSource cs = pd.getCodeSource();
		if(cs==null) return null;
		
		return cs.getLocation();
	}
}
