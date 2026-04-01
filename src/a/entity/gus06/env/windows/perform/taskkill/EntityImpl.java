package a.entity.gus06.env.windows.perform.taskkill;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170410";}
	
		

	
	public void p(Object obj) throws Exception
	{
		String pid = (String) obj;
		Runtime.getRuntime().exec("taskkill /F /PID "+pid);
	}
}
