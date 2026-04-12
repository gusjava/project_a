package a.entity.gus.y.dataview1.class1.gui1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import a.framework.Entity;
import a.framework.G;
import a.framework.I;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, G, P, I, ActionListener {
	public String creationDate() {return "20240125";}

	private Service shiftPanel;
	private Service newViewer;

	private JPanel panel;
	private JButton button;

	private Class data;
	private Constructor constructor;
	private Object viewer;

	public EntityImpl() throws Exception {
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		newViewer = Outside.service(this, "factory#gus.y.dataview1.object");

		button = new JButton("Instanciate");
		button.addActionListener(this);
		button.setEnabled(false);

		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) shiftPanel.i(), BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
	}

	public Object g() throws Exception {
		return data;
	}

	public void p(Object obj) throws Exception {
		data = (Class) obj;
		constructor = finConstructor();
		
		if(viewer!=null) ((P) viewer).p(null);
		button.setEnabled(constructor!=null);
	}

	public Object i() throws Exception {
		return panel;
	}

	public void actionPerformed(ActionEvent e) {
		perform();
	}

	private void perform() {
		try {
			if (constructor == null) return;
			Object result = constructor.newInstance();
			if(viewer==null) initViewer();
			((P) viewer).p(result);
		} catch (Exception e) {
			Outside.err(this, "perform()", e);
		}
	}
	
	private void initViewer() throws Exception {
		viewer = newViewer.g();
		shiftPanel.p(viewer);
	}
	
	private Constructor finConstructor() {
		try {
			if(data==null) return null;
			return data.getDeclaredConstructor();
		} catch (NoSuchMethodException e) {
			return null;
		}
	}
}
