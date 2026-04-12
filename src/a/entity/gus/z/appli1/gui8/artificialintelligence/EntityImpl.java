package a.entity.gus.z.appli1.gui8.artificialintelligence;

import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20260411";}

	private Service tabPersist;
	private Service tabHolder;
	
	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;

	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.y.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.y.swing1.tabbedpane.holder1");
		gui1 = Outside.service(this,"*gus.z.appli1.gui8.artificialintelligence.gui1.server");
		gui2 = Outside.service(this,"*gus.z.appli1.gui8.artificialintelligence.gui2.terminal");
		gui3 = Outside.service(this,"*gus.z.appli1.gui8.artificialintelligence.gui3.knowledge");
		gui4 = Outside.service(this,"*gus.z.appli1.gui8.artificialintelligence.gui4.roadmap");

		tabHolder.v("AI_server#Server", gui1);
		tabHolder.v("AI_terminal#Terminal", gui2);
		tabHolder.v("AI_knowledge#Knowledge", gui3);
		tabHolder.v("AI_roadmap#Roadmap", gui4);

		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	public Object i() throws Exception {
		return tabHolder.i();
	}
}
