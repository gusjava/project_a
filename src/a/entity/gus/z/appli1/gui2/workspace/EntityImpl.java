package a.entity.gus.z.appli1.gui2.workspace;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231116";}

	private Service tabPersist;
	private Service tabHolder;

	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	private Service gui5;
	
	private JPanel panel;
	
	public EntityImpl() throws Exception {
		tabPersist = Outside.service(this,"gus.y.persist1.swing.tabbedpane.tab");
		tabHolder = Outside.service(this,"*gus.y.swing1.tabbedpane.holder1");
		
		gui1 = Outside.service(this,"*gus.z.appli1.gui2_1.applis");
		gui2 = Outside.service(this,"*gus.z.appli1.gui2_2.rules");
		gui3 = Outside.service(this,"*gus.z.appli1.gui2_3.entities");
		gui4 = Outside.service(this,"*gus.z.appli1.gui2_4.cores");
		gui5 = Outside.service(this,"*gus.z.appli1.gui2_5.configs");
		
		tabHolder.v("STRUCT_entity#Entities", gui3);
		tabHolder.v("STRUCT_core#Cores", gui4);
		tabHolder.v("STRUCT_config#Configs", gui5);
		tabHolder.v("STRUCT_rule#Rules", gui2);
		tabHolder.v("STRUCT_appli#Applis", gui1);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tabHolder.i(), BorderLayout.CENTER);
		
		tabPersist.v(getClass().getName()+"_tab",tabHolder.i());
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
