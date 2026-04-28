package a.entity.gus06.swing.dialog.cust3.map1;

import a.framework.*;
import javax.swing.JDialog;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20190519";}
	
	public static final String KEY_BOUNDS = "bounds";
	public static final String KEY_REMOVEONKEY = "removeonkey";
	public static final String KEY_UNDECORATED = "undecorated";
	public static final String KEY_RESIZABLE = "resizable";
	public static final String KEY_ALWAYSONTOP = "alwaysontop";
	public static final String KEY_CENTERED = "centered";
	public static final String KEY_DRAGGABLE = "draggable";


	private Service custBounds;
	private Service disposeOnKey;
	private Service setDraggable;

	public EntityImpl() throws Exception
	{
		custBounds = Outside.service(this,"gus06.swing.dialog.cust2.bounds");
		disposeOnKey = Outside.service(this,"gus06.swing.dialog.cust2.disposeonkey");
		setDraggable = Outside.service(this,"gus.x.swing.comp.cust.dragframe");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JDialog dialog = (JDialog) o[0];
		Map map = (Map) o[1];
		
		Object bounds = get(map,KEY_BOUNDS);
		if(bounds!=null) custBounds.p(new Object[]{dialog,bounds});
		
		String removeOnKey = (String) get(map,KEY_REMOVEONKEY);
		if(removeOnKey!=null) disposeOnKey.v(removeOnKey,dialog);
		
		
		// default true
		
		boolean undecorated = boolDT(map,KEY_UNDECORATED);
		dialog.setUndecorated(undecorated);
		
		boolean alwaysOnTop = boolDT(map,KEY_ALWAYSONTOP);
		dialog.setAlwaysOnTop(alwaysOnTop);
		
		
		// default false
		
		boolean resizable = boolDF(map,KEY_RESIZABLE);
		dialog.setResizable(resizable);
		
		boolean centered = boolDF(map,KEY_CENTERED);
		if(centered) dialog.setLocationRelativeTo(null);
		
		boolean draggable = boolDF(map,KEY_DRAGGABLE);
		if(draggable) setDraggable.p(dialog.getContentPane());
	}
	
	
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	
	
	
	private boolean boolDT(Map map, String key) throws Exception
	{return boolD(map,key,true);}
	
	
	private boolean boolDF(Map map, String key) throws Exception
	{return boolD(map,key,false);}
	
	
	private boolean boolD(Map map, String key, boolean defaultValue) throws Exception
	{
		if(!map.containsKey(key)) return defaultValue;
		Object value = map.get(key);
		return toBool(value);
	}
	
	private boolean toBool(Object obj) throws Exception
	{
		if(obj instanceof Boolean) return ((Boolean) obj).booleanValue();
		if(obj instanceof String) return Boolean.parseBoolean((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
