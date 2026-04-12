package a.entity.gus06.sys.form1.item.slider;

import a.framework.*;
import javax.swing.JSlider;
import java.util.Map;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, V, R, ChangeListener, ActionListener {
	public String creationDate() {return "20260306";}
	
	public static final String KEY_MIN = "min";
	public static final String KEY_MAX = "max";
	public static final String KEY_VALUE = "value";

	private JSlider slider;
	private Object dataHolder;
	private Map config;

	private boolean initialized = false;
	private boolean compToHolder = false;
	private boolean holderToComp = false;

	public EntityImpl() throws Exception
	{
		slider = new JSlider();
		slider.addChangeListener(this);
	}

	public Object i() throws Exception
	{return slider;}

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
		if(dataHolder==null || config==null) return;
		if(initialized) throw new Exception("Item already initialized");

		int min = getIntConfig(KEY_MIN, slider.getMinimum());
		int max = getIntConfig(KEY_MAX, slider.getMaximum());
		int value = getIntConfig(KEY_VALUE, min);

		slider.setMinimum(min);
		slider.setMaximum(max);
		slider.setValue(value);

		((S)dataHolder).addActionListener(this);
		holderToComp();
		initialized = true;
	}

	private void resetComp() throws Exception
	{
		if(dataHolder!=null) ((S)dataHolder).removeActionListener(this);
		dataHolder = null;
		config = null;
		slider.setValue(slider.getMinimum());
	}

	public void actionPerformed(ActionEvent e)
	{
		if(!compToHolder) holderToComp();
	}

	public void stateChanged(ChangeEvent e)
	{
		if(!holderToComp && !slider.getValueIsAdjusting()) compToHolder();
	}

	private void compToHolder()
	{
		compToHolder = true;
		try
		{
			Integer value = slider.getValue();
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
			Integer val = (Integer) ((G)dataHolder).g();
			if(val != null) slider.setValue(val);
		}
		catch(Exception e)
		{Outside.err(this,"holderToComp()",e);}
		holderToComp = false;
	}

	private int getIntConfig(String key, int defaultValue)
	{
		if(config.containsKey(key))
		{
			try {return Integer.parseInt(""+config.get(key));}
			catch(Exception e) {}
		}
		return defaultValue;
	}
}
