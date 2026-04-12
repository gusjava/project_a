package a.entity.gus.y.appview1.entryview.panel.image;

import java.io.InputStream;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20231128";}

	private Service screen;
	private Service toImage;

	public EntityImpl() throws Exception {
		screen = Outside.service(this, "*gus.x.swing.panel.imagepanel.fit");
		toImage = Outside.service(this, "gus.x.io.build.image");
	}

	public Object i() throws Exception {
		return screen.i();
	}

	public void p(Object obj) throws Exception {
		InputStream is = (InputStream) obj;
		screen.p(toImage.t(is));
	}
}