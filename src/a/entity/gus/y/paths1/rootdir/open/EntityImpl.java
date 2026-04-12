package a.entity.gus.y.paths1.rootdir.open;

import a.framework.*;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20231128";}

	private Service open;
	private Service get;

	public EntityImpl() throws Exception {
		open = Outside.service(this, "gus.x.awt.desktop.open");
		get = Outside.service(this, "gus.y.paths1.rootdir");
	}

	public void e() throws Exception {
		open.p(get.g());
	}
}
