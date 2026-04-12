package a.entity.gus.z.appli1.gui1_2.a;

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
		
		gui1 = Outside.service(this,"*gus.z.appli1.gui1_2_1.level0");
		gui2 = Outside.service(this,"*gus.z.appli1.gui1_2_2.level1");
		gui3 = Outside.service(this,"*gus.z.appli1.gui1_2_3.framework");
		
		tabHolder.v("Level 0", gui1);
		tabHolder.v("Level 1", gui2);
		tabHolder.v("Framework", gui3);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tabHolder.i(), BorderLayout.CENTER);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
