package a.entity.gus.z.appli1.gui3.sandbox;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231116";}
	
	private JPanel panel;
	
	public EntityImpl() throws Exception {
		panel = new JPanel(new BorderLayout());
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
