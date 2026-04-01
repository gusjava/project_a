package a.entity.gus06.sys.form1.build.item;

import a.framework.*;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221105";}

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
		
		return new ItemWrapper(dataMap, confMap, id);
	}
	
	private String get(Map m, String key)
	{return m.containsKey(key) ? (String) m.get(key) : null;}
	
	private String get(Map m, String key, String defaultValue)
	{return m.containsKey(key) ? (String) m.get(key) : defaultValue;}
	
	
	public class ItemWrapper implements I, R
	{
		private Map dataMap;
		private Map confMap;
		private Map custMap;
		private String id;
		
		private String type;
		private String key;
		private String entityName;
		
		private Object dataHolder;
		private Object item;
		private JComponent comp;
		
		public ItemWrapper(Map dataMap, Map confMap, String id) throws Exception
		{
			this.dataMap = dataMap;
			this.confMap = confMap;
			this.id = id;
			
			type = get(confMap, KEY_TYPE);
			key = get(confMap, KEY_KEY, id);
			entityName = (String) typeToName.t(type);
			custMap = (Map) buildSubMap.t(new Object[]{confMap, "cust."});
			
			dataHolder = buildDataHolder.t(new Object[]{dataMap, key});
		
			if(entityName!=null)
			{
				item = factory.t(entityName);
				((V)item).v("dataHolder",dataHolder);
				((V)item).v("config",confMap);
			}
			comp = buildComp();
			custComp.p(new Object[]{comp, custMap});
		}
		
		private JComponent buildComp() throws Exception
		{
			if(type==null) return new JLabel("<"+id+": NO TYPE FOUND>");
			if(type.equals("")) return new JLabel("<"+id+": EMPTY TYPE FOUND>");
			if(entityName==null) return new JLabel("<"+id+": TYPE NOT SUPPORTED: "+type+">");
			if(item==null) return new JLabel("<"+id+": ITEM NOT BUILT>");
			
			return (JComponent) ((I) item).i();
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("id")) return id;
			if(key.equals("type")) return type;
			if(key.equals("key")) return key;
			if(key.equals("entityName")) return entityName;
			
			if(key.equals("dataMap")) return dataMap;
			if(key.equals("confMap")) return confMap;
			if(key.equals("custMap")) return confMap;
			if(key.equals("dataHolder")) return dataHolder;
			
			if(key.equals("keys")) return new String[]{
				"id",
				"type",
				"key",
				"entityName",
				"dataMap",
				"confMap",
				"custMap",
				"dataHolder"
			};
			throw new Exception("Unknown key: "+key);
		}
		
		public Object i() throws Exception
		{return comp;}
	}
}
