package a.entity.gus.y.logger1.view1;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I, ActionListener {
	public String creationDate() {return "20240111";}
	
	private Service logger;
	private JScrollPane scroll;
	private JTextArea area;

	public EntityImpl() throws Exception {
		logger = Outside.service(this, "logger");
		
		area = new JTextArea();
		area.setMargin(new Insets(5, 5, 5, 5));
		area.setEditable(false);
		area.setBackground(Color.BLACK);
		area.setForeground(Color.WHITE);
		
		scroll = new JScrollPane(area);
		
		refresh();
		logger.addActionListener(this);
	}
	
	public Object i() throws Exception {
		return scroll;
	}

	public void actionPerformed(ActionEvent e) {
		refresh();
	}
	
	private void refresh() {
		try {
			String log = (String) logger.g();
			area.setText(log);
			area.setCaretPosition(log.length());
		}
		catch(Exception e) {
			Outside.err(this, "refresh()", e);
		}
	}
}
