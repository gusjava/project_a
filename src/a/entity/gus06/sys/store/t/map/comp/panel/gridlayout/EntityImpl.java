package a.entity.gus06.sys.store.t.map.comp.panel.gridlayout;

import a.framework.*;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridLayout;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140908";}

	public static final String KEY_X = "x";
	public static final String KEY_Y = "y";
	public static final String KEY_GAP = "gap";



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
		
		int x = int_(get(map,KEY_X,"1"));
		int y = int_(get(map,KEY_Y,"1"));
		int gap = int_(get(map,KEY_GAP,"0"));
		
		JPanel panel = new JPanel(new GridLayout(x,y,gap,gap));
		custComp.p(new Object[]{panel,map});
		
		for(int i=0;i<x;i++)
		for(int j=0;j<y;j++)
		{
			String id = get(map,"child-"+i+"-"+j);
			if(id!=null) panel.add(comp(id));
		}
		
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
