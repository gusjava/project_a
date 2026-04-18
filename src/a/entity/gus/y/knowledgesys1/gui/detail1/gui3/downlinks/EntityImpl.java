package a.entity.gus.y.knowledgesys1.gui.detail1.gui3.downlinks;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.JTextField;

import a.framework.*;

public class EntityImpl implements Entity, I, P {
	public String creationDate() {return "20260418";}

	private JPanel panel;
	private Map data;

	public EntityImpl() throws Exception {

		panel = new JPanel(new BorderLayout());
	}

	public Object i() throws Exception {
		return panel;
	}

	public void p(Object obj) throws Exception {
		data = (Map) obj;
	}
}
