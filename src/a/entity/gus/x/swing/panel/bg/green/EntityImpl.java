package a.entity.gus.x.swing.panel.bg.green;

import java.awt.Color;

import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.I;

public class EntityImpl implements Entity, I {
	public String creationDate() {
		return "20231112";
	}

	private JPanel panel;

	public EntityImpl() {
		panel = new JPanel();
		panel.setBackground(Color.GREEN);
	}

	public Object i() throws Exception {
		return panel;
	}
}
