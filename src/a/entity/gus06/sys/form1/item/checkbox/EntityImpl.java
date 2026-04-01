package a.entity.gus06.sys.form1.item.checkbox;

import a.framework.*;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.util.Objects;

public class EntityImpl implements Entity, I, V, R, ActionListener {
	public String creationDate() {return "20221106";}

	public static final String KEY_VALUE_CHECKED = "value.checked";
	public static final String KEY_VALUE_UNCHECKED = "value.unchecked";

	private JCheckBox checkBox;
	private Object dataHolder;
	private Map config;
	
	private ActionListener compListener;
	
	private boolean initialized = false;
	private boolean compToHolder = false;
	private boolean holderToComp = false;
	
	private String valueChecked;
	private String valueUnchecked;

	public EntityImpl() throws Exception
	{
		compListener = new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{if(!holderToComp) compToHolder();}
		};
		checkBox = new JCheckBox();
		checkBox.addActionListener(compListener);
	}
	
	public Object i() throws Exception
	{return checkBox;}
	
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
		valueChecked = getValueChecked();
		valueUnchecked = getValueUnchecked();
		initialized = true;
	}
	
	private void resetComp() throws Exception
	{
		if(dataHolder!=null) ((S)dataHolder).removeActionListener(this);
		dataHolder = null;
		config = null;
		valueChecked = null;
		valueUnchecked = null;
		checkBox.setSelected(false);
	}
	
	public void actionPerformed(ActionEvent e) {if(!compToHolder) holderToComp();}
	
	private void compToHolder()
	{
		compToHolder = true;
		try
		{
			String value = checkBox.isSelected() ? valueChecked : valueUnchecked;
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
			boolean selected = Objects.equals(value, valueChecked);
			checkBox.setSelected(selected);
		}
		catch(Exception e)
		{Outside.err(this,"holderToComp()",e);}
		holderToComp = false;
	}

	private String getConfig(String key, String defaultValue)
	{return config.containsKey(key) ? (String) config.get(key) : defaultValue;}
	
	private String getValueChecked()
	{return getConfig(KEY_VALUE_CHECKED,"true");}
	
	private String getValueUnchecked()
	{return getConfig(KEY_VALUE_UNCHECKED,"false");}
}