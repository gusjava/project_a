package a.entity.gus.z.appli1.gui2_3.entities;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231231";}

	private Service tabPersist;
	private Service tabHolder;

	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	
	private JPanel panel;
	
	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.y.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.y.swing1.tabbedpane.holder1");
		
		gui1 = Outside.service(this,"*gus.z.appli1.gui2_3_1.all");
		gui2 = Outside.service(this,"*gus.z.appli1.gui2_3_2.x");
//		gui3 = Outside.service(this,"*gus.z.appli1.gui2_3_3.y");
//		gui4 = Outside.service(this,"*gus.z.appli1.gui2_3_4.z");
		
		tabHolder.v("all", gui1);
		tabHolder.v("x", gui2);
//		tabHolder.v("y", gui3);
//		tabHolder.v("z", gui4);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tabHolder.i(), BorderLayout.CENTER);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
