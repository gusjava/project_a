package a.entity.gus06.sys.expression1.apply.op._jmenu;

import a.framework.*;
import java.util.Map;
import javax.swing.JMenu;
import javax.swing.Action;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170520";}

	public final static String KEY_DISPLAY = "display";
	public final static String KEY_EXECUTE = "execute";
	
	
	
	private Service repaint;
	private Service linkToE;
	
	public EntityImpl() throws Exception
	{
		repaint = Outside.service(this,"gus06.swing.button.cust2.display");
		linkToE = Outside.service(this,"gus06.swing.button.cust3.onclick.execute");
	}

	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Action) return build((Action) obj);
		if(obj instanceof String) return build((String) obj);
		if(obj instanceof Map) return build((Map) obj);
		if(obj instanceof E) return build((E) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private JMenu build(Map map) throws Exception
	{
		String display = (String) get(map,KEY_DISPLAY);
		E execute = (E) get(map,KEY_EXECUTE);
		
		JMenu menu = new JMenu();
		if(display!=null) repaint.v(display,menu);
		if(execute!=null) linkToE.p(new Object[]{menu,execute});
		
		return menu;
	}
	
	
	private JMenu build(E execute) throws Exception
	{
		JMenu menu = new JMenu();
		linkToE.p(new Object[]{menu,execute});
		return menu;
	}
	
	private JMenu build(String display) throws Exception
	{
		JMenu menu = new JMenu();
		repaint.v(display,menu);
		return menu;
	}
	
	private JMenu build(Action action) throws Exception
	{
		return new JMenu(action);
	}
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
