package a.entity.gus06.sys.store.t.map.comp.component;

import a.framework.*;
import java.util.Map;
import javax.swing.JComponent;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140908";}

	public static final String KEY_CLASS = "class";

	private Service custComp;
	
	public EntityImpl() throws Exception
	{
		custComp = Outside.service(this,"gus06.sys.store.t.map.comp.cust3.map1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String class1 = get(map,KEY_CLASS);
		JComponent comp = (JComponent) Class.forName(class1).newInstance();
		custComp.p(new Object[]{comp,map});
		
		return comp;
	}
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}
