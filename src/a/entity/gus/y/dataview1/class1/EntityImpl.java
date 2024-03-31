package a.entity.gus.y.dataview1.class1;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, G, P, I {
	public String creationDate() {return "20231129";}

	private Service gui1;
	private Service gui2;

	private JPanel panel;
	private Class data;

	public EntityImpl() throws Exception {
		gui1 = Outside.service(this, "*gus.y.dataview1.class1.gui1");
		gui2 = Outside.service(this, "*gus.y.dataview1.class1.gui2");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) gui2.i(), BorderLayout.NORTH);
		panel.add((JComponent) gui1.i(), BorderLayout.CENTER);
	}

	public Object g() throws Exception {
		return data;
	}

	public void p(Object obj) throws Exception {
		data = (Class) obj;
		if (data == null)
			resetGui();
		else
			updateGui();
	}

	private void resetGui() throws Exception {
		gui1.p(null);
		gui2.p(null);
	}

	private void updateGui() throws Exception {
		gui1.p(data);
		gui2.p(data);
	}

	public Object i() throws Exception {
		return panel;
	}
}
