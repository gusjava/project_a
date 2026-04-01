package a.entity.gus06.sys.expression1.apply.op._jtabbedpane;

import a.framework.*;
import java.util.Map;
import java.util.List;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160831";}

	public final static String KEY_DISPLAY = "display";
	public final static String KEY_CONTENT = "content";
	public final static String KEY_ACTIVE = "active";
	
	
	private Service addTab;
	
	public EntityImpl() throws Exception
	{
		addTab = Outside.service(this,"gus06.swing.tabbedpane.addtab");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof List) return build((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private JTabbedPane build(List list) throws Exception
	{
		JTabbedPane tab = new JTabbedPane();
		
		for(int i=0;i<list.size();i++)
		addTab(tab,(Map) list.get(i));
		
		return tab;
	}
	
	
	private void addTab(JTabbedPane tab, Map map) throws Exception
	{
		String display = (String) get(map,KEY_DISPLAY);
		JComponent content = (JComponent) get(map,KEY_CONTENT);
		Boolean active = (Boolean) get(map,KEY_ACTIVE);
		
		if(display!=null && content!=null)
			addTab.p(new Object[]{tab,content,display});
	}
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
