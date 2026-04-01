package a.entity.gus06.sys.expression1.apply.op._jbutton;

import a.framework.*;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.Action;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160830";}

	public final static String KEY_TEXT = "text";
	public final static String KEY_ICON = "icon";
	public final static String KEY_DISPLAY = "display";
	public final static String KEY_EXECUTE = "execute";
	public final static String KEY_TOOLTIP = "tooltip";
	public final static String KEY_FONT = "font";
	public final static String KEY_FONTSIZE = "fontsize";
	public final static String KEY_FOREGROUND = "foreground";
	public final static String KEY_BACKGROUND = "background";
	public final static String KEY_OPAQUE = "opaque";
	public final static String KEY_MARGIN = "margin";
	public final static String KEY_BORDER = "border";
	
	
	
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
	
	
	
	
	private JButton build(Map map) throws Exception
	{
		String display = (String) get(map,KEY_DISPLAY);
		E execute = (E) get(map,KEY_EXECUTE);
		
		JButton button = new JButton();
		if(display!=null) repaint.v(display,button);
		if(execute!=null) linkToE.p(new Object[]{button,execute});
		
		return button;
	}
	
	
	private JButton build(E execute) throws Exception
	{
		JButton button = new JButton();
		linkToE.p(new Object[]{button,execute});
		return button;
	}
	
	private JButton build(String display) throws Exception
	{
		JButton button = new JButton();
		repaint.v(display,button);
		return button;
	}
	
	private JButton build(Action action) throws Exception
	{
		return new JButton(action);
	}
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
