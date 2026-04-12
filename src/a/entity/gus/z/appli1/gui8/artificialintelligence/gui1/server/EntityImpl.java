package a.entity.gus.z.appli1.gui8.artificialintelligence.gui1.server;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20260412";}

	private Service delegate;

	public EntityImpl() throws Exception {
		delegate = Outside.service(this,"*gus.y.server1.maingui");
	}

	public Object i() throws Exception {
		return delegate.i();
	}
}
