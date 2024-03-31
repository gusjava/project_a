package a.entity.gus.y.appli1.en.execute.exit;

import a.framework.*;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20240110";}
	
	private Service exit;

	public EntityImpl() throws Exception {
		exit = Outside.service(this, "exit");
	}
	
	public void e() throws Exception {
		exit.e();
	}
}
