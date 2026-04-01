package a.entity.gus06.sys.store.t.map.comp.button;

import a.framework.*;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.Action;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140908";}

	public static final String KEY_DISPLAY = "display";
	public static final String KEY_ACTION = "action";

	private Service repaint;
	private Service custComp;
	private Service findObj;
	
	public EntityImpl() throws Exception
	{
		repaint = Outside.service(this,"gus06.swing.button.cust2.display");
		custComp = Outside.service(this,"gus06.sys.store.t.map.comp.cust3.map1");
		findObj = Outside.service(this,"gus06.sys.store.obj.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		JButton button = new JButton();
		custComp.p(new Object[]{button,map});
		
		String display = get(map,KEY_DISPLAY);
		if(display!=null) repaint.v(display,button);
		
		String action = get(map,KEY_ACTION);
		if(action!=null) button.setAction(action(action));
		
		return button;
	}
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	private Action action(String rule) throws Exception
	{return (Action) findObj.t(rule);}
}
