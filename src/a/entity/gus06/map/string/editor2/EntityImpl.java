package a.entity.gus06.map.string.editor2;

import a.framework.*;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20230215";}

	public static final String DEFAULT_TYPE = "string";
	
	private Service buildSupport;
	private Service form;
	private Service fieldBuilder;
	private Service listToArray;
	
	private Map map;
	private Map configMap;
	private Map fieldMap;

	public EntityImpl() throws Exception
	{
		buildSupport = Outside.service(this,"gus06.map.build.supportmap");
		form = Outside.service(this,"*gus06.swing.panel.formpanel");
		fieldBuilder = Outside.service(this,"gus06.map.string.editor2.fieldbuilder");
		listToArray = Outside.service(this,"gus06.convert.listtostringarray");
		
		fieldMap = new HashMap();
	}

	public Object i() throws Exception
	{return form.i();}
	
	
	public void p(Object obj) throws Exception
	{
		map = toSupportMap((Map) obj);
		
		Iterator it = fieldMap.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Object field = fieldMap.get(key);
			
			((P)field).p(map);
		}
	}
	
	private Map toSupportMap(Map m) throws Exception
	{
		if(m==null) return null;
		if(m instanceof S) return m;
		return (Map) buildSupport.t(m);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("init")) {init((Map) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	private void init(Map config) throws Exception
	{
		if(configMap!=null) throw new Exception("Editor already initialized");
		configMap = config;
		
		if(!configMap.containsKey("struct"))
			throw new Exception("struct key not found inside config map");
		
		String[] struct = toArray(configMap.get("struct"));
		for(int i=0;i<struct.length;i++)
		{
			String key = struct[i];
			String displayKey = key+".display";
			String typeKey = key+".type";
			
			String display = configMap.containsKey(displayKey) ? (String) configMap.get(displayKey) : key;
			String type = configMap.containsKey(typeKey) ? (String) configMap.get(typeKey) : DEFAULT_TYPE;
			
			Object field = fieldBuilder.t(new String[]{type, key});
			fieldMap.put(key,field);
			form.v(display,((I) field).i());
		}
	}
	
	
	private String[] toArray(Object data) throws Exception
	{
		if(data instanceof String[]) return (String[]) data;	
		if(data instanceof List) return (String[]) listToArray.t(data);	
		if(data instanceof String) return ((String) data).split(";");
		throw new Exception("Unsupported data type: "+data.getClass().getName());
	}
}