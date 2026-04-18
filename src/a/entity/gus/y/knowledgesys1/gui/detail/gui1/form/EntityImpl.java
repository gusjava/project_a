package a.entity.gus.y.knowledgesys1.gui.detail.gui1.form;

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
	public String creationDate() {return "20260414";}

	private Service formPanel;

	private JPanel mainPanel;

	private JTextField fieldId = new JTextField();
	private JTextField fieldDateCreated = new JTextField();
	private JTextField fieldDateUpdated = new JTextField();
	private JTextField fieldCode = new JTextField();
	private JTextField fieldState = new JTextField();
	private JTextField fieldAction = new JTextField();
	private JTextField fieldObject = new JTextField();
	private JTextArea taDescription = new JTextArea();

	public EntityImpl() throws Exception {
		formPanel = Outside.service(this, "*gus.x.swing.panel.formpanel");

		fieldId.setEditable(false);
		fieldDateCreated.setEditable(false);
		fieldDateUpdated.setEditable(false);
		fieldCode.setEditable(false);
		fieldState.setEditable(false);
		fieldAction.setEditable(false);
		fieldObject.setEditable(false);
		
		taDescription.setEditable(false);
		taDescription.setLineWrap(true);
		taDescription.setWrapStyleWord(true);
		taDescription.setMargin(new Insets(3,3,3,3));

		formPanel.v("id", fieldId);
		formPanel.v("date_created", fieldDateCreated);
		formPanel.v("date_updated", fieldDateUpdated);
		formPanel.v("code", fieldCode);
		formPanel.v("state", fieldState);
		formPanel.v("action", fieldAction);
		formPanel.v("object", fieldObject);

		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add((JComponent) formPanel.i(), BorderLayout.NORTH);
		mainPanel.add(new JScrollPane(taDescription), BorderLayout.CENTER);
	}

	public Object i() throws Exception {
		return mainPanel;
	}

	public void p(Object obj) throws Exception {
		if (obj == null) {
			fieldId.setText("");
			fieldDateCreated.setText("");
			fieldDateUpdated.setText("");
			fieldCode.setText("");
			fieldState.setText("");
			fieldAction.setText("");
			fieldObject.setText("");
			taDescription.setText("");
			return;
		}

		Map m = (Map) obj;
		fieldId.setText(str(m.get("id")));
		fieldDateCreated.setText(str(m.get("date_created")));
		fieldDateUpdated.setText(str(m.get("date_updated")));
		fieldCode.setText(str(m.get("code")));
		fieldState.setText(str(m.get("state")));
		fieldAction.setText(str(m.get("action")));
		fieldObject.setText(str(m.get("object")));
		
		taDescription.setText(str(m.get("description")));
		taDescription.setCaretPosition(0);
	}

	private String str(Object value) {
		return value != null ? value.toString() : "";
	}
}
