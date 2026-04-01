package a.entity.gus06.sys.store.t.map.comp.panel.shift;

import a.framework.*;
import java.util.Map;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140913";}
	
	public static final String KEY_PROVIDER = "provider";
	
	
	private Service findObj;
	private Service custComp;
	private Service buildShift;
	
	public EntityImpl() throws Exception
	{
		findObj = Outside.service(this,"gus06.sys.store.obj.find");
		custComp = Outside.service(this,"gus06.swing.comp.cust3.map1");
		buildShift = Outside.service(this,"gus06.sys.store.t.map.comp.cust3.map1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String provider_ = get(map,KEY_PROVIDER);
		if(provider_==null) throw new Exception("Provider key not found");
		
		Object provider = findObj(provider_);
		Holder holder = new Holder(provider,map);
		
		JPanel panel = (JPanel) buildShift.t(holder);
		custComp.p(new Object[]{panel,map});
		
		return panel;
	}
	
	
	
	
	private Object findObj(String rule) throws Exception
	{return findObj.t(rule);}
	
	private String get(Map map, String key)
	{return get(map,key,null);}
	
	private String get(Map map, String key, String defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return (String) map.get(key);
	}
	
	
	
	private Object build(Map map, String key) throws Exception
	{
		String value = get(map,key);
		if(value==null) throw new Exception("Key not found: "+key);
		
		Object obj = findObj(value);
		if(obj==null) throw new Exception("Object could not be generated");
		
		return obj;
	}
	
	
	
	
	private class Holder implements S, G
	{
		private S s;
		private G g;
		private Map map;
		
		public Holder(Object obj, Map map)
		{
			this.s = (S) obj;
			this.g = (G) obj;
			this.map = map;
		}
		
		public void addActionListener(ActionListener l) throws Exception
		{s.addActionListener(l);}
		
		public void removeActionListener(ActionListener l) throws Exception
		{s.removeActionListener(l);}
		
		public List listeners() throws Exception
		{return s.listeners();}
		
		public Object g() throws Exception
		{
			Object r = g.g();
			if(r instanceof String)
				return build(map,(String)r);
			return r;
		}
	}
}
