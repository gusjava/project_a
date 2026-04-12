package a.entity.gus.y.cust1.rb.guiexit;

import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, E, G {
	public String creationDate() {return "20260329";}

	private S1 s;

	public EntityImpl() throws Exception {
		s = new S1();
	}

	public void e() throws Exception {
		//TODO ask for exit and log result
		exit();
		System.exit(0);
	}
	
	public Object g()
	{return s;}
	
	private void exit()
	{s.send(this,"exit()");}
}
