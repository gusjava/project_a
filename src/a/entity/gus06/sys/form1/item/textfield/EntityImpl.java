package a.entity.gus06.sys.form1.item.textfield;

import a.framework.*;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, V, R, DocumentListener, ActionListener {
	public String creationDate() {return "20221105";}

	private JTextField textField;
	private Object dataHolder;
	private Map config;
	
	private boolean initialized = false;
	private boolean compToHolder = false;
	private boolean holderToComp = false;

	public EntityImpl() throws Exception
	{
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
		if(dataHolder==null) ((S)dataHolder).removeActionListener(this);
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
			String value = textField.getText();
			((P)dataHolder).p(value);
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
		}
		catch(Exception e)
		{Outside.err(this,"holderToComp()",e);}
		holderToComp = false;
	}
}