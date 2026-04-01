package a.entity.gus06.sys.form1.gui.maingui;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Font;

public class EntityImpl implements Entity, I, P, V, R {

	public String creationDate() {return "20221105";}

	public static final String KEY_STRUCT = "structure";
	public static final String KEY_DISPLAY = "display";

	private Service buildSubMap;
	private Service paintLabel;
	private Service buildItem;
	private Service formGui;
	
	private Map dataMap;
	private Map configMap;
	private Map itemConfMap0;
	
	private JPanel panel;
	
	private Map itemMap;
	private Map compMap;
	private Map labelMap;



	public EntityImpl() throws Exception
	{
		buildSubMap = Outside.service(this,"gus06.map.string.submap");
		paintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		buildItem = Outside.service(this,"gus06.sys.form1.build.item");
		formGui = Outside.service(this, "*gus.swing.panel.formpanel2");
		
		panel = (JPanel) formGui.i();
		
		itemMap = new HashMap();
		compMap = new HashMap();
		labelMap = new HashMap();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{initDataMap((Map) obj);}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("configMap")) {initConfigMap((Map) obj);return;}
		if(key.equals("dataMap")) {initDataMap((Map) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("configMap"))return configMap;
		if(key.equals("dataMap"))return dataMap;
		if(key.equals("itemMap"))return itemMap;
		if(key.equals("compMap"))return compMap;
		if(key.equals("labelMap"))return labelMap;
		
		if(key.equals("keys")) 
			return new String[]{"configMap","dataMap","itemMap","compMap","labelMap"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void initConfigMap(Map configMap) throws Exception
	{
		this.configMap = configMap;
		initForm();
	}
	
	private void initDataMap(Map dataMap) throws Exception
	{
		this.dataMap = dataMap;
		initForm();
	}
	
	private void initForm() throws Exception
	{
		if(dataMap==null) return;
		if(configMap==null) return;
    		
		resetItemMap();
		
		itemMap.clear();
		compMap.clear();
		labelMap.clear();
		
		formGui.p("removeAll");
		
		if(!hasConfig(KEY_STRUCT)) return;
		String idList = config(KEY_STRUCT);
		String[] ids = idList.split(";");
		
		itemConfMap0 = subConfig("item.*.");
		for(int i=0;i<ids.length;i++)
		{
			String id = ids[i];
			if(id!=null && !id.equals(""))
			{
				if(id.equals("|")) handleSep();
				else handleId(id);
			}
		}
		formGui.p("repaint");
	}
	
	
	
	private void resetItemMap() throws Exception
	{
		if(itemMap==null) return;
		Iterator it = itemMap.keySet().iterator();
		while(it.hasNext())
		{
			String id = (String) it.next();
			V v = (V) itemMap.get(id);
			v.v("reset",null);
		}
	}
	
	
	
	
	private void handleId(String id) throws Exception
	{
		Map confMap = new HashMap(itemConfMap0);
		confMap.putAll(subConfig("item."+id+"."));
		String display = get(confMap, KEY_DISPLAY, id);
		
		I item = (I) buildItem.t(new Object[]{dataMap, confMap, id});
		
		JComponent comp = (JComponent) item.i();
		JLabel label = buildLabel(display);
		
		itemMap.put(id,item);
		compMap.put(id,comp);
		labelMap.put(id,label);
		
		formGui.v("add",new Object[]{label,comp});
	}
	
	private void handleSep() throws Exception
	{
		formGui.v("add",new Object[]{labelBlank(),labelBlank()});
	}
	
	
	
	
	private JLabel buildLabel(String display) throws Exception
	{
		JLabel label = new JLabel();
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		paintLabel.v(display, label);
		return label;
	}
	
	private JLabel labelBlank() throws Exception
	{
		return new JLabel(" ");
	}
	
	
	
	private String get(Map m, String key)
	{return (String) m.get(key);}
	
	private boolean has(Map m, String key)
	{return m!=null && m.containsKey(key);}
	
	private String get(Map m, String key, String defaultValue)
	{return has(m, key) ? get(m, key) : defaultValue;}
	
	
	
	private String config(String key)
	{return (String) configMap.get(key);}
	
	private boolean hasConfig(String key)
	{return configMap!=null && configMap.containsKey(key);}
	
	private String config(String key, String defaultValue)
	{return hasConfig(key) ? config(key) : defaultValue;}
	
	
	
	private Map subConfig(String offset) throws Exception
	{return (Map) buildSubMap.t(new Object[]{configMap, offset});}
}