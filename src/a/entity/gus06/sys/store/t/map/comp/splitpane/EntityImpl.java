package a.entity.gus06.sys.store.t.map.comp.splitpane;

import a.framework.*;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JSplitPane;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140908";}

	public static final String KEY_LEFT = "child-left";
	public static final String KEY_RIGHT = "child-right";
	public static final String KEY_ORIENTATION = "orientation";
	public static final String KEY_ONETOUCH = "onetouchexpandable";
	public static final String KEY_DIVIDERLOC = "divider-location";
	public static final String KEY_DIVIDERSIZE = "divider-size";
	
	
	
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
		
		int ori = int_(get(map,KEY_ORIENTATION,"1"));
		int loc = int_(get(map,KEY_DIVIDERLOC,"-1"));
		int size = int_(get(map,KEY_DIVIDERSIZE,"-1"));
		boolean oneTouch = bool_(get(map,KEY_ONETOUCH,"false"));
		
		JSplitPane split = new JSplitPane(ori);
		custComp.p(new Object[]{split,map});
		
		split.setOneTouchExpandable(oneTouch);
		if(loc!=-1) split.setDividerLocation(loc);
		if(size!=-1) split.setDividerSize(size);
		
		String child_left = get(map,KEY_LEFT);
		String child_right = get(map,KEY_RIGHT);
		
		if(child_left!=null) split.setLeftComponent(comp(child_left));
		if(child_right!=null) split.setRightComponent(comp(child_right));
		
		return split;
	}
	
	
	
	
	
	private JComponent comp(String rule) throws Exception
	{return (JComponent) findObj.t(rule);}
	
	private int int_(String s)
	{return Integer.parseInt(s);}
	
	private boolean bool_(String s)
	{return Boolean.parseBoolean(s);}
	
	private String get(Map map, String key)
	{return get(map,key,null);}
	
	private String get(Map map, String key, String defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return (String) map.get(key);
	}
}
