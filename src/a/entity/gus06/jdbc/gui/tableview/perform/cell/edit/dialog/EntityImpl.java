package a.entity.gus06.jdbc.gui.tableview.perform.cell.edit.dialog;

import a.framework.*;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;

public class EntityImpl implements Entity, T, G, ItemListener {

	public String creationDate() {return "20230401";}
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 300;


	private Service dialog;
	private Service buildLabelTitle;
	private Service buildTextArea;
	private Service okCancel;
	private Service onF1;
	private Service onEscape;
	
	private JLabel labelTitle;
	private JTextArea textArea;
	private JCheckBox nullCheck;
	private JPanel panel;
	
	
	public EntityImpl() throws Exception
	{
		dialog = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel");
		buildLabelTitle = Outside.service(this,"gus06.swing.label.build.titlelabel1");
		buildTextArea = Outside.service(this,"gus06.swing.textarea.buildarea1");
		okCancel = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel");
		onF1 = Outside.service(this,"gus.x.swing.comp.cust3.execute.f1");
		onEscape = Outside.service(this,"gus.x.swing.comp.cust3.execute.escape");
		
		labelTitle = (JLabel) buildLabelTitle.i();
		textArea = (JTextArea) buildTextArea.i();
		
		nullCheck = new JCheckBox("NULL");
		nullCheck.addItemListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(labelTitle,BorderLayout.NORTH);
		panel.add(new JScrollPane(textArea),BorderLayout.CENTER);
		panel.add(nullCheck,BorderLayout.SOUTH);
		
		E executeOk = (E) this::validate;
		E executeCancel = (E) this::cancel;
		
		onF1.p(new Object[]{textArea, executeOk});
		onEscape.p(new Object[]{textArea, executeCancel});
	}
	
	
	
	public void itemStateChanged(ItemEvent e)
	{textArea.setEnabled(!nullCheck.isSelected());}
	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String)
		{
			String title = (String) obj;
			String initValue = "";
			return askForInput(title,initValue);
		}
		if(obj instanceof String[])
		{
			String[] o = (String[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			String title = o[0];
			String initValue = o[1];
			return askForInput(title,initValue);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	public Object g() throws Exception
	{
		return askForInput("INPUT","");
	}
	
	
	private void validate()
	{
		try
		{
			okCancel.v("do","ok");
		}
		catch(Exception e)
		{Outside.err(this,"validate()",e);}
	}
	
	private void cancel()
	{
		try
		{
			if(!textArea.getText().equals("")) textArea.setText("");
			else okCancel.v("do","cancel");
		}
		catch(Exception e)
		{Outside.err(this,"cancel()",e);}
	}
	
	
	
	
	private G askForInput(String title, String initValue) throws Exception
	{
		if(initValue==null)
		{
			nullCheck.setSelected(true);
			textArea.setEnabled(false);
		}
		else
		{
			nullCheck.setSelected(false);
			textArea.setText(initValue);
			textArea.selectAll();
		}
		
		labelTitle.setText(title);
		okCancel.v("width",""+WIDTH);
		okCancel.v("height",""+HEIGHT);
		boolean result = okCancel.f(panel);
		
		return result ? wrapValue() : null;
	}
	
	private G wrapValue()
	{
		if(nullCheck.isSelected()) return new ValueWrapper(null);
		return new ValueWrapper(textArea.getText());
	}
	
	private class ValueWrapper implements G
	{
		private Object value;
		public ValueWrapper(Object value) {this.value = value;}
		
		public Object g() throws Exception
		{return value;}
	}
}