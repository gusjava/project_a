package a.entity.gus06.sys.form1.item.email1;

import a.framework.*;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, I, V, R, DocumentListener, ActionListener {
	public String creationDate() {return "20221107";}

	private Service isEmail;

	private JTextField textField;
	private Object dataHolder;
	private Map config;
	
	private boolean initialized = false;
	private boolean compToHolder = false;
	private boolean holderToComp = false;

	public EntityImpl() throws Exception
	{
		isEmail = Outside.service(this,"gus06.filter.string.is.email");
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(this);
	}
	
	public Object i() throws Exception
	{return textField;}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("dataHolder")) return dataHolder;
		if(key.equals("config")) return config;
		
		if(key.equals("keys")) return new String[]{"dataHolder","config"};
		throw new Exception("Unknown key: "+key);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("dataHolder")) {initDataHolder(obj);return;}
		if(key.equals("config")) {initConfig((Map) obj);return;}
		if(key.equals("reset")) {resetComp();return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	private void initDataHolder(Object dataHolder) throws Exception
	{
		this.dataHolder = dataHolder;
		initComp();
	}
	
	private void initConfig(Map config) throws Exception
	{
		this.config = config;
		initComp();
	}
	
	private void initComp() throws Exception
	{
		if(dataHolder==null) return;
		if(config==null) return;
		if(initialized) throw new Exception("Item already initialized");
		
		((S)dataHolder).addActionListener(this);
		holderToComp();
		initialized = true;
	}
	
	private void resetComp() throws Exception
	{
		if(dataHolder!=null) ((S)dataHolder).removeActionListener(this);
		dataHolder = null;
		config = null;
		textField.setText("");
	}
	
	public void changedUpdate(DocumentEvent e) {}
	public void insertUpdate(DocumentEvent e) {if(!holderToComp) compToHolder();}
	public void removeUpdate(DocumentEvent e) {if(!holderToComp) compToHolder();}
	
	public void actionPerformed(ActionEvent e) {if(!compToHolder) holderToComp();}
	
	private void compToHolder()
	{
		compToHolder = true;
		try
		{
			formatCompData();
			String value = textField.getText();
			((P)dataHolder).p(value);
			updateForeground();
		}
		catch(Exception e)
		{Outside.err(this,"compToHolder()",e);}
		compToHolder = false;
	}

	private void holderToComp()
	{
		holderToComp = true;
		try
		{
			String value = (String) ((G) dataHolder).g();
			textField.setText(value);
			updateForeground();
		}
		catch(Exception e)
		{Outside.err(this,"holderToComp()",e);}
		holderToComp = false;
	}

	protected void formatCompData()
	{
		String s = textField.getText();
		final String s1 = s.replace("�","@").replace(";",".").replace(",",".").replace(":",".");
		if(!s.equals(s1))
		{
			SwingUtilities.invokeLater(new Runnable(){
				public void run()
				{
					int p = textField.getCaretPosition();
					textField.setText(s1);
					textField.setCaretPosition(p);
				}
			});
		}
	}
	
	
	private void updateForeground() throws Exception
	{
		String s = textField.getText();
		boolean b = isEmail.f(s);
		Color fg = b ? Color.BLACK : Color.RED;
		textField.setForeground(fg);
	}
}