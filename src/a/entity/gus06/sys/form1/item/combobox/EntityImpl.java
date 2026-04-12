package a.entity.gus06.sys.form1.item.combobox;

import a.framework.*;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class EntityImpl implements Entity, I, V, R, ActionListener {

	public String creationDate() {return "20221106";}

	public static final String KEY_CONV = "conv";
	
	public static final String CONV_ELEMENT = "element";
	public static final String CONV_INDEX = "index";

	private Service custWhite;
	
	private JComboBox comboBox;
	private Object dataHolder;
	private Map config;
	
	private ActionListener compListener;
	
	private boolean initialized = false;
	private boolean compToHolder = false;
	private boolean holderToComp = false;

	private String conv;

	public EntityImpl() throws Exception
	{
		custWhite = Outside.service(this,"gus06.swing.combobox.cust.white");
		
		compListener = new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{if(!holderToComp) compToHolder();}
		};
		
		comboBox = new JComboBox();
		comboBox.addActionListener(compListener);
		
		custWhite.p(comboBox);
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
		conv = getConv();
		holderToComp();
		initialized = true;
	}
	
	private void resetComp() throws Exception
	{
		if(dataHolder!=null) ((S)dataHolder).removeActionListener(this);
		dataHolder = null;
		config = null;
		conv = null;
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
			int index = holderToIndex();
			comboBox.setSelectedIndex(index);
		}
		catch(Exception e)
		{Outside.err(this,"holderToComp()",e);}
		holderToComp = false;
	}

	private String compToValue() throws Exception
	{
		if(comboBox.getSelectedIndex()==-1) return null;
		if(conv.equals(CONV_INDEX)) return ""+comboBox.getSelectedIndex();
		if(conv.equals(CONV_ELEMENT)) return ""+comboBox.getSelectedItem();
		throw new Exception("Unsupported conv: "+conv);
	}
	
	private int holderToIndex() throws Exception
	{
		String value = (String) ((G) dataHolder).g();
		if(value==null) return -1;
		if(conv.equals(CONV_INDEX)) return indexFromConvIndex(value);
		if(conv.equals(CONV_ELEMENT)) return indexFromConvElement(value);
		throw new Exception("Unsupported conv: "+conv);
	}
	
	private int indexFromConvIndex(String value)
	{
		int newIndex = value==null ? -1 : toInt(value);
		if(newIndex<0 || newIndex>=comboBox.getItemCount()) return -1;
		return newIndex;
	}
	
	private int indexFromConvElement(String value)
	{
		int nb = comboBox.getItemCount();
		for(int i=0;i<nb;i++) 
		if(comboBox.getItemAt(i).equals(value)) return i;
		return -1;
	}
	
	private int toInt(String value)
	{
		try{return Integer.parseInt(value);}
		catch(NumberFormatException e){return -1;}
	}

	private String getConfig(String key, String defaultValue)
	{return config.containsKey(key) ? (String) config.get(key) : defaultValue;}
	
	private String getConv()
	{return getConfig(KEY_CONV,CONV_ELEMENT);}
}