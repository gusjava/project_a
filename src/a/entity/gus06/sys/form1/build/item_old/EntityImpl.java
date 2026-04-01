package a.entity.gus06.sys.form1.build.item_old;

import a.framework.*;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260307";}

	public static final String KEY_TYPE = "type";
	public static final String KEY_KEY = "key";

	private Service buildSubMap;
	private Service buildDataHolder;
	private Service typeToName;
	private Service factory;
	private Service custComp;

	public EntityImpl() throws Exception
	{
		buildSubMap = Outside.service(this,"gus06.map.string.submap");
		buildDataHolder = Outside.service(this,"gus06.map.wrap.key.support");
		typeToName = Outside.service(this,"gus06.sys.form1.typetoname");
		factory = Outside.service(this,"entitynew");
		custComp = Outside.service(this,"gus06.data.perform.cust");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Map dataMap = (Map) o[0];
		Map confMap = (Map) o[1];
		String id = (String) o[2];
		
		String type = get(confMap, KEY_TYPE);
		String key = get(confMap, KEY_KEY, id);
		
		if(type==null) return new Item0("<"+id+": NO TYPE FOUND>");
		if(type.equals("")) return new Item0("<"+id+": EMPTY TYPE FOUND>");
		
		Object dataHolder = buildDataHolder.t(new Object[]{dataMap, key});
		
		String entityName = (String) typeToName.t(type);
		if(entityName==null) return new Item0("<"+id+": TYPE NOT SUPPORTED: "+type+">");
		
		Object item = factory.t(entityName);
		
		((V)item).v("dataHolder",dataHolder);
		((V)item).v("config",confMap);
		
		JComponent comp = (JComponent) ((I) item).i();
		Map custMap = (Map) buildSubMap.t(new Object[]{confMap, "cust."});
		custComp.p(new Object[]{comp, custMap});
		
		return item;
	}
	
	private String get(Map m, String key)
	{return m.containsKey(key) ? (String) m.get(key) : null;}
	
	private String get(Map m, String key, String defaultValue)
	{return m.containsKey(key) ? (String) m.get(key) : defaultValue;}
	
	public class Item0 implements I, V, R
	{
		private JLabel label;
		
		public Item0(String message)
		{label = new JLabel(message);}
		
		public Object r(String key) throws Exception
		{return null;}
		
		public void v(String key, Object obj) throws Exception {}
		
		public Object i() throws Exception
		{return label;}
	}
}