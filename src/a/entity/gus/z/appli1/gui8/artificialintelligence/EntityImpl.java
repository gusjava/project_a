package a.entity.gus.z.appli1.gui8.artificialintelligence;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20260411";}

	private Service tabPersist;
	private Service tabHolder;
	
	private Service gui1;
	private Service gui2;
	
	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.y.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.y.swing1.tabbedpane.holder1");
		gui1 = Outside.service(this,"*gus.y.server1.maingui");
		gui2 = Outside.service(this,"*gus.x.swing.panel.bg.red");
		
		tabHolder.v("GUI_server#Server", gui1);
		tabHolder.v("GUI_consoleAI#Terminal", gui2);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	public Object i() throws Exception {
		return tabHolder.i();
	}
}
