package a.entity.gus06.sys.store.t.map.comp.label;

import a.framework.*;
import java.util.Map;
import javax.swing.JLabel;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140907";}

	private Service custComp;
	private Service custLabel;
	
	public EntityImpl() throws Exception
	{
		custComp = Outside.service(this,"gus06.sys.store.t.map.comp.cust3.map1");
		custLabel = Outside.service(this,"gus06.sys.store.t.map.comp.label.cust3.map1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		JLabel label = new JLabel();
		custComp.p(new Object[]{label,map});
		custLabel.p(new Object[]{label,map});
		
		return label;
	}
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}
