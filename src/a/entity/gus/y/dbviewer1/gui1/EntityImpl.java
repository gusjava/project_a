package a.entity.gus.y.dbviewer1.gui1;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.sql.Connection;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20240206";}
	
	private JPanel panel;
	private JTextField field;
	private JTextArea area;
	
	private Connection cx;
	

	public EntityImpl() throws Exception {
		
		field = new JTextField();
		area = new JTextArea();
		area.setEditable(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field, BorderLayout.NORTH);
		panel.add(new JScrollPane(area), BorderLayout.NORTH);
	}
	
	public void p(Object obj) throws Exception {
		cx = (Connection) obj;
	}
	
	public Object i() throws Exception {
		return panel;
	}
}
