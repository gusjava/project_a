package a.entity.gus.y.entityimporter1.maingui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, I, P {
	public String creationDate() {return "20240117";}

	private Service engine;
	private Service gui;
	private Service persister;
	private Service undoRedo;
	private Service custComp1;

	private JPanel panel;
	private JTextField fieldDir;

	public EntityImpl() throws Exception {
		engine = Outside.service(this, "gus.y.entityimporter1.engine1");
		gui = Outside.service(this, "*gus.x.features.p.string.inputgui1");
		persister = Outside.service(this, "gus.y.persist1.swing.textcomp");
		undoRedo = Outside.service(this,"gus.y.swing1.textcomp.cust.action.ctrl_zy.undoredo");
		custComp1 = Outside.service(this,"gus.y.swingactions1.custcomp1");

		fieldDir = new JTextField();
		Object area = gui.r("area");

		persister.v(getClass().getName() + "_field", fieldDir);
		persister.v(getClass().getName() + "_area", area);
		
		undoRedo.p(area);
		custComp1.p(area);

		panel = new JPanel(new BorderLayout());
		panel.add(fieldDir, BorderLayout.NORTH);
		panel.add((JComponent) gui.i(), BorderLayout.CENTER);

		gui.p(this);
	}

	public Object i() throws Exception {
		return panel;
	}

	public void p(Object obj) throws Exception {
		String input = (String) obj;

		File dir = new File(fieldDir.getText());
		if (!dir.isDirectory())
			throw new Exception("Root directory not found: " + dir);

		engine.p(new Object[] { dir, input });
	}
}
