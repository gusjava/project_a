package a.entity.gus06.sys.store.t.map.comp.panel.borderlayout;

import a.framework.*;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140907";}

	public static final String KEY_NORTH = "child-north";
	public static final String KEY_SOUTH = "child-south";
	public static final String KEY_CENTER = "child-center";
	public static final String KEY_EAST = "child-east";
	public static final String KEY_WEST = "child-west";
	
	
	
	private Service findObj;
	private Service custComp;
	
	public EntityImpl() throws Exception
	{
		findObj = Outside.service(this,"gus06.sys.store.obj.find");
		custComp = Outside.service(this,"gus06.sys.store.t.map.comp.cust3.map1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		JPanel panel = new JPanel(new BorderLayout());
		custComp.p(new Object[]{panel,map});
		
		String child_n = get(map,KEY_NORTH);
		String child_s = get(map,KEY_SOUTH);
		String child_c = get(map,KEY_CENTER);
		String child_e = get(map,KEY_EAST);
		String child_w = get(map,KEY_WEST);
		
		if(child_n!=null) panel.add(comp(child_n),BorderLayout.NORTH);
		if(child_s!=null) panel.add(comp(child_s),BorderLayout.SOUTH);
		if(child_c!=null) panel.add(comp(child_c),BorderLayout.CENTER);
		if(child_e!=null) panel.add(comp(child_e),BorderLayout.EAST);
		if(child_w!=null) panel.add(comp(child_w),BorderLayout.WEST);
		
		return panel;
	}
	
	
	
	
	
	private JComponent comp(String rule) throws Exception
	{return (JComponent) findObj.t(rule);}
	
	private int int_(String s)
	{return Integer.parseInt(s);}
	
	private String get(Map map, String key)
	{return get(map,key,null);}
	
	private String get(Map map, String key, String defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return (String) map.get(key);
	}
}
