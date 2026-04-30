package a.entity.gus.y.knowledgesys1.gui.knowledge.add;

import a.framework.*;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20260430";}

	private Service dialogOkCancel;
	private Service formPanel;
	private Service insert;

	private JTextField fieldCode = new JTextField();
	private JTextField fieldState = new JTextField();
	private JTextField fieldAction = new JTextField();
	private JTextField fieldObject = new JTextField();
	private JTextArea taDescription = new JTextArea();

	private JPanel contentPanel;

	public EntityImpl() throws Exception
	{
		dialogOkCancel = Outside.service(this, "gus06.swing.dialog.blocked1.okcancel");
		formPanel = Outside.service(this, "*gus.x.swing.panel.formpanel");
		insert = Outside.service(this, "gus.y.knowledgedb1.knowledge.insert");

		taDescription.setLineWrap(true);
		taDescription.setWrapStyleWord(true);
		taDescription.setMargin(new Insets(3, 3, 3, 3));

		formPanel.v("code", fieldCode);
		formPanel.v("state", fieldState);
		formPanel.v("action", fieldAction);
		formPanel.v("object", fieldObject);

		contentPanel = new JPanel(new BorderLayout());
		contentPanel.add((JComponent) formPanel.i(), BorderLayout.NORTH);
		contentPanel.add(new JScrollPane(taDescription), BorderLayout.CENTER);
	}

	public void p(Object obj) throws Exception
	{
		Service engine = (Service) obj;

		fieldCode.setText("");
		fieldState.setText("");
		fieldAction.setText("");
		fieldObject.setText("");
		taDescription.setText("");

		dialogOkCancel.v("width", "800");
		dialogOkCancel.v("height", "600");
		boolean ok = dialogOkCancel.f(contentPanel);
		if (!ok) return;

		Map data = new HashMap();
		data.put("code", fieldCode.getText().trim());
		data.put("state", fieldState.getText().trim());
		data.put("action", fieldAction.getText().trim());
		data.put("object", fieldObject.getText().trim());
		data.put("description", taDescription.getText());

		insert.t(new Object[]{engine.r("cx"), data});
		engine.e();
	}
}