package a.entity.gus.x.swing.panel.bg.orange;

import java.awt.Color;

import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;

public class EntityImpl implements Entity, I {
	public String creationDate() {
		return "20231114";
	}

	private JPanel panel;

	public EntityImpl() {
		panel = new JPanel();
		panel.setBackground(Color.ORANGE);
	}

	public Object i() throws Exception {
		return panel;
	}
}
