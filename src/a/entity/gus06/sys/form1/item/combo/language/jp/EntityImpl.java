package a.entity.gus06.sys.form1.item.combo.language.jp;

import a.framework.*;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class EntityImpl implements Entity, I, V, R, ActionListener {
	public String creationDate() {return "20221108";}

	private Service buildCombo;
	
	private JComboBox comboBox;
	private Object dataHolder;
	private Map config;
	
	private ActionListener compListener;
	
	private boolean initialized = false;
	private boolean compToHolder = false;
	private boolean holderToComp = false;

	public EntityImpl() throws Exception
	{
		buildCombo = Outside.service(this,"gus06.swing.combobox.build.language.jp");
		
		compListener = new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{if(!holderToComp) compToHolder();}
		};
		
		comboBox = (JComboBox) buildCombo.i();
		comboBox.addActionListener(compListener);
	}
	
	public Object i() throws Exception
	{return comboBox;}
	
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
		comboBox.removeAllItems();
	}
	
	public void actionPerformed(ActionEvent e) {if(!compToHolder) holderToComp();}
	
	private void compToHolder()
	{
		compToHolder = true;
		try
		{
			String value = compToValue();
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
			String value = holderToValue();
			if(value!=null) comboBox.setSelectedItem(value);
			else comboBox.setSelectedIndex(-1);
		}
		catch(Exception e)
		{Outside.err(this,"holderToComp()",e);}
		holderToComp = false;
	}
	
	private String compToValue() throws Exception
	{
		if(comboBox.getSelectedIndex()==-1) return "";
		return ""+comboBox.getSelectedItem();
	}
	
	private String holderToValue() throws Exception
	{
		return (String) ((G) dataHolder).g();
	}
}