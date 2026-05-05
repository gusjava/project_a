package a.entity.gus06.sys.store.t.map.action;

import a.framework.*;
import java.util.Map;
import javax.swing.Action;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140912";}

	public static final String KEY_DISPLAY = "display";
	public static final String KEY_DESC = "description";
	public static final String KEY_EXECUTE = "execute";
	public static final String KEY_ENABLE = "enable";

	private Service exeToAction;
	private Service repaint;
	private Service custEnable;
	private Service findObj;
	
	
	public EntityImpl() throws Exception
	{
		exeToAction = Outside.service(this,"gus.y.convert1.executetoaction.th");
		repaint = Outside.service(this,"gus06.swing.action.cust2.display");
		custEnable = Outside.service(this,"gus06.swing.action.cust.enable");
		findObj = Outside.service(this,"gus06.sys.store.obj.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		String execute = get(map,KEY_EXECUTE);
		if(execute==null) throw new Exception("Execute key not found for action");
		
		Action action = (Action) exeToAction.t(findObj(execute));
		
		String display = get(map,KEY_DISPLAY);
		if(display!=null) repaint.v(display,action);
		
		String desc = get(map,KEY_DESC);
		if(desc!=null) action.putValue(Action.SHORT_DESCRIPTION,desc);
		
		String enable = get(map,KEY_ENABLE);
		if(enable!=null) custEnable.p(new Object[]{action,findObj(enable)});
		
		return action;
	}
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	private Object findObj(String rule) throws Exception
	{return findObj.t(rule);}
}
