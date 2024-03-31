package a.entity.gus.y.docgenerator1.gui;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I, ActionListener {
	public String creationDate() {return "20231203";}
	
	private Service perform;
	private Service findDev;

	private JTextArea area;
	private JButton buttonPerform;
	private JPanel panel;

	public EntityImpl() throws Exception {
		perform = Outside.service(this, "gus.y.docgenerator1.perform");
		findDev = Outside.service(this, "gus.y.srcroot1.dev");
		
		area = new JTextArea();
		area.setMargin(new Insets(5, 5, 5, 5));
		area.setEditable(false);

		buttonPerform = new JButton("Perform");
		buttonPerform.addActionListener(this);
		buttonPerform.setEnabled(devId()!=null);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
		panel.add(buttonPerform, BorderLayout.SOUTH);
	}
	
	public Object i() throws Exception {
		return panel;
	}
	
	public void actionPerformed(ActionEvent e) {
		perform();
	}

	private void perform() {
		try {
			String generated = (String) perform.g();
			area.setText(generated);
		} catch (Exception e) {
			Outside.err(this, "perform()", e);
		}
	}
	
	private String devId() throws Exception {
		return (String) findDev.g();
	}
}
