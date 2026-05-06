package a.entity.gus.z.appli1.gui2_3_3.y.detail.doc;

import javax.swing.JPanel;

import a.framework.*;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20260506";}

	private JPanel panel;

	public EntityImpl() throws Exception
	{
		panel = new JPanel();
	}

	public Object i() throws Exception {
		return panel;
	}

	public void p(Object obj) throws Exception {
	}
}
