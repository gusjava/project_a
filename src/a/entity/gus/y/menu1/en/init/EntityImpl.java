package a.entity.gus.y.menu1.en.init;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import a.framework.E;
import a.framework.Entity;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, E {
	public String creationDate() {return "20231117";}

	private Service getMenuBar;
	private Service getActionAbout;
	private Service getActionRestart;
	private Service getActionExit;

	public EntityImpl() throws Exception {
		getMenuBar = Outside.service(this, "gus.y.appli1.gui.menubar");
		getActionAbout = Outside.service(this, "gus.y.appli1.en.action.about");
		getActionRestart = Outside.service(this, "gus.y.appli1.en.action.restart");
		getActionExit = Outside.service(this, "gus.y.appli1.en.action.exit");
	}

	public void e() throws Exception {
		Action actionAbout = (Action) getActionAbout.g();
		Action actionRestart = (Action) getActionRestart.g();
		Action actionExit = (Action) getActionExit.g();
		
		JMenu menu1 = new JMenu("Menu");
		menu1.add(actionAbout);
		menu1.add(actionRestart);
		menu1.add(actionExit);

		JMenuBar bar = (JMenuBar) getMenuBar.g();
		bar.add(menu1);
	}
}
