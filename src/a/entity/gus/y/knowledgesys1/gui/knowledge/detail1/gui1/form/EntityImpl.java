package a.entity.gus.y.knowledgesys1.gui.knowledge.detail1.gui1.form;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;

import a.framework.*;

public class EntityImpl implements Entity, I, P, V {
	public String creationDate() {return "20260414";}

	private Service edit;
	private Service formPanel;
	private Service f2;
	
	private Object engine;
	private Map data;

	private JPanel mainPanel;
	private JButton buttonEdit;

	private JTextField fieldId = new JTextField();
	private JTextField fieldDateCreated = new JTextField();
	private JTextField fieldDateUpdated = new JTextField();
	private JTextField fieldCode = new JTextField();
	private JTextField fieldState = new JTextField();
	private JTextField fieldAction = new JTextField();
	private JTextField fieldObject = new JTextField();
	private JTextField fieldPreprocessor = new JTextField();
	private JTextArea taDescription = new JTextArea();

	public EntityImpl() throws Exception
	{
		edit = Outside.service(this,"gus.y.knowledgesys1.gui.knowledge.edit");
		formPanel = Outside.service(this, "*gus.x.swing.panel.formpanel");
		f2 = Outside.service(this, "gus.x.swing.comp.cust3.execute.f2");

		fieldId.setEditable(false);
		fieldDateCreated.setEditable(false);
		fieldDateUpdated.setEditable(false);
		fieldCode.setEditable(false);
		fieldState.setEditable(false);
		fieldAction.setEditable(false);
		fieldObject.setEditable(false);
		fieldPreprocessor.setEditable(false);

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
		formPanel.v("preprocessor", fieldPreprocessor);

		buttonEdit = new JButton("Edit");
		buttonEdit.addActionListener(e->edit());
		buttonEdit.setEnabled(false);

		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add((JComponent) formPanel.i(), BorderLayout.NORTH);
		mainPanel.add(new JScrollPane(taDescription), BorderLayout.CENTER);
		mainPanel.add(buttonEdit, BorderLayout.SOUTH);
		f2.p(new Object[]{mainPanel, (E)this::edit});
	}

	public Object i() throws Exception
	{
		return mainPanel;
	}

	public void v(String key, Object obj) throws Exception
	{
		if (key.equals("engine")) {engine = obj;return;}
		throw new Exception("Unkwown key: "+key);
	}

	public void p(Object obj) throws Exception
	{
		if (obj == null)
		{
			data = null;
			
			fieldId.setText("");
			fieldDateCreated.setText("");
			fieldDateUpdated.setText("");
			fieldCode.setText("");
			fieldState.setText("");
			fieldAction.setText("");
			fieldObject.setText("");
			fieldPreprocessor.setText("");
			taDescription.setText("");
			buttonEdit.setEnabled(false);
			return;
		}

		data = (Map) obj;
		
		fieldId.setText(valueFor("id"));
		fieldDateCreated.setText(valueFor("date_created"));
		fieldDateUpdated.setText(valueFor("date_updated"));
		fieldCode.setText(valueFor("code"));
		fieldState.setText(valueFor("state"));
		fieldAction.setText(valueFor("action"));
		fieldObject.setText(valueFor("object"));
		fieldPreprocessor.setText(valueFor("preprocessor"));
		buttonEdit.setEnabled(true);
		
		taDescription.setText(valueFor("description"));
		taDescription.setCaretPosition(0);
	}

	private String valueFor(String key)
	{
		if(data==null) return "";
		Object value = data.get(key);
		return value != null ? value.toString() : "";
	}
	
	private void edit()
	{
		try
		{
			if(data==null || engine==null) return;
			edit.p(new Object[]{engine, data});
		}
		catch(Exception e)
		{Outside.err(this,"edit()",e);}
	}

}
