package a.entity.gus.y.appli1.en.execute.about;

import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20240110";}

	private Service dialogPopup;
	private Service aboutPanel;

	public EntityImpl() throws Exception {
		dialogPopup = Outside.service(this, "gus.y.swing1.dialog.popup1");
		aboutPanel = Outside.service(this, "*gus.y.appli1.en.execute.about.panel");
	}

	public void e() throws Exception {
		dialogPopup.p(aboutPanel.i());
	}
}