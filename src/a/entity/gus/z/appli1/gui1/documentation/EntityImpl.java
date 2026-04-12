package a.entity.gus.z.appli1.gui1.documentation;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231203";}

	private Service tabPersist;
	private Service tabHolder;

	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	private Service gui5;
	private Service gui6;
	private Service gui7;
	
	private JPanel panel;
	
	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.y.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.y.swing1.tabbedpane.holder1");
		
		gui1 = Outside.service(this,"*gus.z.appli1.gui1_1.welcome");
		gui2 = Outside.service(this,"*gus.z.appli1.gui1_2.a");
		gui3 = Outside.service(this,"*gus.z.appli1.gui1_3.applis");
		gui4 = Outside.service(this,"*gus.z.appli1.gui1_4.rules");
		gui5 = Outside.service(this,"*gus.z.appli1.gui1_5.entities");
		gui6 = Outside.service(this,"*gus.z.appli1.gui1_6.cores");
		gui7 = Outside.service(this,"*gus.z.appli1.gui1_7.configs");
		
		tabHolder.v("GUI_welcome#Welcome", gui1);
		tabHolder.v("STRUCT_a#Project a", gui2);
		tabHolder.v("STRUCT_entity#Entities", gui5);
		tabHolder.v("STRUCT_core#Cores", gui6);
		tabHolder.v("STRUCT_config#Configs", gui7);
		tabHolder.v("STRUCT_rule#Rules", gui4);
		tabHolder.v("STRUCT_appli#Applis", gui3);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tabHolder.i(), BorderLayout.CENTER);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
