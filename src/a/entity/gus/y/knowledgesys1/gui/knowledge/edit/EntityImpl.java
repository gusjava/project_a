package a.entity.gus.y.knowledgesys1.gui.knowledge.edit;

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
	public String creationDate() {return "20260428";}

	private Service dialogOkCancel;
	private Service formPanel;
	private Service update;

	private JTextField fieldCode = new JTextField();
	private JTextField fieldState = new JTextField();
	private JTextField fieldAction = new JTextField();
	private JTextField fieldObject = new JTextField();
	private JTextField fieldPreprocessor = new JTextField();
	private JTextArea taDescription = new JTextArea();

	private JPanel contentPanel;

	public EntityImpl() throws Exception
	{
		dialogOkCancel = Outside.service(this, "gus06.swing.dialog.blocked1.okcancel");
		formPanel = Outside.service(this, "*gus.x.swing.panel.formpanel");
		update = Outside.service(this, "gus.y.knowledgedb1.knowledge.update");

		taDescription.setLineWrap(true);
		taDescription.setWrapStyleWord(true);
		taDescription.setMargin(new Insets(3, 3, 3, 3));

		formPanel.v("code", fieldCode);
		formPanel.v("state", fieldState);
		formPanel.v("action", fieldAction);
		formPanel.v("object", fieldObject);
		formPanel.v("preprocessor", fieldPreprocessor);

		contentPanel = new JPanel(new BorderLayout());
		contentPanel.add((JComponent) formPanel.i(), BorderLayout.NORTH);
		contentPanel.add(new JScrollPane(taDescription), BorderLayout.CENTER);
	}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Service engine = (Service) o[0];
		Map data = (Map) o[1];

		populate(data);

		dialogOkCancel.v("width", "800");
		dialogOkCancel.v("height", "600");
		boolean ok = dialogOkCancel.f(contentPanel);

		if (!ok) return;

		Map updated = new HashMap(data);
		updated.put("code", fieldCode.getText().trim());
		updated.put("state", fieldState.getText().trim());
		updated.put("action", fieldAction.getText().trim());
		updated.put("object", fieldObject.getText().trim());
		updated.put("preprocessor", fieldPreprocessor.getText().trim());
		updated.put("description", taDescription.getText());

		update.p(new Object[]{engine.r("cx"), updated});
		engine.e();
	}

	private void populate(Map data)
	{
		fieldCode.setText(valueFor(data, "code"));
		fieldState.setText(valueFor(data, "state"));
		fieldAction.setText(valueFor(data, "action"));
		fieldObject.setText(valueFor(data, "object"));
		fieldPreprocessor.setText(valueFor(data, "preprocessor"));
		taDescription.setText(valueFor(data, "description"));
		taDescription.setCaretPosition(0);
	}

	private String valueFor(Map data, String key)
	{
		Object value = data.get(key);
		return value != null ? value.toString() : "";
	}
}
