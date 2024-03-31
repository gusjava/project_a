package a.entity.gus.z.appli1.gui1_5.entities;

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
	
	private JPanel panel;
	
	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.y.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.y.swing1.tabbedpane.holder1");
		
		gui1 = Outside.service(this,"*gus.z.appli1.gui1_5_1.x");
		gui2 = Outside.service(this,"*gus.z.appli1.gui1_5_2.y");
		gui3 = Outside.service(this,"*gus.z.appli1.gui1_5_3.z");
		
		tabHolder.v("x", gui1);
		tabHolder.v("y", gui2);
		tabHolder.v("z", gui3);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tabHolder.i(), BorderLayout.CENTER);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
