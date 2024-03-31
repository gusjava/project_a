package a.entity.gus.y.cust1.exit;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, E {
	public String creationDate() {return "20231202";}
	
	private Service log;
	
	public EntityImpl() throws Exception {
		log = Outside.service(this,"logger");
	}
	
	public void e() throws Exception {
		send(this, "exit");

		log.p(new Object[] {this, "Exiting application..."});
		System.exit(0);
	}
}
