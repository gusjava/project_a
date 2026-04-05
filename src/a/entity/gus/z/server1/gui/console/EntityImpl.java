package a.entity.gus.z.server1.gui.console;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JTextArea;

import a.framework.Entity;
import a.framework.I;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20260405";}

	private JTextArea area;

	public EntityImpl() throws Exception {
		area = new JTextArea();
		area.setEditable(false);
		area.setBackground(Color.BLACK);
		area.setForeground(Color.WHITE);
		area.setMargin(new Insets(5, 5, 5, 5));
	}

	public Object i() throws Exception {
		return area;
	}
}
