package a.entity.tav.y.init1.launcher;

import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20240104";}

	public EntityImpl() throws Exception {
		
	}
	
	public void p(Object obj) throws Exception {
		String configId = (String) obj;
		System.out.println("Attempt to use tav.mini1 core with configId=" + configId);
	}
}
