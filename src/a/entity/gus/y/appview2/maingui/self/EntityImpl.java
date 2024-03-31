package a.entity.gus.y.appview2.maingui.self;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231128";}

	private Service mainGui;
	private Service appLocation;

	public EntityImpl() throws Exception {
		mainGui = Outside.service(this, "*gus.y.appview2.maingui");
		appLocation = Outside.service(this, "gus.x.app.location");

		File location = (File) appLocation.g();
		mainGui.p(location);
	}

	public Object i() throws Exception {
		return mainGui.i();
	}
}
